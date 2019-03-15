package service;

import java.util.ArrayList;
import java.util.HashMap;

public interface TeacherStatsService {
    public HashMap<String,Integer> getStudentworkRecordOfCoursing(String ccid);
    //teacher统计： 获得一门课学生的所有作业情况，0表示全交，负数表示有多少没交
    public HashMap<String,Integer> getTeacherCoursingNumOfTerm(String tid);
    //teacher统计： 获得教师每学期开课数目
    public HashMap<String,Integer> getStudentNumOfTeacherCourse(String tid);
    //teacher统计： 获得教师某学期所有开课的学生数目
    public ArrayList<String> getAllTerms();
}
