package dao;

import daoService.FollowpostDaoService;
import entity.Followpost;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;
import utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class FollowpostDao implements FollowpostDaoService {
    @Override
    public void addFollowpost(Followpost followpost) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(followpost);
        transaction.commit();
    }

    @Override
    public int numOfFollowposts() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql = session.createQuery("select count(*) from entity.Followpost as followpost");
        Number num = (Number) hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    @Override
    public Followpost findFollowPostById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Followpost followpost = session.get(Followpost.class, id);
        transaction.commit();
        if (followpost == null) {
            System.out.println("no followpost of the id " + id);
            return followpost;
        }
        return followpost;
    }

    @Override
    public ArrayList<Followpost> findFollowpostByPid(String pid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql = session.createQuery("from entity.Followpost as followpost where followpost.pid='" + pid + "'");
        List<Followpost> list = hql.list();
        transaction.commit();
        return (ArrayList<Followpost>) list;
    }
/*
    public static void main(String[] args) {
        ApplicationContext appliationContext = new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        FollowpostDaoService followpostDaoService = (FollowpostDaoService) appliationContext.getBean("FollowpostDaoService");

        Followpost followpost=new Followpost();
        followpost.setId("fp1");
        followpost.setPid("p1");
        followpost.setTime(TimeUtil.getCurrentTime());
        followpost.setFollower_name("betty");
        followpost.setContent("I am not for it.");
        //followpostDaoService.addFollowpost(followpost);

        System.out.println(followpostDaoService.numOfFollowposts());
        ArrayList<Followpost> followposts=followpostDaoService.findFollowpostByPid("p1");
        System.out.println(followposts.size()+" "+followposts.get(0).getContent());
        Followpost followpostGet=followpostDaoService.findFollowPostById("fp1");
        System.out.println(followpostGet.getTime());


    }*/
}