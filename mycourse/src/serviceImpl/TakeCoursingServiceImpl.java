package serviceImpl;

import com.sun.deploy.security.ValidationState;
import daoService.ChooseCoursingDaoService;
import daoService.CourseDaoService;
import daoService.CoursingDaoService;
import daoService.TakeCoursingDaoService;
import entity.Coursing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.TakeCoursingService;
import utils.TimeUtil;
import utils.TypeUtil;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

@Repository
public class TakeCoursingServiceImpl implements TakeCoursingService {
    private static CourseDaoService courseDaoService;
    private static CoursingDaoService coursingDaoService;
    private static ChooseCoursingDaoService chooseCoursingDaoService;
    private static TakeCoursingDaoService takeCoursingDaoService;

    public TakeCoursingServiceImpl() {
        ApplicationContext appliationContext = new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        courseDaoService = (CourseDaoService) appliationContext.getBean("CourseDaoService");
        coursingDaoService = (CoursingDaoService) appliationContext.getBean("CoursingDaoService");
        chooseCoursingDaoService = (ChooseCoursingDaoService) appliationContext.getBean("ChooseCoursingDaoService");
        takeCoursingDaoService = (TakeCoursingDaoService) appliationContext.getBean("TakeCoursingDaoService");

    }

    @Override
    public ArrayList<Coursing> getCoursingOfSid(String sid) {
        ArrayList<String> coursingIdsOfSid = takeCoursingDaoService.findTakenCoursingBySid(sid);
        ArrayList<Coursing> result = new ArrayList<Coursing>();
        if (coursingIdsOfSid != null) {
            for (int i = 0; i <= coursingIdsOfSid.size() - 1; i++) {
                String ccid = coursingIdsOfSid.get(i);
                Coursing coursing = coursingDaoService.findCouringById(ccid);
                result.add(coursing);
            }
        }
        return result;
    }

    public void updateAllCoursingStateChooseToTake() {
        ArrayList<Coursing> coursings = coursingDaoService.findAllCoursing();
        if (coursings != null) {
            for (int i = 0; i <= coursings.size() - 1; i++) {
                Coursing coursing = coursings.get(i);
                String state = coursing.getState();
                if (state.equals(TypeUtil.state.CHOOSE.toString())) {
                    //only "choose"state can transfer to "start"state
                    String startTime = coursing.getStart_time();
                    String ccid = coursing.getId();
                    Boolean currentAfterStart = TimeUtil.ifCurrentAfterTargetTime(startTime);
                    if (currentAfterStart) {
                        coursing.setState(TypeUtil.state.START.toString());
                        coursingDaoService.updateCoursing(coursing);
                        updateCoursingTakenTable(ccid);
                    }
                }
            }
        }
    }

    //！！！这应该是一个私有方法，在查看我的课getCoursingOfSid之前做，根据时间生成某些从选课进入上课生成的中选学生名单
    //用来更新coursing_taken表，以更新我的课
    private void updateCoursingTakenTable(String ccid) {
        Coursing coursing = coursingDaoService.findCouringById(ccid);
        int limitNum = coursing.getLimit_num();
        ArrayList<String> studentIds = chooseCoursingDaoService.findChoosenStudentsOfCoursing(ccid);
        if (studentIds != null) {
            int chooseNum = studentIds.size();
            if (chooseNum > limitNum) {
                //如果选课人数超过课程限制人数，将随机产生中选人
                ArrayList<Integer> randoms = new ArrayList<Integer>();
                for (int i = 0; i <= limitNum - 1; i++) {
                    Random randomUtil = new Random();
                    int random = randomUtil.nextInt(chooseNum);
                    if (!randoms.contains(random)) {
                        randoms.add(random);
                        String sid = studentIds.get(random);
                        takeCoursingDaoService.addTakenCoursing(ccid, sid);
                    } else {
                        i--;
                    }
                }
                coursing.setTake_num(limitNum);
                coursingDaoService.updateCoursing(coursing);
            } else {
                for (int i = 0; i <= chooseNum - 1; i++) {
                    String sid = studentIds.get(i);
                    takeCoursingDaoService.addTakenCoursing(ccid, sid);
                }
                coursing.setTake_num(chooseNum);
                coursingDaoService.updateCoursing(coursing);
            }
        }

    }


