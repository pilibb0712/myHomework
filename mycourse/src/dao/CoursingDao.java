package dao;


import daoService.CoursingDaoService;
import entity.Coursing;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;
import utils.TypeUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class CoursingDao implements CoursingDaoService{

    @Override
    public void addCoursing(Coursing coursing) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(coursing);
        transaction.commit();
    }

    @Override
    public int numOfCoursings() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Coursing as coursing");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    @Override
    public Coursing findCouringById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Coursing coursing=session.get(Coursing.class,id);
        transaction.commit();
        if(coursing==null){
            System.out.println("no coursing of the id "+id);
            return coursing;
        }
        return coursing;
    }

    @Override
    public ArrayList<Coursing> findCouringByCid(String cid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Coursing as coursing where coursing.cid='"+cid+"'");
        List<Coursing> list=hql.list();
        transaction.commit();
        return (ArrayList<Coursing>)list;
    }

    @Override
    public ArrayList<Coursing> findCoursingByTerm(String term) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Coursing as coursing where coursing.term='"+term+"'");
        List<Coursing> list=hql.list();
        transaction.commit();
        return (ArrayList<Coursing>)list;
    }

    @Override
    public ArrayList<Coursing> findAllCoursing() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaQuery<Coursing> criteria = session.getCriteriaBuilder().createQuery(Coursing.class);
        criteria.from(Coursing.class);
        List<Coursing> result = session.createQuery(criteria).getResultList();
        transaction.commit();
        return (ArrayList<Coursing>)result;
    }

    @Override
    public void updateCoursing(Coursing coursing) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(coursing);
        transaction.commit();
    }

    @Override
    public ArrayList<String> findTermsOfAllCoursing() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select distinct coursing.term from entity.Coursing as coursing");
        List<String> list=hql.list();
        transaction.commit();
        return (ArrayList<String>)list;
    }

    @Override
    public int findCoursingNumOfTerm(String term) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Coursing as coursing where coursing.term='"+term+"'");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }
/*
    public static void main(String[] args){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        CoursingDaoService coursingDaoService=(CoursingDaoService)appliationContext.getBean("CoursingDaoService");

        Coursing coursing=new Coursing();
        coursing.setId("cc1");
        coursing.setCid("c1");
        coursing.setLimit_num(50);
        coursing.setTake_num(50);
        coursing.setState(TypeUtil.state.CHECK.toString());
        coursing.setTerm("2016 spring");
        coursing.setClass_num("1");
        coursing.setLimit_num(50);
        coursing.setScorefile_name("pic.bmp");

        Coursing coursing2=new Coursing();
        coursing2.setId("cc2");
        coursing2.setCid("c1");
        coursing2.setLimit_num(50);
        coursing2.setTake_num(50);
        coursing2.setState(TypeUtil.state.CHECK.toString());
        coursing2.setTerm("2016 autumn");
        coursing2.setClass_num("2");
        coursing2.setLimit_num(50);
        coursing2.setScorefile_name("pic.bmp");

       // coursingDaoService.addCoursing(coursing);
       // coursingDaoService.addCoursing(coursing2);

        System.out.println(coursingDaoService.numOfCoursings());
        Coursing coursingGet=coursingDaoService.findCouringById("cc1");
        System.out.println(coursingGet.getTerm());
        ArrayList<Coursing> coursings=coursingDaoService.findCoursingByTerm("2016 spring");
        System.out.println(coursings.size()+" "+coursings.get(0).getId());
        ArrayList<Coursing> coursings2=coursingDaoService.findCouringByCid("c1");
        System.out.println(coursings2.size()+" "+coursings2.get(1).getId());
        ArrayList<Coursing> coursings3=coursingDaoService.findAllCoursing();
        System.out.println(coursings3.size()+" "+coursings3.get(0).getId()+" "+coursings3.get(1).getId());
        ArrayList<String> terms=coursingDaoService.findTermsOfAllCoursing();
        System.out.println(terms.size()+" "+terms.get(0)+" "+terms.get(1));
        System.out.println(coursingDaoService.findCoursingNumOfTerm("2016 spring"));

        coursingGet.setState(TypeUtil.state.CHOOSE.toString());
        coursingDaoService.updateCoursing(coursingGet);
    }*/
}
