package daoService;


import entity.Studentwork;

import java.util.ArrayList;

public interface StudentworkDaoService {
    public void addStudentwork(Studentwork studentwork);
    public void updateStudentwork(Studentwork studentwork);
    public int numOfStudentworks();
    public Studentwork findStudentworkById(String id);
    public ArrayList<Studentwork> findStudentworkByCwid(String cwid);
    public ArrayList<Studentwork> findStudentworkByCwidAndSid(String cwid,String sid);
    public ArrayList<String> findStudentByCwid(String cwid);
    public Boolean ifStudentWorkExists(String cwid,String sid);
}
