package daoService;

import entity.Coursing;

import java.util.ArrayList;

public interface TakeCoursingDaoService {
    public void addTakenCoursing(String ccid,String sid);
    //public void addTakenCoursings(ArrayList<String[]> takens);
    //insert taken_coursing of large amount
    public ArrayList<String> findTakenCoursingBySid(String sid);
    //of all terms, for find all coursings taken by the student
    public Boolean ifTookCoursingByStudent(String ccid,String sid);
    //just of current term, for choose coursing
    public void deleteTakenCoursing(String ccid,String sid);
    public ArrayList<String> findTakenStudentsOfCoursing(String ccid);
}
