package servlet;

import com.google.gson.Gson;
import entity.Course;
import entity.Courseware;
import entity.Coursework;
import entity.Coursing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CourseService;
import service.CoursewareService;
import service.CourseworkService;
import utils.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/coursework")
@MultipartConfig
public class CourseworkServlet extends HttpServlet {
    private  ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private CourseworkService courseworkService=(CourseworkService) serviceAppliationContext.getBean("CourseworkService");

    public CourseworkServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("coursework servlet");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String operationType=request.getParameter("operationType");
        System.out.println(operationType);
        String ccid=(String)session.getAttribute("ccid");
        if(operationType!=null){
            if(operationType.equals("getCourseworkList")){
                System.out.println("coursework servlet1");
               ArrayList<Coursework> courseworks=courseworkService.getCourseworkOfCoursing(ccid);
                Gson gson=new Gson();
                String result=gson.toJson(courseworks);
                out.print(result);
                out.flush();
                out.close();
            }else if(operationType.equals("createCoursework")){
                String topic=request.getParameter("courseworkTopic");
                String content=request.getParameter("courseworkContent");
                String ddl=request.getParameter("ddl");
                Coursework coursework=new Coursework();
                coursework.setDdl(TimeUtil.getTimeOfDay(ddl));
               coursework.setContent(content);
               coursework.setTopic(topic);
               coursework.setCcid(ccid);
               courseworkService.createCoursework(coursework);
                out.print("create coursework successfully");
                out.flush();
                out.close();
            }else if(operationType.equals("setCourseworkId")){
                String cwid=request.getParameter("courseworkId");
                session.setAttribute("cworkId",cwid);
                out.print("set coursework successfully");
                out.flush();
                out.close();
            }else if(operationType.equals("getCoursework")){
                String cwid=(String)session.getAttribute("cworkId");
                Coursework coursework=courseworkService.getCoursework(cwid);
                Gson gson=new Gson();
                String result=gson.toJson(coursework);
                out.print(result);
                out.flush();
                out.close();
            }else{
                System.out.println("no such operation type");
            }
        } else{
            if(request.getPart("file")!=null){
            System.out.println("upload here");
            String cwid=(String)session.getAttribute("cworkId");
            Part part=request.getPart("file");
            System.out.println(part.getSubmittedFileName());
            String servletPath = this.getServletContext().getRealPath("/");
            courseworkService.uploadScoreRecordOfCoursework(part,cwid,servletPath);
        }else{
            System.out.println("courseware servlet2");
        }
        System.out.println("opration type is null");
    }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
