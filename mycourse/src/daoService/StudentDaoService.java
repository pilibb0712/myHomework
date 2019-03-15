package daoService;


import entity.Student;

import java.util.ArrayList;

public interface StudentDaoService {
    public Boolean ifStudentNameExist(String name);
    public Boolean ifStudentEmailExist(String email);
    public Boolean ifEmailExistOfCurrentStudents(String email);
    public Student findStudentByNameAndPw(String name, String password);
    public void addStudent(Student student);
    public int numOfStudents();
    public void updateStudent(Student student);
    public Student findStudentById(String id);
    public Student findStudentByName(String name);
    public Student findStudentByEmail(String email);

    public ArrayList<String> findGradesOfStudents();
    public int findStudentNumOfGrade(String grade);
}
