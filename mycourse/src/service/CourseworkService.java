package service;

import entity.Coursework;
import entity.Studentwork;

import javax.servlet.http.Part;
import java.util.ArrayList;

public interface CourseworkService {
    public void createCoursework(Coursework coursework);
    public ArrayList<Coursework> getCourseworkOfCoursing(String ccid);
    public Coursework getCoursework(String cwid);

    public void uploadScoreRecordOfCoursework(Part scoreRecord, String cwid,String servletPath);
    //upload and save filename
    public String downloadScoreRecordOfCoursework(String cwid,String servletPath);

    public void uploadStudentwork(Part studentworkPart,String cwid,String sid,String servletPath);
    //for student, upload and add studentwork
    public ArrayList<Studentwork> getStudentworkOfStudent(String cwid,String sid);
    //for student and teacher
    public ArrayList<Studentwork> getStudentworkOfCoursework(String cwid);
    public String  zipStudentworks(String cwid,String servlertPath);
}
