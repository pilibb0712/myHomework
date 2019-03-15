package dao;


import daoService.CourseDaoService;
import entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDao implements CourseDaoService{
    @Override
    public void addCourse(Course course) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(course);
        transaction.commit();
    }

    @Override
    public Course findCourseById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Course course=session.get(Course.class,id);
        transaction.commit();
        if(course==null){
            System.out.println("no course of the id "+id);
            return course;
        }
        return course;
    }

    @Override
    public int numOfCourses() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Course as course");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    @Override
    public ArrayList<Course> findCourseByTid(String tid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Course as course where course.tid='"+tid+"'");
        List<Course> list=hql.list();
        transaction.commit();
        return (ArrayList<Course>)list;
    }

    @Override
    public ArrayList<String> findCourseIdByTid(String tid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select course.id from entity.Course as course where course.tid='"+tid+"'");
        List<String> list=hql.list();
        transaction.commit();
        return (ArrayList<String>)list;
    }

    @Override
    public ArrayList<Course> findAllCourse() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaQuery<Course> criteria = session.getCriteriaBuilder().createQuery(Course.class);
        criteria.from(Course.class);
        List<Course> result = session.createQuery(criteria).getResultList();
        transaction.commit();
        return (ArrayList<Course>)result;
    }

    @Override
    public void updateCourse(Course course) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(course);
        transaction.commit();
    }


    /*public static void main(String[] args){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        CourseDaoService courseDaoService=(CourseDaoService)appliationContext.getBean("CourseDaoService");

        System.out.println(courseDaoService.numOfCourses());
        Course course=new Course();
        course.setId("c1");
        course.setChecked(false);
        course.setName("J2ee and middleware");
        course.setContent("teach servlet, ejb, spring, hibernate, jpa and etc");
        course.setTid("t1");
        courseDaoService.addCourse(course);

        Course course2=new Course();
        course2.setId("c2");
        course2.setChecked(false);
        course2.setName("linux design");
        course2.setContent("teach basic of linux");
        course2.setTid("t2");
        courseDaoService.addCourse(course2);

        ArrayList<Course> courses=courseDaoService.findAllCourse();
        System.out.println(courses.get(0).getName()+"  "+courses.get(1).getName());

        Course courseGet=courseDaoService.findCourseById("c1");
        System.out.println(courseGet.getName());

        ArrayList<Course> courses1=courseDaoService.findCourseByTid("t1");
        System.out.println(courses1.size()+"  "+courses1.get(0).getName());

        ArrayList<String> courses2=courseDaoService.findCourseIdByTid("t2");
        System.out.println(courses2.size()+"  "+courses2.get(0));

        courseGet.setChecked(true);
        courseDaoService.updateCourse(courseGet);

        Course course3=new Course();
        course3.setId("c3");
        course3.setChecked(false);
        course3.setName("software structure");
        course3.setContent("vector mode");
        course3.setTid("t3");
        courseDaoService.addCourse(course3);

    }*/
}
