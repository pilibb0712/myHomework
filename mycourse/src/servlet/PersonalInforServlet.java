package servlet;

import com.google.gson.Gson;
import entity.Admin;
import entity.Student;
import entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/personalInfor")
@MultipartConfig
public class PersonalInforServlet  extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private UserInfoService userInfoService = (UserInfoService) serviceAppliationContext.getBean("UserInfoService");

    public PersonalInforServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String userType=(String)session.getAttribute("userType");
        String result="";
        Gson gson=new Gson();
        String operationType=request.getParameter("operationType");
        if(operationType!=null){
        if(operationType.equals("getUserInfor")){
        if (userType.equals("Admin")) {
            Admin admin=(Admin)session.getAttribute("user");
            result=gson.toJson(admin);
        }else if (userType.equals("Student")) {
            Student student=(Student)session.getAttribute("user");
            System.out.println(student.getName()+"   "+student.getPic_name());
            result=gson.toJson(student);
        } else if (userType.equals("Teacher")) {
            Teacher teacher=(Teacher) session.getAttribute("user");
            System.out.println(teacher.getName()+"   "+teacher.getPic_name());
            result=gson.toJson(teacher);
        } else {
            result="no userType";
        }
        }else if(operationType.equals("updateUserInfor")){
            if(userType.equals("Student")){
                Student student=(Student)session.getAttribute("user");
                String name=request.getParameter("userName");
                String grade=request.getParameter("userGrade");
                String snumber=request.getParameter("userSnumber");
                student.setName(name);
                student.setGrade(grade);
                student.setStudent_number(snumber);
                userInfoService.updateStudent(student);
                session.setAttribute("user",student);
                //个人信息会更新，而user在整个过程中一开始就设置好，并不会由界面触发刷新（都是通过session取而不是id）
                // 所以在更新完个人信息后也要更新这个session
                //要么一开始就不要保存user而只保存userId
                result="update successfully";
            }else if(userType.equals("Teacher")){
                Teacher teacher=(Teacher) session.getAttribute("user");
                String name=request.getParameter("userName");
                teacher.setName(name);
                userInfoService.updateTeacher(teacher);
                session.setAttribute("user",teacher);
                result="update successfully";
            }else{
                result="user type: "+userType+" cannot update infor.";
            }

        }else if(operationType.equals("cancelStudent")){
            Student student=(Student)session.getAttribute("user");
            String sid=student.getId();
            userInfoService.cancelStudent(sid);
            session.setAttribute("user",null);
            session.setAttribute("userType",null);
            result="cancel successfully";
        }else{
            result="no operationType: "+operationType;
        }
            out.print(result);
            out.flush();
            out.close();
        }else {
        if (request.getPart("file") != null) {
            System.out.println("upload here");
            Part part = request.getPart("file");
            System.out.println(part.getSubmittedFileName());
            String servletPath = this.getServletContext().getRealPath("/");
           if(userType.equals("Student")){
               Student student=(Student)session.getAttribute("user");
               userInfoService.uploadStudentPic(part,student.getId(),servletPath);
               String sid=student.getId();
               Student newStudent=userInfoService.getStudentById(sid);
               session.setAttribute("user",newStudent);
           }else if(userType.equals("Teacher")){
               Teacher teacher=(Teacher) session.getAttribute("user");
               userInfoService.uploadTeacherPic(part,teacher.getId(),servletPath);
               String tid=teacher.getId();
               Teacher newTeacher=userInfoService.getTeacherById(tid);
               session.setAttribute("user",newTeacher);
           }else{
               System.out.println("user type: "+userType+" cannot upload pic.");
           }
        } else {
            System.out.println("personalinfor servlet2");
        }
    }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    }
