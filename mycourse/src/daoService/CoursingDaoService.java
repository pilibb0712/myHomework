package daoService;

import entity.Coursing;

import java.util.ArrayList;

public interface CoursingDaoService {
    public void addCoursing(Coursing coursing);
    public int numOfCoursings();
    public Coursing findCouringById(String id);
    public ArrayList<Coursing> findCouringByCid(String cid);
    //the method for teacher to find his lessons
    public ArrayList<Coursing> findCoursingByTerm(String term);
    //the method for admin to get statistics
    public ArrayList<Coursing> findAllCoursing();
    //the method for admin to check coursing
    public void updateCoursing(Coursing coursing);
    public ArrayList<String> findTermsOfAllCoursing();
    public int findCoursingNumOfTerm(String term);
}
