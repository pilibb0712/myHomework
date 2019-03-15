package servlet;

import com.google.gson.Gson;
import entity.Coursing;
import entity.Student;
import entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CourseService;
import service.CoursingService;
import service.TakeCoursingService;
import utils.TimeUtil;

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

@WebServlet("/coursingList")
@MultipartConfig
public class CoursingListServlet  extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private CoursingService coursingService=(CoursingService) serviceAppliationContext.getBean("CoursingService");
    private TakeCoursingService takeCoursingService=(TakeCoursingService) serviceAppliationContext.getBean("TakeCoursingService");


    public CoursingListServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String operationType=request.getParameter("operationType");
        Gson gson=new Gson();
        String result="";
        if(operationType.equals("getTeacherCoursing")) {
            Teacher teacher=(Teacher)session.getAttribute("user");
            String tid=teacher.getId();
            ArrayList<Coursing> coursings=coursingService.getCoursingOfTid(tid);
            result=gson.toJson(coursings);
            System.out.println(result);
        }else if(operationType.equals("getAllCoursing")){
            ArrayList<Coursing> coursings=coursingService.getAllCoursing();
            result=gson.toJson(coursings);
        }else if(operationType.equals("getCurrentTermCoursing")){
            ArrayList<Coursing> coursings=coursingService.getCoursingOfTerm(TimeUtil.getCurrentTerm());
            result=gson.toJson(coursings);
        }else if(operationType.equals("getMyCoursing")){
            Student student=(Student)session.getAttribute("user");
            String sid=student.getId();
            takeCoursingService.updateAllCoursingStateChooseToTake();
            ArrayList<Coursing> coursings=takeCoursingService.getCoursingOfSid(sid);
            result=gson.toJson(coursings);
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
