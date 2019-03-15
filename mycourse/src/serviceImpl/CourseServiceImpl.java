package serviceImpl;


import daoService.CourseDaoService;
import daoService.CoursingDaoService;
import entity.Course;
import entity.Coursing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.CourseService;

import java.util.ArrayList;

@Repository
public class CourseServiceImpl implements CourseService {
    private static CourseDaoService courseDaoService;
    private static CoursingDaoService coursingDaoService;

    public CourseServiceImpl(){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        courseDaoService=(CourseDaoService)appliationContext.getBean("CourseDaoService");
        coursingDaoService=(CoursingDaoService)appliationContext.getBean("CoursingDaoService");
    }

    @Override
    public void createCourse(Course course) {
        int num=courseDaoService.numOfCourses();
        num++;
        String id="c"+String.valueOf(num);
        course.setId(id);
        course.setChecked(false);
        courseDaoService.addCourse(course);
    }

    @Override
    public ArrayList<Course> getCoursesOfTeacher(String tid) {
        return courseDaoService.findCourseByTid(tid);
    }

    @Override
    public Course getCourse(String cid) {
        return courseDaoService.findCourseById(cid);
    }

    @Override
    public ArrayList<Course> getAllCourse() {
        return courseDaoService.findAllCourse();
    }

    @Override
    public void checkCourse(String cid) {
        Course course=courseDaoService.findCourseById(cid);
        course.setChecked(true);
        courseDaoService.updateCourse(course);
    }

    @Override
    public Course getCourseOfCoursing(String ccid) {
        Coursing coursing=coursingDaoService.findCouringById(ccid);
        String cid=coursing.getCid();
        return courseDaoService.findCourseById(cid);
    }

    /*
    public static void main(String args[]){
        ApplicationContext serviceAppliationContext=new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
        CourseService courseService=(CourseService)serviceAppliationContext.getBean("CourseService");

        Course course=new Course();
        course.setName("Electronic Business");
        course.setContent("About Electronic Business");
        course.setTid("t4");
        courseService.createCourse(course);

        courseService.checkCourse("c2");

        Course course1=courseService.getCourseOfCoursing("cc1");
        System.out.println(course1.getId());

    }*/
}
