package dao;

import daoService.FollowpostDaoService;
import daoService.PostingDaoService;
import entity.Followpost;
import entity.Posting;
import javafx.geometry.Pos;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;
import utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class PostingDao implements PostingDaoService {
    @Override
    public void addPosting(Posting posting) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(posting);
        transaction.commit();
    }

    @Override
    public int numOfPostings() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Posting as posting");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    @Override
    public Posting findPosingById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Posting posting=session.get(Posting.class,id);
        transaction.commit();
        if(posting==null){
            System.out.println("no posting of the id "+id);
            return posting;
        }
        return posting;
    }

    @Override
    public ArrayList<Posting> findPostingByCid(String cid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Posting as posting where posting.cid='"+cid+"'");
        List<Posting> list=hql.list();
        transaction.commit();
        return (ArrayList<Posting>)list;
    }

    /*
    public static void main(String[] args) {
        ApplicationContext appliationContext = new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        PostingDaoService postingDaoService = (PostingDaoService) appliationContext.getBean("PostingDaoService");

        Posting posting=new Posting();
        posting.setId("p1");
        posting.setCid("c1");
        posting.setTime(TimeUtil.getCurrentTime());
        posting.setContent("what do you want to know about lab1");
        posting.setTopic("lab1 Q&A");
        posting.setPoster_name("tinny");
        postingDaoService.addPosting(posting);

        System.out.println(postingDaoService.numOfPostings());
        ArrayList<Posting> postings=postingDaoService.findPostingByCid("c1");
        System.out.println(postings.size()+" "+postings.get(0).getContent());
        Posting postingGet=postingDaoService.findPosingById("p1");
        System.out.println(postingGet.getTime());

    }*/
}
