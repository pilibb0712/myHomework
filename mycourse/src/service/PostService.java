package service;

import entity.Followpost;
import entity.Posting;
import javafx.geometry.Pos;

import java.util.ArrayList;

public interface PostService {
    public void createPosting(Posting posting);
    public void createFollowpost(Followpost followpost);
    public ArrayList<Posting> getPostingOfCourse(String cid);
    public Posting getPosting(String pid);
    public ArrayList<Followpost> getFollowpostOfPosting(String pid);
    public Followpost getFllowpost(String fpid);
    //这个方法不一定会用到，因为打开主楼就会显示所有跟贴了

}