    //查看选课内容后，按钮触发选课操作，返回成功类型（未开课并成功选择，已开课并成功选择——更新数据表）
    //开课前选课要更新choose表，开课后中选要更新choose和take
    //失败类型（已开课并无名额，已开课并已中选，未开课并已选择）
    @Override
    public String chooseCoursing(String ccid, String sid) {
        Coursing coursing = coursingDaoService.findCouringById(ccid);
        String state = coursing.getState();
        if (state.equals(TypeUtil.state.CHOOSE.toString())) {
            //选课状态：未开课并成功选择；未开课并已选择
            Boolean ifChoosed = chooseCoursingDaoService.ifChoosedCoursingByStudent(ccid, sid);
            if (ifChoosed) {
                return TypeUtil.chooseCoursingResult.HAVE_CHOOSED_COURSING.toString();
            }
            chooseCoursingDaoService.addChooseCoursing(ccid, sid);
            return TypeUtil.chooseCoursingResult.CHOOSE_SUCCESS.toString();
        }
        if (state.equals(TypeUtil.state.START.toString())) {
            //开课后，chooseCoursing这张表对该coursing就没有意义的
            Boolean ifTaken = takeCoursingDaoService.ifTookCoursingByStudent(ccid, sid);
            if (ifTaken) {
                return TypeUtil.chooseCoursingResult.HAVE_TAKEN_COURSING.toString();
            }
            int takenNum = coursing.getTake_num();
            int limitNum = coursing.getLimit_num();
            if (takenNum < limitNum) {
                takeCoursingDaoService.addTakenCoursing(ccid, sid);
                takenNum++;
                coursing.setTake_num(takenNum);
                coursingDaoService.updateCoursing(coursing);
                return TypeUtil.chooseCoursingResult.TAKE_SUCCESS.toString();
            }
            return TypeUtil.chooseCoursingResult.NO_REDUND.toString();
        }
        if(state.equals((TypeUtil.state.END.toString()))){
            return TypeUtil.chooseCoursingResult.COURSING_END.toString();
        }
        return null;
        //when in other state can not choose course
    }

    @Override
    public void cancelCoursing(String ccid, String sid) {
        takeCoursingDaoService.deleteTakenCoursing(ccid, sid);

        Coursing coursing=coursingDaoService.findCouringById(ccid);
        int takeNum=coursing.getTake_num();
        takeNum--;
        coursing.setTake_num(takeNum);
        coursingDaoService.updateCoursing(coursing);
    }

    @Override
    public Boolean ifChoosedOrTaken(String ccid, String sid) {
        Coursing coursing=coursingDaoService.findCouringById(ccid);
        String startTime=coursing.getStart_time();
        if(TimeUtil.ifCurrentAfterTargetTime(startTime)){
            //coursing started
            return takeCoursingDaoService.ifTookCoursingByStudent(ccid,sid);
        }else{
            //coursing not started, choosing
            return chooseCoursingDaoService.ifChoosedCoursingByStudent(ccid,sid);
        }
    }

    /*
    public static void main(String args[]) {

        ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
        TakeCoursingService takeCoursingService = (TakeCoursingService) serviceAppliationContext.getBean("TakeCoursingService");

        //takeCoursingService.chooseCoursing("cc1","s1");
       // takeCoursingService.chooseCoursing("cc1","s2");
      // takeCoursingService.chooseCoursing("cc1","s3");

        //takeCoursingService.updateAllCoursingStateChooseToTake();

        ArrayList<Coursing> coursings=takeCoursingService.getCoursingOfSid("s1");
        System.out.println(coursings.size()+" "+coursings.get(0).getId());

    }*/
}
