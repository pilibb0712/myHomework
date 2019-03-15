package service;

import java.util.HashMap;

public interface AdminStatsService {
    public HashMap<String,Integer> getUserNumOfType();
    //admin统计：获得每种类别的用户数目
    public HashMap<String,Integer> getStudentNumOfGrade();
    //admin统计：获得每年级的学生数目
    public HashMap<String,Integer> getCoursingNumOfEachTerm();
    //admin统计： 获得每学期的开课量
    public HashMap<String, Integer> getAllCourseTakenNum();
    //admin统计：获得所有课程的总计选课人数，可以看出什么课比较受欢迎
}
