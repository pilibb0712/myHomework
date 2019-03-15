package servlet;

import com.google.gson.Gson;
import entity.Followpost;
import entity.Posting;
import entity.Student;
import entity.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

@WebServlet("/followPost")
@MultipartConfig
public class FollowPostServlet   extends HttpServlet {
    private ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
    private PostService postService = (PostService) serviceAppliationContext.getBean("PostService");

    public FollowPostServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String operationType = request.getParameter("operationType");
        if (operationType.equals("setPostingId")) {
            String postingId = request.getParameter("postingId");
            session.setAttribute("pid", postingId);
            out.print("set posting successfully");
            out.flush();
            out.close();
        }else if(operationType.equals("getPosting")){
            String pid=(String)session.getAttribute("pid");
            System.out.println(pid);
            Posting posting=postService.getPosting(pid);
            System.out.println(posting==null);
            System.out.println(posting.getId());
            Gson gson=new Gson();
            String result=gson.toJson(posting);
            out.print(result);
            out.flush();
            out.close();
        }else if(operationType.equals("createFollowPost")){
            String userType=(String)session.getAttribute("userType");
            String posterName="";
            String pid=(String)session.getAttribute("pid");
            String content=request.getParameter("postContent");
            if(userType.equals("Teacher")) {
                Teacher teacher = (Teacher) session.getAttribute("user");
                posterName=teacher.getName();
            }else{
                Student student=(Student)session.getAttribute("user");
                posterName=student.getName();
            }
            Followpost followpost=new Followpost();
            followpost.setContent(content);
            followpost.setPid(pid);
            followpost.setFollower_name(posterName);
            postService.createFollowpost(followpost);
            out.print("create posting successfully");
            out.flush();
            out.close();
        }else if(operationType.equals("getFollowPostList")){
            String pid=(String)session.getAttribute("pid");
            ArrayList<Followpost> followposts=postService.getFollowpostOfPosting(pid);
            Gson gson=new Gson();
            String result=gson.toJson(followposts);
            out.print(result);
            out.flush();
            out.close();
        }else{
            out.print("no such opration type: "+operationType);
            out.flush();
            out.close();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}