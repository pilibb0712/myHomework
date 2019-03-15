package servlet;

import entity.Admin;
import entity.Student;
import entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
@MultipartConfig
public class LoginServlet extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private  UserInfoService userInfoService = (UserInfoService) serviceAppliationContext.getBean("UserInfoService");

    public LoginServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType=request.getParameter("userType");
        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String result="";
        if(userInfoService.ifNameExists(userName)) {
            if (userType.equals("Admin")) {
                Boolean adminNameExist=userInfoService.ifNameExistsOfUser(userName,"Admin");
                if(adminNameExist) {
                    Admin admin = userInfoService.loginAdmin(userName, password);
                    if (admin == null) {
                        result = "password is wrong";
                    } else {
                        session.setAttribute("userType", "Admin");
                        session.setAttribute("user", admin);
                        result = "login successfully";
                    }
                }else{
                    result="no this admin name.";
                }
            } else if (userType.equals("Student")) {
                Boolean studentNameEixst=userInfoService.ifNameExistsOfUser(userName,"Student");
                if(studentNameEixst){
                    Student student = userInfoService.loginStudent(userName, password);
                    if (student == null) {
                        result="password is wrong";
                    } else {
                        session.setAttribute("userType","Student");
                        session.setAttribute("user",student);
                        result="login successfully";
                    }
                }else{
                    result="no this student name.";
                }
            } else if (userType.equals("Teacher")) {
                Boolean teacherNameEixst=userInfoService.ifNameExistsOfUser(userName,"Teacher");
                if(teacherNameEixst){
                    Teacher teacher = userInfoService.loginTeacher(userName, password);
                    System.out.println(teacher.getName()+"   "+teacher.getPic_name());
                    if (teacher == null) {
                        result="password is wrong";
                    } else {
                        session.setAttribute("userType","Teacher");
                        session.setAttribute("user",teacher);
                        result="login successfully";
                    }
                }else{
                    result="no this teacher name.";
                }
            }else {
                result="no userType";
            }
        }else{
            result="no this username";
        }
        out.print(result);
        out.flush();
        out.close();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
