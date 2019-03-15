package daoService;

import entity.Teacher;

public interface TeacherDaoService {
    public Boolean ifTeacherNameExist(String name);
    public Boolean ifTeacherEmailExist(String email);
    public Teacher findTeacherByNameAndPw(String name, String password);
    public void addTeacher(Teacher teacher);
    public void updateTeacher(Teacher teacher);
    public int numOfTeachers();
    public Teacher findTeacherById(String id);
    public Teacher findTeacherByName(String name);
}
