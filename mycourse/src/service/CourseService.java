package service;

import entity.Course;
import entity.Courseware;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.HashMap;

public interface CourseService {
    public void createCourse(Course course);
    public ArrayList<Course> getCoursesOfTeacher(String tid);
    public Course getCourse(String cid);
    public ArrayList<Course> getAllCourse();
    //所有course要显示是否check状态，这是要给Admin审核用的方法
    public void checkCourse(String cid);
    public Course getCourseOfCoursing(String ccid);

}
