package daoService;

import entity.Posting;

import java.util.ArrayList;

public interface PostingDaoService {
    public void addPosting(Posting posting);
    public int numOfPostings();
    public Posting findPosingById(String id);
    public ArrayList<Posting> findPostingByCid(String cid);
}
