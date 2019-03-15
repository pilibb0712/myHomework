package dao;

import daoService.CourseworkDaoService;
import entity.Course;
import entity.Coursework;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;
import utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseworkDao implements CourseworkDaoService {
    @Override
    public void addCoursework(Coursework coursework) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(coursework);
        transaction.commit();
    }

    @Override
    public int numOfCourseworks() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Coursework as coursework");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    @Override
    public Coursework findCourseworkById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Coursework coursework=session.get(Coursework.class,id);
        transaction.commit();
        if(coursework==null){
            System.out.println("no coursework of the id "+id);
            return coursework;
        }
        return coursework;
    }

    @Override
    public ArrayList<Coursework> findCourseworkByCcid(String ccid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Coursework as coursework where coursework.ccid='"+ccid+"'");
        List<Coursework> list=hql.list();
        transaction.commit();
        return (ArrayList<Coursework>)list;
    }

    @Override
    public void updateCoursework(Coursework coursework) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(coursework);
        transaction.commit();
    }

    /*
    public static void main(String[] args) {
        ApplicationContext appliationContext = new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        CourseworkDaoService courseworkDaoService = (CourseworkDaoService) appliationContext.getBean("CourseworkDaoService");

        Coursework coursework=new Coursework();
        coursework.setId("cwork1");
        coursework.setTime(TimeUtil.getCurrentTime());
        System.out.println(TimeUtil.getCurrentTime());
        coursework.setCcid("cc1");
        coursework.setTopic("lab1 of j2ee");
        coursework.setContent("describe your previous project");
        coursework.setDdl("2019-12-31 00:00:00");
        coursework.setMax_size("5M");

        courseworkDaoService.addCoursework(coursework);
        System.out.println(courseworkDaoService.numOfCourseworks());
        Coursework courseworkGet=courseworkDaoService.findCourseworkById("cwork1");
        System.out.println(courseworkGet.getTopic());
        ArrayList<Coursework> courseworks=courseworkDaoService.findCourseworkByCcid("cc1");
        System.out.println(courseworks.size()+"  "+courseworks.get(0).getContent());

        courseworkGet.setScorefile_name("pic.bmp");
        courseworkDaoService.updateCoursework(courseworkGet);

    }
    */
    }
