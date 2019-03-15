package dao;

import daoService.CoursewareDaoService;
import entity.Course;
import entity.Courseware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CoursewareDao implements CoursewareDaoService {
    @Override
    public void addCourseware(Courseware courseware) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(courseware);
        transaction.commit();
    }

    @Override
    public int numOfCoursewares() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Courseware as courseware");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    @Override
    public Courseware findCoursewareById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Courseware courseware=session.get(Courseware.class,id);
        transaction.commit();
        if(courseware==null){
            System.out.println("no courseware of the id "+id);
            return courseware;
        }
        return courseware;
    }

    @Override
    public ArrayList<Courseware> findCoursewareByCid(String cid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Courseware as courseware where courseware.cid='"+cid+"'");
        List<Courseware> list=hql.list();
        transaction.commit();
        return (ArrayList<Courseware>)list;
    }
/*
    public static void main(String[] args) {
        ApplicationContext appliationContext = new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        CoursewareDaoService coursewareDaoService = (CoursewareDaoService) appliationContext.getBean("CoursewareDaoService");

        Courseware courseware=new Courseware();
        courseware.setFilename("pic.bmp");
        courseware.setCid("c1");
        courseware.setId("cware1");
        courseware.setFile_url("D:\\Homework\\J2EE与中间件\\lab9\\mycourse\\out\\artifacts\\lab1_war_exploded\\pic.bmp");

        coursewareDaoService.addCourseware(courseware);

        System.out.println(coursewareDaoService.numOfCoursewares());
        Courseware coursewareGet=coursewareDaoService.findCoursewareById("cware1");
        System.out.println(coursewareGet.getFilename());
        System.out.println(coursewareGet.getFile_url());

        ArrayList<Courseware> coursewares=coursewareDaoService.findCoursewareByCid("c1");
        System.out.println(coursewares.size()+"  "+coursewares.get(0).getFilename());

    }
    */
    }
