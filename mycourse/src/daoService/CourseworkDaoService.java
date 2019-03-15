package daoService;

import entity.Coursework;

import java.util.ArrayList;

public interface CourseworkDaoService {
    public void addCoursework(Coursework coursework);
    public int numOfCourseworks();
    public Coursework findCourseworkById(String id);
    public ArrayList<Coursework> findCourseworkByCcid(String ccid);
    public void updateCoursework(Coursework coursework);
}
