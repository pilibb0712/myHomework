package servlet;

import com.google.gson.Gson;
import entity.Coursing;
import entity.Posting;
import entity.Student;
import entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CoursingService;
import service.PostService;

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

@WebServlet("/posting")
@MultipartConfig
public class PostingServlet  extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private PostService postService=(PostService) serviceAppliationContext.getBean("PostService");
    private CoursingService coursingService=(CoursingService) serviceAppliationContext.getBean("CoursingService");

    public PostingServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String operationType=request.getParameter("operationType");
        System.out.println(operationType);
        if(operationType.equals("createPosting")){
            String userType=(String)session.getAttribute("userType");
            String cid="";
            String posterName="";
            if(session.getAttribute("cid")==null){
                String ccid=(String)session.getAttribute("ccid");
                Coursing coursing=coursingService.getCoursingOfId(ccid);
                cid=coursing.getCid();
            }else{
                cid=(String)session.getAttribute("cid");
            }
            if(userType.equals("Teacher")) {
                Teacher teacher = (Teacher) session.getAttribute("user");
                posterName=teacher.getName();
            }else{
                Student student=(Student)session.getAttribute("user");
                posterName=student.getName();
            }
            String topic=request.getParameter("postTopic");
            String content=request.getParameter("postContent");
            Posting posting=new Posting();
            posting.setPoster_name(posterName);
            posting.setTopic(topic);
            posting.setContent(content);
            posting.setCid(cid);
            postService.createPosting(posting);
            out.print("create posting successfully");
            out.flush();
            out.close();
        }else if(operationType.equals("getPostingList")){
            String cid="";
            if(session.getAttribute("cid")==null){
                String ccid=(String)session.getAttribute("ccid");
                Coursing coursing=coursingService.getCoursingOfId(ccid);
                cid=coursing.getCid();
            }else{
                cid=(String)session.getAttribute("cid");
            }
            ArrayList<Posting> postings=postService.getPostingOfCourse(cid);
            Gson gson=new Gson();
            String result=gson.toJson(postings);
            out.print(result);
            out.flush();
            out.close();
        }else{

        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    }
