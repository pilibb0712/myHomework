package service;

import entity.Courseware;

import javax.servlet.http.Part;
import java.util.ArrayList;

public interface CoursewareService {
    public void uploadCourseware(Part coursewarePart, String cid,String servletPath);
    //upload courseware and add courseware
    public String downloadCourseware(String cwid);
    public ArrayList<Courseware> getCoursewareOfCourse(String cid);
    public ArrayList<Courseware> getCoursewareOfCoursing(String ccid);
}
