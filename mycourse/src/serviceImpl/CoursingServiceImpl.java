package serviceImpl;

import daoService.CourseDaoService;
import daoService.CoursingDaoService;
import entity.Course;
import entity.Coursing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.CoursingService;
import utils.TimeUtil;
import utils.TypeUtil;


import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;

@Repository
public class CoursingServiceImpl implements CoursingService {
    private static CourseDaoService courseDaoService;
    private static CoursingDaoService coursingDaoService;

    public CoursingServiceImpl(){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        courseDaoService=(CourseDaoService)appliationContext.getBean("CourseDaoService");
        coursingDaoService=(CoursingDaoService)appliationContext.getBean("CoursingDaoService");
    }
    @Override
    public ArrayList<Coursing> getAllCoursing() {
       return coursingDaoService.findAllCoursing();
    }

    @Override
    public void checkCoursing(String ccid) {
        Coursing coursing=coursingDaoService.findCouringById(ccid);
        String state=coursing.getState();
        if(state.equals(TypeUtil.state.CHECK.toString())){
            //only check can turn to choose
            coursing.setState(TypeUtil.state.CHOOSE.toString());
            String choose_time = TimeUtil.getCurrentTime();
            coursing.setChoose_time(choose_time);
            coursingDaoService.updateCoursing(coursing);
        }
    }

    @Override
    public void endCoursing(String ccid) {
        Coursing coursing=coursingDaoService.findCouringById(ccid);
        String state=coursing.getState();
        if(state.equals(TypeUtil.state.START.toString())) {
            //only start can turn to end
            coursing.setState(TypeUtil.state.END.toString());
            String end_time = TimeUtil.getCurrentTime();
            coursing.setEnd_time(end_time);
            coursingDaoService.updateCoursing(coursing);
        }
    }

    @Override
    public void createCoursing(Coursing coursing) {
        int num=coursingDaoService.numOfCoursings();
        num++;
        String id="cc"+String.valueOf(num);
        String cid=coursing.getCid();
        Course course=courseDaoService.findCourseById(cid);
        String cname=course.getName();
        coursing.setCname(cname);
        coursing.setId(id);
        coursing.setState(TypeUtil.state.CHECK.toString());
        coursingDaoService.addCoursing(coursing);
    }

    @Override
    public ArrayList<Coursing> getCoursingOfTerm(String term) {
        ArrayList<Coursing> coursings = coursingDaoService.findCoursingByTerm(term);
        ArrayList<Coursing> result = new ArrayList<Coursing>();
        if (coursings != null) {
            for (int i = 0; i <= coursings.size() - 1; i++) {
                Coursing coursing = coursings.get(i);
                String state = coursing.getState();
                if (!state.equals(TypeUtil.state.CHECK.toString())) {
                    //only checked courses can present to student
                    result.add(coursing);
                }
            }
        }
            return result;
    }

    @Override
    public ArrayList<Coursing> getCoursingOfTid(String tid) {
        ArrayList<Coursing> coursings=new ArrayList<Coursing>();
        ArrayList<String> courseIds=courseDaoService.findCourseIdByTid(tid);
        if(courseIds==null){
            return coursings;
        }
        for(int i=0;i<=courseIds.size()-1;i++){
            String cid=courseIds.get(i);
            ArrayList<Coursing> coursingsOfCourse=coursingDaoService.findCouringByCid(cid);
            coursings.addAll(coursingsOfCourse);
        }
        return coursings;
    }

    @Override
    public Coursing getCoursingOfId(String id) {
        return coursingDaoService.findCouringById(id);
    }

    @Override
    public void uploadScoreRecordOfCoursing(Part scoreRecord, String ccid,String servletPath) {
        String filename = scoreRecord.getSubmittedFileName();//获取上传文件名
        String dirPath = servletPath+"/coursingScore";
        String fileUrl=dirPath+ "/" + filename;
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdir();//如果目录不存在，则创建
        }
        try {
            scoreRecord.write(fileUrl);//将文件写入服务器*/
        }catch (IOException e){
            e.printStackTrace();
        }

        //upadate coursing
        Coursing coursing=coursingDaoService.findCouringById(ccid);
        coursing.setScorefile_name(filename);
        coursingDaoService.updateCoursing(coursing);
    }



    @Override
    public String downloadScoreRecordOfCoursing(String ccid,String servletPath) {
        Coursing coursing=coursingDaoService.findCouringById(ccid);
        String fileName=coursing.getScorefile_name();
        String dirPath = servletPath+"/coursingScore";
        String fileUrl=dirPath+ "/" + fileName;
        File file = new File(fileUrl);
        if(file.exists()){
        System.out.println("file exists");
        return fileName;
    }
        return null;
    }
/*
    public static void main(String args[]) {
        ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
        CoursingService coursingService = (CoursingService) serviceAppliationContext.getBean("CoursingService");

        Coursing coursing=new Coursing();
        coursing.setCid("c2");
        coursing.setLimit_num(50);
        coursing.setState(TypeUtil.state.CHECK.toString());
        coursing.setTerm("2016 spring");
        coursing.setClass_num("1");
        coursing.setScorefile_name("pic.bmp");

        coursingService.createCoursing(coursing);

        coursingService.checkCoursing("cc1");
        coursingService.checkCoursing("cc2");

        ArrayList<Coursing> coursings=coursingService.getCoursingOfTerm("2016 spring");
        System.out.println(coursings.size()+" "+coursings.get(0).getId());
        ArrayList<Coursing> coursings2=coursingService.getCoursingOfTid("t1");
        System.out.println(coursings2.size()+" "+coursings2.get(1).getId());


    }*/
    }
