package serviceImpl;

import daoService.*;
import entity.Course;
import entity.Coursework;
import entity.Coursing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.TeacherStatsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TeacherStatsServiceImpl implements TeacherStatsService {
    private static CourseDaoService courseDaoService;
    private static CoursingDaoService coursingDaoService;
    private static TakeCoursingDaoService takeCoursingDaoService;
    private static CourseworkDaoService courseworkDaoService;
    private static StudentworkDaoService studentworkDaoService;
    private static TeacherDaoService teacherDaoService;

    public TeacherStatsServiceImpl(){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        courseDaoService=(CourseDaoService)appliationContext.getBean("CourseDaoService");
        coursingDaoService=(CoursingDaoService)appliationContext.getBean("CoursingDaoService");
        takeCoursingDaoService=(TakeCoursingDaoService)appliationContext.getBean("TakeCoursingDaoService");
        courseworkDaoService=(CourseworkDaoService)appliationContext.getBean("CourseworkDaoService");
        studentworkDaoService=(StudentworkDaoService)appliationContext.getBean("StudentworkDaoService");
        teacherDaoService=(TeacherDaoService)appliationContext.getBean("TeacherDaoService");
    }
    @Override
    public HashMap<String, Integer> getStudentworkRecordOfCoursing(String ccid) {
        HashMap<String, Integer> result=new HashMap<String, Integer>();
        ArrayList<String> sids=takeCoursingDaoService.findTakenStudentsOfCoursing(ccid);
        if(sids!=null){
            ArrayList<Coursework> courseworks=courseworkDaoService.findCourseworkByCcid(ccid);
            if(courseworks!=null){
                    for(int j=0;j<=courseworks.size()-1;j++){
                        Coursework coursework=courseworks.get(j);
                        String cwName=coursework.getTopic();
                        String cwid=coursework.getId();
                        int num=0;
                        for(int i=0;i<=sids.size()-1;i++){
                            String sid=sids.get(i);
                            Boolean ifStudentworkExist=studentworkDaoService.ifStudentWorkExists(cwid,sid);
                            if(!ifStudentworkExist){
                                num++;
                            }
                        }
                        if(result.get(cwName)!=null){
                            //万一有同名的两个作业，记为同一个
                            int preNum=result.get(cwName);
                            num=preNum+num;
                        }
                        result.put(cwName,num);
                    }
                    return result;
            }
            return result;
        }
        return null;
    }

    @Override
    public HashMap<String, Integer> getTeacherCoursingNumOfTerm(String tid) {
        HashMap<String, Integer> result=new HashMap<String, Integer>();
        ArrayList<Course> courses=courseDaoService.findCourseByTid(tid);
        if(courses!=null){
            for(int i=0;i<=courses.size()-1;i++) {
                Course course = courses.get(i);
                String cid = course.getId();
                ArrayList<Coursing> coursings = coursingDaoService.findCouringByCid(cid);
                if(coursings!=null){
                    for(int j=0;j<=coursings.size()-1;j++){
                       Coursing coursing=coursings.get(j);
                       String term=coursing.getTerm();
                       if(result.get(term)==null){
                           result.put(term,1);
                           continue;
                       }
                       int num=result.get(term);
                       num++;
                       result.put(term,num);
                    }
                }
            }
            return result;
        }
        return null;
    }

    @Override
    public HashMap<String, Integer> getStudentNumOfTeacherCourse(String tid) {
        HashMap<String, Integer> result=new HashMap<String, Integer>();
        ArrayList<Course> courses=courseDaoService.findCourseByTid(tid);
        if(courses!=null){
            for(int i=0;i<=courses.size()-1;i++){
                Course course=courses.get(i);
                String cid=course.getId();
                String cname=course.getName();
                ArrayList<Coursing> coursings=coursingDaoService.findCouringByCid(cid);
                int num=0;
                if((coursings==null)||(coursings.size()==0)){
                    result.put(cname,num);
                }else{
                    for(int j=0;j<=coursings.size()-1;j++){
                        Coursing coursing=coursings.get(j);
                        String ccid=coursing.getId();
                        ArrayList<String> sids=takeCoursingDaoService.findTakenStudentsOfCoursing(ccid);
                        if(sids!=null){
                            num=num+sids.size();
                        }
                    }
                    if(result.get(cname)!=null){
                        int preNum=result.get(cname);
                        num=preNum+num;
                    }
                    result.put(cname,num);

                }
            }
            return result;
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllTerms() {
        return coursingDaoService.findTermsOfAllCoursing();
    }
    /*
    public static void main(String[] args){
        ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
                TeacherStatsService teacherStatsService = (TeacherStatsService) serviceAppliationContext.getBean("TeacherStatsService");

        HashMap<String,Integer> result=teacherStatsService.getStudentNumOfTeacherCoursing("2016 spring","t1");
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        HashMap<String,Integer> result2=teacherStatsService.getStudentworkRecordOfCoursing("cc1");
        for (Map.Entry<String, Integer> entry : result2.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        HashMap<String,Integer> result3=teacherStatsService.getTeacherCoursingNumOfTerm("t1");
        for (Map.Entry<String, Integer> entry : result3.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }*/
}
