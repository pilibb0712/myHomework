package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.Student;
import entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.EmailService;
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
import java.rmi.StubNotFoundException;

@WebServlet("/register")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private  UserInfoService userInfoService = (UserInfoService) serviceAppliationContext.getBean("UserInfoService");
    private  EmailService emailService = (EmailService) serviceAppliationContext.getBean("EmailService");

    public RegisterServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operationType=request.getParameter("operation");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if(operationType.equals("validate")){
            JsonObject result=new JsonObject();
            String emailAddress=request.getParameter("email");
            System.out.println(emailAddress);
            Boolean ifEmailAddressExist=userInfoService.ifEmailExists(emailAddress);
            System.out.print(ifEmailAddressExist);
            if(ifEmailAddressExist){
                result.addProperty("returnType","email existed");
                out.print(result);
            }else{
                String validateNum=emailService.validateEmail(emailAddress);
                result.addProperty("returnType","sent");
                result.addProperty("validateNumber",validateNum);
                System.out.println(validateNum);
                out.print(result);
            }
        }else if(operationType.equals("register")){
            String userType=request.getParameter("userType");
            if(userType.equals("student")){
                String studentJson=request.getParameter("student");
                Gson gson=new Gson();
                Student student=gson.fromJson(studentJson,Student.class);
                System.out.println(student.getName());
                Boolean ifNameExist=userInfoService.ifNameExists(student.getName());
                if(ifNameExist){
                    out.print("The name has existed.");
                }else{
                    Student user=userInfoService.registerStudent(student);
                    session.setAttribute("userType","Student");
                    session.setAttribute("user",user);
                    out.print("register successsfully");
                }
            }else if(userType.equals("teacher")){
                String teacherJson=request.getParameter("teacher");
                Gson gson=new Gson();
                Teacher teacher=gson.fromJson(teacherJson,Teacher.class);
                Boolean ifNameExist=userInfoService.ifNameExists(teacher.getName());
                if(ifNameExist){
                    out.print("The name has existed.");
                }else{
                    Teacher user=userInfoService.registerTeacher(teacher);
                    session.setAttribute("userType","Teacher");
                    session.setAttribute("user",user);
                    out.print("register successsfully");
                }
            }else{
                out.print("do not know about user-type");
            }

        }else{
            out.print("do not know about operation type");
        }
        out.flush();
        out.close();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    }
