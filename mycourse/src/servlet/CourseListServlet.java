package servlet;

import com.google.gson.Gson;
import entity.Course;
import entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/courseList")
@MultipartConfig
public class CourseListServlet extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private CourseService courseService=(CourseService) serviceAppliationContext.getBean("CourseService");

    public CourseListServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String operationType=request.getParameter("operationType");
        String result="";
        if(operationType.equals("getTeacherCourse")) {
            Teacher teacher = (Teacher) session.getAttribute("user");
            String tid = teacher.getId();
            ArrayList<Course> courses = courseService.getCoursesOfTeacher(tid);
            Gson gson = new Gson();
            result = gson.toJson(courses);
            System.out.println(result);

        }else if(operationType.equals("getAllCourse")){
            ArrayList<Course> courses=courseService.getAllCourse();
            Gson gson = new Gson();
            result = gson.toJson(courses);
            System.out.println(result);
        }else{
            result="no operationType: "+operationType;
        }
        out.println(result);
        out.flush();
        out.close();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    }
