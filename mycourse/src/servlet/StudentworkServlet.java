package servlet;

import com.google.gson.Gson;
import entity.Coursework;
import entity.Student;
import entity.Studentwork;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CourseworkService;
import utils.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/studentwork")
@MultipartConfig
public class StudentworkServlet  extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private CourseworkService courseworkService=(CourseworkService) serviceAppliationContext.getBean("CourseworkService");

    public StudentworkServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("studentwork servlet");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String operationType=request.getParameter("operationType");
        System.out.println(operationType);
        String cworkId=(String)session.getAttribute("cworkId");
        if(operationType!=null){
            if(operationType.equals("getStudentworkList")){
                ArrayList<Studentwork> studentworks=courseworkService.getStudentworkOfCoursework(cworkId);
                Gson gson=new Gson();
                String result=gson.toJson(studentworks);
                out.print(result);
                out.flush();
                out.close();
            }else if(operationType.equals("getStudentworkZip")){
                String servletPath = this.getServletContext().getRealPath("/");
                String zipFilename=courseworkService.zipStudentworks(cworkId,servletPath);
                out.print(zipFilename);
                out.flush();
                out.close();
            }else if(operationType.equals("getStudentworkOfStudent")){
                Student student=(Student)session.getAttribute("user");
                String sid=student.getId();
                ArrayList<Studentwork> studentworks=courseworkService.getStudentworkOfStudent(cworkId,sid);
                Gson gson=new Gson();
                String result=gson.toJson(studentworks);
                out.print(result);
                out.flush();
                out.close();
            }else{
                System.out.println("no such operation type");
            }
        }else{
            if(request.getPart("file")!=null){
                String cwid=(String)session.getAttribute("cworkId");
                Coursework coursework=courseworkService.getCoursework(cwid);
                String ddl=coursework.getDdl();
                System.out.print("upload studentwork");
                System.out.print(ddl);
                System.out.print(TimeUtil.ifCurrentAfterTargetTime(ddl));
                if (!TimeUtil.ifCurrentAfterTargetTime(ddl)) {
                    //ddl not past
                    Student student=(Student)session.getAttribute("user");
                    String sid=student.getId();
                    System.out.println("upload here");
                    Part part=request.getPart("file");
                    System.out.println(part.getSubmittedFileName());
                    String servletPath = this.getServletContext().getRealPath("/");
                    courseworkService.uploadStudentwork(part,cwid,sid,servletPath);

                }
            }else{
                System.out.println("student servlet2");
            }
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    }
