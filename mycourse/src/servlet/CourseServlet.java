package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.Course;
import entity.Teacher;
import netscape.javascript.JSObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CourseService;
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

@WebServlet("/course")
@MultipartConfig
public class CourseServlet extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private CourseService courseService=(CourseService) serviceAppliationContext.getBean("CourseService");
    private UserInfoService userInfoService = (UserInfoService) serviceAppliationContext.getBean("UserInfoService");

    public CourseServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String operationType=request.getParameter("operationType");
        if(operationType.equals("createCourse")){
            Teacher teacher=(Teacher)session.getAttribute("user");
            String courseName=request.getParameter("courseName");
            String courseContent=request.getParameter("courseContent");
            String tid=teacher.getId();
            Course course=new Course();
            course.setName(courseName);
            course.setContent(courseContent);
            course.setTid(tid);
            courseService.createCourse(course);
            out.print("create course successfully");
            out.flush();
            out.close();
        }else if(operationType.equals("setCourseId")){
            String cid=request.getParameter("courseId");
            session.setAttribute("cid",cid);
            out.print("set course successfully");
            out.flush();
            out.close();
        }else if(operationType.equals("getCourse")){
            String cid=(String)session.getAttribute("cid");
            System.out.println(session.getAttribute("cid"));
            Course course=courseService.getCourse(cid);
            if(course==null){
                out.print("this course does not exist");
                out.flush();
                out.close();
            }else{
                String tid=course.getTid();
                Teacher teacherOfCourse=userInfoService.getTeacherById(tid);
                String tname=teacherOfCourse.getName();
                Gson gson=new Gson();
                String courseStr=gson.toJson(course);
                JsonObject result=new JsonObject();
                result.addProperty("teacher",tname);
                result.addProperty("course",courseStr);
                out.println(result);
                out.flush();
                out.close();
            }
        }else if(operationType.equals("checkCourse")){
            String cid=(String)session.getAttribute("cid");
            courseService.checkCourse(cid);
            out.print("check course successfully");
            out.flush();
            out.close();
        }else{
            out.print("no such opration type: "+operationType);
            out.flush();
            out.close();
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
