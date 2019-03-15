package servlet;

import com.google.gson.Gson;
import entity.Admin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AdminStatsService;
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
import java.util.HashMap;


@WebServlet("/adminStats")
@MultipartConfig
public class AdminStatsServlet extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private AdminStatsService adminStatsService=(AdminStatsService) serviceAppliationContext.getBean("AdminStatsService");

    public AdminStatsServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        Gson gson=new Gson();
        String result="";
        String operationType=request.getParameter("operationType");
        if(operationType.equals("getUserNumOfType")){
            HashMap<String,Integer> map=adminStatsService.getUserNumOfType();
            result=gson.toJson(map);
        }else if(operationType.equals("getStudentNumOfGrade")){
            HashMap<String,Integer> map=adminStatsService.getStudentNumOfGrade();
            result=gson.toJson(map);
        }else if(operationType.equals("getCoursingNumOfEachTerm")){
            HashMap<String,Integer> map=adminStatsService.getCoursingNumOfEachTerm();
            result=gson.toJson(map);
        }else if(operationType.equals("getAllCourseTakenNum")){
            HashMap<String,Integer> map=adminStatsService.getAllCourseTakenNum();
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
