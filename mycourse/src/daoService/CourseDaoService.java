package daoService;

import entity.Course;

import java.util.ArrayList;

public interface CourseDaoService {
    public void addCourse(Course course);
    public Course findCourseById(String id);
    public int numOfCourses();
    public ArrayList<Course> findCourseByTid(String tid);
    public ArrayList<String> findCourseIdByTid(String tid);
    public ArrayList<Course> findAllCourse();
    //this method for admin to check course
    public void updateCourse(Course course);
}
