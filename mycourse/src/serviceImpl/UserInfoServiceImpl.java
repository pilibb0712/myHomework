package serviceImpl;

import daoService.AdminDaoService;
import daoService.StudentDaoService;
import daoService.TeacherDaoService;
import entity.Admin;
import entity.Courseware;
import entity.Student;
import entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.UserInfoService;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@Repository
public class UserInfoServiceImpl implements UserInfoService {
    private static TeacherDaoService teacherDaoService;
    private static StudentDaoService studentDaoService;
    private static AdminDaoService adminDaoService;

    public UserInfoServiceImpl() {
        ApplicationContext appliationContext = new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        teacherDaoService = (TeacherDaoService) appliationContext.getBean("TeacherDaoService");
        studentDaoService = (StudentDaoService) appliationContext.getBean("StudentDaoService");
        adminDaoService = (AdminDaoService) appliationContext.getBean("AdminDaoService");
    }

    @Override
    public Boolean ifEmailExists(String email) {
        Boolean ifEmailOfTeacher = teacherDaoService.ifTeacherEmailExist(email);
        Boolean ifEmailOfStudent = studentDaoService.ifEmailExistOfCurrentStudents(email);
        //学生注销了后，他的邮箱就可以重新注册
        return ifEmailOfStudent || ifEmailOfTeacher;
    }

    @Override
    public Boolean ifNameExists(String name) {
        Boolean ifNameOfTeacher = teacherDaoService.ifTeacherNameExist(name);
        Boolean ifNameOfStudent = studentDaoService.ifStudentNameExist(name);
        Boolean ifNameOfAdmin = adminDaoService.ifAdminNameExist(name);
        return ifNameOfAdmin || ifNameOfStudent || ifNameOfTeacher;
    }

    public Boolean ifNameExistsOfUser(String name,String type){
        Boolean result=false;
        if(type.equals("Student")){
            result=studentDaoService.ifStudentNameExist(name);
        }else if(type.equals("Teacher")){
            result=teacherDaoService.ifTeacherNameExist(name);
        }else if(type.equals("Admin")){
            result=adminDaoService.ifAdminNameExist(name);
        }else{
            System.out.println("no user of type: "+type);
        }
        return  result;
    }

    @Override
    public Student registerStudent(Student student) {
        String email = student.getEmail();
        Boolean ifEmailExist = studentDaoService.ifStudentEmailExist(email);
        String id = "";
        if (ifEmailExist) {
            //如果用注销了的邮箱注册，默认和原来同一个人，数据共享
            Student preStudent = studentDaoService.findStudentByEmail(email);
            id = preStudent.getId();
            student.setId(id);
            student.setCanceled(false);
            studentDaoService.updateStudent(student);
        } else {
            int num = studentDaoService.numOfStudents();
            num++;
            id = "s" + String.valueOf(num);
            student.setId(id);
            student.setCanceled(false);
            studentDaoService.addStudent(student);
        }
        return student;
    }

    @Override
    public Student getStudentById(String sid) {
        return studentDaoService.findStudentById(sid);
    }

    @Override
    public Student getStudentByName(String name) {
        return studentDaoService.findStudentByName(name);
    }

    @Override
    public Student loginStudent(String name, String password) {
        return studentDaoService.findStudentByNameAndPw(name, password);
    }

    @Override
    public void cancelStudent(String sid) {
        Student student = studentDaoService.findStudentById(sid);
        student.setCanceled(true);
        studentDaoService.updateStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDaoService.updateStudent(student);
    }

    @Override
    public void uploadStudentPic(Part part, String sid, String servletPath) {
        String filename = part.getSubmittedFileName();//获取上传文件名
        String dirPath = servletPath+"/userPic";
        String fileUrl=dirPath+ "/" + filename;
        System.out.println(fileUrl);
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdir();//如果目录不存在，则创建
        }
        try {
            part.write(fileUrl);//将文件写入服务器*/
        }catch (IOException e){
            e.printStackTrace();
        }

       Student student=studentDaoService.findStudentById(sid);
        student.setPic_name(filename);
        studentDaoService.updateStudent(student);

    }

    @Override
    public Teacher registerTeacher(Teacher teacher) {
        int num = teacherDaoService.numOfTeachers();
        num++;
        String id = "t" + String.valueOf(num);
        teacher.setId(id);
        teacherDaoService.addTeacher(teacher);
        return teacher;
    }

    @Override
    public Teacher getTeacherById(String tid) {
        return teacherDaoService.findTeacherById(tid);
    }

    @Override
    public Teacher getTeacherByName(String name) {
        return teacherDaoService.findTeacherByName(name);
    }

    @Override
    public Teacher loginTeacher(String name, String password) {
        return teacherDaoService.findTeacherByNameAndPw(name, password);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherDaoService.updateTeacher(teacher);
    }

    @Override
    public void uploadTeacherPic(Part part, String tid, String servletPath) {
        String filename = part.getSubmittedFileName();//获取上传文件名
        String dirPath = servletPath+"/userPic";
        String fileUrl=dirPath+ "/" + filename;
        System.out.println(fileUrl);
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdir();//如果目录不存在，则创建
        }
        try {
            part.write(fileUrl);//将文件写入服务器*/
        }catch (IOException e){
            e.printStackTrace();
        }

        Teacher teacher=teacherDaoService.findTeacherById(tid);
        teacher.setPic_name(filename);
        teacherDaoService.updateTeacher(teacher);
    }

    @Override
    public Admin loginAdmin(String name, String password) {
        return adminDaoService.findAdminByNameAndPw(name, password);
    }
    /*
        public static void main(String args[]) {

            ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
            UserInfoService userInfoService = (UserInfoService) serviceAppliationContext.getBean("UserInfoService");

             System.out.println(userInfoService.ifNameExists("qiaobb"));

            System.out.println(userInfoService.ifNameExists("bei"));
            System.out.println(userInfoService.ifNameExists("teddy"));
            System.out.println(userInfoService.ifNameExists("betty"));

            System.out.println(userInfoService.ifEmailExists("1292155474@163.com"));
            System.out.println(userInfoService.ifEmailExists("1292155474@126.com"));
            System.out.println(userInfoService.ifEmailExists("1292155475@163.com"));

            Student student1=new Student();
            student1.setName("delanna");
            student1.setEmail("161250194@smail.nju.edu.cn");
            student1.setGrade("2017");
            student1.setPassword("1234");
            student1.setStudent_number("171250194");
            userInfoService.registerStudent(student1);

            Student student2=new Student();
            student2.setName("catryna");
            student2.setEmail("1292155474@126.com");
            student2.setGrade("2015");
            student2.setPassword("1234");
            student2.setStudent_number("151250194");
            userInfoService.registerStudent(student2);

            userInfoService.cancelStudent("s1");

            Teacher teacher1=new Teacher();
            teacher1.setEmail("1292155475@126.com");
            teacher1.setName("tinny");
            teacher1.setPassword("1234");
            userInfoService.registerTeacher(teacher1);

        Teacher teacher1=new Teacher();
        teacher1.setEmail("1292155475@qq.com");
        teacher1.setName("nuna");
        teacher1.setPassword("1234");
        userInfoService.registerTeacher(teacher1);

        teacher1.setEmail("1292155475@smail.nju.edu.cn");
        teacher1.setName("benny");
        teacher1.setPassword("1234");
        userInfoService.registerTeacher(teacher1);

    } */
}
