package service;

import entity.Admin;
import entity.Student;
import entity.Teacher;

import javax.servlet.http.Part;
import java.util.HashMap;

public interface UserInfoService {
    public Boolean ifEmailExists(String email);
    //email must be only no matter you are student or teacher
    public Boolean ifNameExists(String name);
    //email must be only no matter you are student, teacher or admin
    public Boolean ifNameExistsOfUser(String name,String userType);


    public Student registerStudent(Student student);
    public Student getStudentById(String sid);
    public Student getStudentByName(String name);
    public Student loginStudent(String name,String password);
    public void cancelStudent(String sid);
    public void updateStudent(Student student);
    public void uploadStudentPic(Part part, String sid,String servletPath);

    public Teacher registerTeacher(Teacher teacher);
    public Teacher getTeacherById(String tid);
    public Teacher getTeacherByName(String name);
    public Teacher loginTeacher(String name,String password);
    public void updateTeacher(Teacher teacher);
    public void uploadTeacherPic(Part part, String tid,String servletPath);

    public Admin loginAdmin(String name, String password);

}
