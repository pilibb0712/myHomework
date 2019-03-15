package servlet;

import com.google.gson.Gson;
import entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.StudentStatsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


@WebServlet("/studentStats")
@MultipartConfig
public class StudentStatsServlet extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private StudentStatsService studentStatsService=(StudentStatsService) serviceAppliationContext.getBean("StudentStatsService");

    public StudentStatsServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        Gson gson=new Gson();
        String result="";
        String operationType=request.getParameter("operationType");
        Student student=(Student)session.getAttribute("user");
        String sid=student.getId();
        if(operationType.equals("getStudentCourseNumOfTerm")){
            HashMap<String,Integer> map=studentStatsService.getStudentCourseNumOfTermRecord(sid);
            result=gson.toJson(map);
        }else if(operationType.equals("getStudentCourseOfTeacher")){
            HashMap<String,Integer> map=studentStatsService.getStudentCourseOfTeacherRecord(sid);
            result=gson.toJson(map);
        }else if(operationType.equals("getStudentworkRecord")){
            HashMap<String,Integer> map=studentStatsService.getStudentworkRecord(sid);
            result=gson.toJson(map);
        }else{
            result="no this operation type: "+operationType;
        }
        out.print(result);
        out.flush();
        out.close();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
