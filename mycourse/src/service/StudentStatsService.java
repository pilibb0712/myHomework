package service;

import java.util.HashMap;

public interface StudentStatsService {
    public HashMap<String,Integer> getStudentworkRecord(String sid);
    //student统计： 获得学生某学期所有课的作业提交记录，0代表提交了所有作业，正数即为少提交多少作业
    //同一个名字的课的缺作业数累加，这个网站的原则上一个学生不会选两次同样的课吧
    public HashMap<String,Integer> getStudentCourseOfTeacherRecord(String sid);
    //student统计：获得学生选的老师的课的数目折线图（老师-课的数目）
    public HashMap<String,Integer> getStudentCourseNumOfTermRecord(String sid);
    //student统计：获得学生每学期选课数目折线图
}
