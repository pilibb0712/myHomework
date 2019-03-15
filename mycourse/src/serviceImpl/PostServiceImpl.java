package serviceImpl;


import daoService.FollowpostDaoService;
import daoService.PostingDaoService;
import entity.Followpost;
import entity.Posting;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.PostService;
import utils.TimeUtil;

import java.util.ArrayList;

@Repository
public class PostServiceImpl implements PostService {
    private static PostingDaoService postingDaoService;
    private static FollowpostDaoService followpostDaoService;

    public PostServiceImpl(){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        postingDaoService=(PostingDaoService) appliationContext.getBean("PostingDaoService");
        followpostDaoService=(FollowpostDaoService)appliationContext.getBean("FollowpostDaoService");
    }
    @Override
    public void createPosting(Posting posting) {
        int num=postingDaoService.numOfPostings();
        num++;
        String id="p"+String.valueOf(num);
        posting.setId(id);
        String time= TimeUtil.getCurrentTime();
        posting.setTime(time);
        postingDaoService.addPosting(posting);

    }

    @Override
    public void createFollowpost(Followpost followpost) {
        int num=followpostDaoService.numOfFollowposts();
        num++;
        String id="fp"+String.valueOf(num);
        followpost.setId(id);
        String time= TimeUtil.getCurrentTime();
        followpost.setTime(time);
        followpostDaoService.addFollowpost(followpost);

    }

    /*
    public static void main(String args[]) {
        ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
        PostService postService = (PostService) serviceAppliationContext.getBean("PostService");

        Posting posting=new Posting();
        posting.setCid("c1");
        posting.setTime(TimeUtil.getCurrentTime());
        posting.setContent("what do you want to know about lab2");
        posting.setTopic("lab2 Q&A");
        posting.setPoster_name("tinny");
        postService.createPosting(posting);

        Followpost followpost=new Followpost();
        followpost.setPid("p1");
        followpost.setTime(TimeUtil.getCurrentTime());
        followpost.setFollower_name("cindy");
        followpost.setContent("I am for it.");
        postService.createFollowpost(followpost);
    }*/

        @Override
    public ArrayList<Posting> getPostingOfCourse(String cid) {
        return postingDaoService.findPostingByCid(cid);
    }

    @Override
    public Posting getPosting(String pid) {
        return postingDaoService.findPosingById(pid);
    }

    @Override
    public ArrayList<Followpost> getFollowpostOfPosting(String pid) {
        return followpostDaoService.findFollowpostByPid(pid);
    }

    @Override
    public Followpost getFllowpost(String fpid) {
        return followpostDaoService.findFollowPostById(fpid);
    }
}
