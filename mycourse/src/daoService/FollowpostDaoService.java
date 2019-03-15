package daoService;

import entity.Followpost;

import java.util.ArrayList;

public interface FollowpostDaoService {
    public void addFollowpost(Followpost followpost);
    public int numOfFollowposts();
    public Followpost findFollowPostById(String id);
    public ArrayList<Followpost> findFollowpostByPid(String pid);
}
