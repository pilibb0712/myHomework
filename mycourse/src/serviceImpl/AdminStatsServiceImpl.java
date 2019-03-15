package serviceImpl;

import daoService.*;
import entity.Course;
import entity.Coursing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.AdminStatsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Repository
public class AdminStatsServiceImpl implements AdminStatsService {
    private static TeacherDaoService teacherDaoService;
    private static StudentDaoService studentDaoService;
    private static AdminDaoService adminDaoService;
    private static CoursingDaoService coursingDaoService;
    private static CourseDaoService courseDaoService;
    private static TakeCoursingDaoService takeCoursingDaoService;
    public AdminStatsServiceImpl(){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        teacherDaoService=(TeacherDaoService)appliationContext.getBean("TeacherDaoService");
        studentDaoService=(StudentDaoService)appliationContext.getBean("StudentDaoService");
        adminDaoService=(AdminDaoService)appliationContext.getBean("AdminDaoService");
        coursingDaoService=(CoursingDaoService)appliationContext.getBean("CoursingDaoService");
        courseDaoService=(CourseDaoService)appliationContext.getBean("CourseDaoService");
        takeCoursingDaoService=(TakeCoursingDaoService)appliationContext.getBean("TakeCoursingDaoService");
    }


    @Override
    public HashMap<String, Integer> getUserNumOfType() {
        int studentNum=studentDaoService.numOfStudents();
        int teacherNum=teacherDaoService.numOfTeachers();
        int adminNum=adminDaoService.numOfAdmin();
        HashMap<String,Integer> result=new HashMap<String,Integer>();
        result.put("student",studentNum);
        result.put("teacher",teacherNum);
        result.put("admin",adminNum);
        return result;
    }

    @Override
    public HashMap<String, Integer> getStudentNumOfGrade() {
        ArrayList<String> grades=studentDaoService.findGradesOfStudents();
        if(grades!=null){
            HashMap<String,Integer> result=new HashMap<String,Integer>();
            for(int i=0;i<=grades.size()-1;i++){
                String grade=grades.get(i);
                int num=studentDaoService.findStudentNumOfGrade(grade);
                result.put(grade,num);
            }
            return result;
        }
        return null;
    }

    @Override
    public HashMap<String, Integer> getCoursingNumOfEachTerm() {
        ArrayList<String> terms=coursingDaoService.findTermsOfAllCoursing();
        if(terms!=null){
            HashMap<String,Integer> result=new HashMap<String,Integer>();
            for(int i=0;i<=terms.size()-1;i++){
                String term=terms.get(i);
                int num=coursingDaoService.findCoursingNumOfTerm(term);
                result.put(term,num);
            }
            return result;
        }
        return null;
    }

    @Override
    public HashMap<String, Integer> getAllCourseTakenNum() {
        ArrayList<Course> courses=courseDaoService.findAllCourse();
        ArrayList<Coursing> coursings=coursingDaoService.findAllCoursing();
        HashMap<String,Integer> result=new HashMap<String,Integer>();
        if(coursings!=null){
            if(courses!=null){
                for(int i=0;i<=courses.size()-1;i++){
                    Course course=courses.get(i);
                    String cname=course.getName();
                    result.put(cname,0);
                }
            }
            //coursing不等于null，course就不可能等于null，coursing等于null，那也没什么好求的了
            for(int j=0;j<=coursings.size()-1;j++){
                Coursing coursing=coursings.get(j);
                String cid=coursing.getCid();
                Course course=courseDaoService.findCourseById(cid);
                String courseName=course.getName();
                String ccid=coursing.getId();
                ArrayList<String> studentsNumOfCoursing=takeCoursingDaoService.findTakenStudentsOfCoursing(ccid);
                int num=0;
                if(studentsNumOfCoursing!=null){
                    num=studentsNumOfCoursing.size();
                }
                int currentNumOfCourse=result.get(courseName);
                currentNumOfCourse=currentNumOfCourse+num;
                result.put(courseName,currentNumOfCourse);
                //同一个key，hashmap会覆盖
            }
        }
        return result;
    }
/*
    public static void main(String args[]) {

        ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
        AdminStatsService adminStatsService = (AdminStatsService) serviceAppliationContext.getBean("AdminStatsService");

        HashMap<String,Integer> result=adminStatsService.getAllCourseTakenNum();
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        HashMap<String,Integer> result2=adminStatsService.getCoursingNumOfEachTerm();
        for (Map.Entry<String, Integer> entry : result2.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        HashMap<String,Integer> result3=adminStatsService.getStudentNumOfGrade();
        for (Map.Entry<String, Integer> entry : result3.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        HashMap<String,Integer> result4=adminStatsService.getUserNumOfType();
        for (Map.Entry<String, Integer> entry : result4.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }*/
    }
