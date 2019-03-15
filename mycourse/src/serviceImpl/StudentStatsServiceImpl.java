package serviceImpl;

import daoService.*;
import entity.Course;
import entity.Coursework;
import entity.Coursing;
import entity.Teacher;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.StudentStatsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentStatsServiceImpl implements StudentStatsService {
    private static CourseDaoService courseDaoService;
    private static CoursingDaoService coursingDaoService;
    private static TakeCoursingDaoService takeCoursingDaoService;
    private static CourseworkDaoService courseworkDaoService;
    private static StudentworkDaoService studentworkDaoService;
    private static TeacherDaoService teacherDaoService;

    public StudentStatsServiceImpl(){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        courseDaoService=(CourseDaoService)appliationContext.getBean("CourseDaoService");
        coursingDaoService=(CoursingDaoService)appliationContext.getBean("CoursingDaoService");
        takeCoursingDaoService=(TakeCoursingDaoService)appliationContext.getBean("TakeCoursingDaoService");
        courseworkDaoService=(CourseworkDaoService)appliationContext.getBean("CourseworkDaoService");
        studentworkDaoService=(StudentworkDaoService)appliationContext.getBean("StudentworkDaoService");
        teacherDaoService=(TeacherDaoService)appliationContext.getBean("TeacherDaoService");
    }

    @Override
    public HashMap<String, Integer> getStudentworkRecord(String sid) {
        HashMap<String, Integer> result=new HashMap<String, Integer>();
        ArrayList<String> ccids=takeCoursingDaoService.findTakenCoursingBySid(sid);
        if(ccids!=null){
            for(int i=0;i<=ccids.size()-1;i++){
                String ccid=ccids.get(i);
                Coursing coursing=coursingDaoService.findCouringById(ccid);
                String cid=coursing.getCid();
                Course course=courseDaoService.findCourseById(cid);
                String courseName=course.getName();
                int num=0;
                ArrayList<Coursework> courseworks=courseworkDaoService.findCourseworkByCcid(ccid);
                if(courseworks!=null){
                    for(int j=0;j<=courseworks.size()-1;j++){
                        Coursework coursework=courseworks.get(j);
                        String cwid=coursework.getId();
                        Boolean studentWorkExist=studentworkDaoService.ifStudentWorkExists(cwid,sid);
                        if(!studentWorkExist){
                            num++;
                        }
                    }
                }
               if(result.get(courseName)!=null){
                    int preNum=result.get(courseName);
                    num=preNum+num;
                    result.put(courseName,num);
                }else {
                    result.put(courseName, num);
                }
            }
            return result;
        }
        //ccid==null: no coursing of student
        return null;
    }

    @Override
    public HashMap<String, Integer> getStudentCourseOfTeacherRecord(String sid) {
        HashMap<String, Integer> result=new HashMap<String, Integer>();
        ArrayList<String> ccids=takeCoursingDaoService.findTakenCoursingBySid(sid);
        if(ccids!=null){
            for(int i=0;i<=ccids.size()-1;i++){
                String ccid=ccids.get(i);
                Coursing coursing=coursingDaoService.findCouringById(ccid);
                String cid=coursing.getCid();
                Course course=courseDaoService.findCourseById(cid);
                String tid=course.getTid();
                Teacher teacher=teacherDaoService.findTeacherById(tid);
                String teacherName=teacher.getName();
                if(result.get(teacherName)==null){
                    result.put(teacherName,1);
                    continue;
                }
                int num=result.get(teacherName);
                num++;
                result.put(teacherName,num);
            }
            return result;
        }
        return null;
    }

    @Override
    public HashMap<String, Integer> getStudentCourseNumOfTermRecord(String sid) {
        HashMap<String, Integer> result=new HashMap<String, Integer>();
        ArrayList<String> ccids=takeCoursingDaoService.findTakenCoursingBySid(sid);
        if(ccids!=null){
            for(int i=0;i<=ccids.size()-1;i++){
                String ccid=ccids.get(i);
                Coursing coursing=coursingDaoService.findCouringById(ccid);
                String term=coursing.getTerm();
                if(result.get(term)==null){
                    result.put(term,1);
                    continue;
                }
                int num=result.get(term);
                num++;
                result.put(term,num);
            }
            return result;
        }
        return null;
    }
/*
    public static void main(String[] args){
        ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
        StudentStatsService studentStatsService = (StudentStatsService) serviceAppliationContext.getBean("StudentStatsService");

        HashMap<String,Integer> result=studentStatsService.getStudentworkRecord("s1");
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        HashMap<String,Integer> result2=studentStatsService.getStudentCourseNumOfTermRecord("s1");
        for (Map.Entry<String, Integer> entry : result2.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        HashMap<String,Integer> result3=studentStatsService.getStudentCourseOfTeacherRecord("s1");
        for (Map.Entry<String, Integer> entry : result3.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }*/
}
