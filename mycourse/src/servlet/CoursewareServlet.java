package servlet;

import com.google.gson.Gson;
import entity.Courseware;
import entity.Coursing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CourseService;
import service.CoursewareService;
import service.CoursingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/courseware")
@MultipartConfig
public class CoursewareServlet extends HttpServlet {
    private  ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private CoursewareService coursewareService=(CoursewareService) serviceAppliationContext.getBean("CoursewareService");
    private CoursingService coursingService=(CoursingService) serviceAppliationContext.getBean("CoursingService");


    public CoursewareServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("courseware servlet");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String operationType=request.getParameter("operationType");
        System.out.println(operationType);

        if(operationType!=null){
            if(operationType.equals("getCoursewareList")){
            String cid="";
            if(session.getAttribute("cid")==null){
                String ccid=(String)session.getAttribute("ccid");
                Coursing coursing=coursingService.getCoursingOfId(ccid);
                cid=coursing.getCid();
            }else{
                cid=(String)session.getAttribute("cid");
            }
            System.out.println("courseware servlet1");
            ArrayList<Courseware> coursewares=coursewareService.getCoursewareOfCourse(cid);
            Gson gson=new Gson();
            String result=gson.toJson(coursewares);
            out.print(result);
            out.flush();
            out.close();
            }else{
                System.out.println("courseware servlet0");
            }
        }else{
            if(request.getPart("file")!=null){
                String cid=(String)session.getAttribute("cid");
                System.out.println("upload here");
                Part part=request.getPart("file");
                System.out.println(part.getSubmittedFileName());
                String servletPath = this.getServletContext().getRealPath("/");
                coursewareService.uploadCourseware(part,cid,servletPath);
            }else{
                System.out.println("courseware servlet2");
            }
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
