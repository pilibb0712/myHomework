package servlet;

import com.google.gson.Gson;
import entity.Student;
import entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.StudentStatsService;
import service.TeacherStatsService;

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


@WebServlet("/teacherStats")
@MultipartConfig
public class TeacherStatsServlet extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private TeacherStatsService teacherStatsService=(TeacherStatsService) serviceAppliationContext.getBean("TeacherStatsService");

    public TeacherStatsServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        Gson gson=new Gson();
        String result="";
        String operationType=request.getParameter("operationType");
        Teacher teacher=(Teacher)session.getAttribute("user");
        String tid=teacher.getId();
        if(operationType.equals("getTeacherCoursingNumOfTerm")){
            HashMap<String,Integer> map=teacherStatsService.getTeacherCoursingNumOfTerm(tid);
            result=gson.toJson(map);
        }else if(operationType.equals("getStudentNumOfTeacherCourse")){
            HashMap<String,Integer> map=teacherStatsService.getStudentNumOfTeacherCourse(tid);
            result=gson.toJson(map);
        }else if(operationType.equals("getStudentworkRecordOfCoursing")){
            String ccid=request.getParameter("coursingId");
            HashMap<String,Integer> map=teacherStatsService.getStudentworkRecordOfCoursing(ccid);
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
