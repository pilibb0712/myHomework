package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Course;
import entity.Coursing;
import entity.Student;
import entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.*;
import utils.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/coursing")
@MultipartConfig
public class CoursingServlet extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private CoursingService coursingService=(CoursingService) serviceAppliationContext.getBean("CoursingService");
    private CourseService courseService=(CourseService) serviceAppliationContext.getBean("CourseService");
    private EmailService emailService = (EmailService) serviceAppliationContext.getBean("EmailService");
    private UserInfoService userInfoService = (UserInfoService) serviceAppliationContext.getBean("UserInfoService");
    private TakeCoursingService takeCoursingService=(TakeCoursingService) serviceAppliationContext.getBean("TakeCoursingService");


    public CoursingServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String operationType=request.getParameter("operationType");
        System.out.println(operationType);
        if(operationType!=null){
        if(operationType.equals("createCoursing")){
            String cid=(String)session.getAttribute("cid");
            Course course=courseService.getCourse(cid);
            if(course.isChecked()){
            String term=request.getParameter("term");
            String startTime=request.getParameter("startTime");
            String limitNum=request.getParameter("limitNum");
            String classNum=request.getParameter("classNum");
            Coursing coursing=new Coursing();
            coursing.setCid(cid);
            coursing.setLimit_num(Integer.parseInt(limitNum));
            coursing.setClass_num(classNum);
            coursing.setTerm(term);
            coursing.setStart_time(TimeUtil.getTimeOfDay(startTime));
            coursingService.createCoursing(coursing);
            out.print("create coursing successfully");
            out.flush();
            out.close();
            }else{
                //前后端双层检查
                out.print("The course is not checked, can not release.");
                out.flush();
                out.close();
            }
        }else if(operationType.equals("setCoursingId")){
            String ccid=request.getParameter("coursingId");
            session.setAttribute("ccid",ccid);
            out.print("set coursing successfully");
            out.flush();
            out.close();
        }else if(operationType.equals("getCoursing")){
            String ccid=(String)session.getAttribute("ccid");
            System.out.println(session.getAttribute("ccid"));
            takeCoursingService.updateAllCoursingStateChooseToTake();
            Coursing coursing=coursingService.getCoursingOfId(ccid);
            if(coursing==null){
                out.print("this coursing does not exist");
                out.flush();
                out.close();
            }else {
                String cid=coursing.getCid();
                Course course=courseService.getCourse(cid);
                String courseContent=course.getContent();
                String tid=course.getTid();
                Teacher teacherOfCourse=userInfoService.getTeacherById(tid);
                String tname=teacherOfCourse.getName();
                Gson gson=new Gson();
                String coursingStr=gson.toJson(coursing);
                JsonParser jsonParser = new JsonParser();
                JsonElement coursingElement = jsonParser.parse(coursingStr);
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("content",courseContent);
                jsonObject.addProperty("coursing",coursingStr);
                jsonObject.addProperty("teacher",tname);
                if(session.getAttribute("userType").equals("Student")){
                    Student student=(Student)session.getAttribute("user");
                    String sid=student.getId();
                    Boolean ifStudentChooseOfTakeCoursing=takeCoursingService.ifChoosedOrTaken(ccid,sid);
                    jsonObject.addProperty("ifChooseOrTaken",ifStudentChooseOfTakeCoursing);
                }
                out.println(jsonObject);
                out.flush();
                out.close();
            }
        }else if(operationType.equals("checkCoursing")){
            String ccid=(String)session.getAttribute("ccid");
            coursingService.checkCoursing(ccid);
            out.print("check coursing successfully");
            out.flush();
            out.close();
        }else if(operationType.equals("chooseCoursing")){
            String ccid=(String)session.getAttribute("ccid");
            Student student=(Student)session.getAttribute("user");
            String sid=student.getId();
            String result=takeCoursingService.chooseCoursing(ccid,sid);
            out.print(result);
            out.flush();
            out.close();
        }else if(operationType.equals("cancelCoursing")){
            String ccid=(String)session.getAttribute("ccid");
            Student student=(Student)session.getAttribute("user");
            String sid=student.getId();
            takeCoursingService.cancelCoursing(ccid,sid);
            out.print("cancel coursing successfully");
            out.flush();
            out.close();
        }else if(operationType.equals("emailToAll")){
            String ccid=(String)session.getAttribute("ccid");
            String topic=request.getParameter("emailTopic");
            String content=request.getParameter("emailContent");
            emailService.sendEmailToAllStudents(ccid,topic,content);
            out.print("email successfully");
            out.flush();
            out.close();
        }else if(operationType.equals("endCoursing")){
            String ccid=(String)session.getAttribute("ccid");
            coursingService.endCoursing(ccid);
            out.print("end coursing successfully");
            out.flush();
            out.close();
        }else{
            out.print("no such opration type: "+operationType);
            out.flush();
            out.close();
        }
        }else{
            if(request.getPart("file")!=null){
                System.out.println("upload here");
                String ccid=(String)session.getAttribute("ccid");
                Part part=request.getPart("file");
                System.out.println(part.getSubmittedFileName());
                String servletPath = this.getServletContext().getRealPath("/");
                coursingService.uploadScoreRecordOfCoursing(part,ccid,servletPath);
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
