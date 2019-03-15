package dao;


import daoService.StudentworkDaoService;
import entity.Student;
import entity.Studentwork;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentworkDao implements StudentworkDaoService {
    @Override
    public void addStudentwork(Studentwork studentwork) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(studentwork);
        transaction.commit();
    }

    @Override
    public void updateStudentwork(Studentwork studentwork) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(studentwork);
        transaction.commit();
    }

    @Override
    public int numOfStudentworks() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Studentwork as studentwork");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    @Override
    public Studentwork findStudentworkById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Studentwork studentwork=session.get(Studentwork.class,id);
        transaction.commit();
        if(studentwork==null){
            System.out.println("no studentwork of the id "+id);
            return studentwork;
        }
        return studentwork;
    }

    @Override
    public ArrayList<Studentwork> findStudentworkByCwid(String cwid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Studentwork as studentwork where studentwork.cwid='"+cwid+"'");
        List<Studentwork> list=hql.list();
        transaction.commit();
        return (ArrayList<Studentwork>)list;
    }

    @Override
    public ArrayList<Studentwork> findStudentworkByCwidAndSid(String cwid, String sid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Studentwork as studentwork where studentwork.cwid='"+cwid+"' and studentwork.sid='"+sid+"'");
        List<Studentwork> list=hql.list();
        transaction.commit();
        return (ArrayList<Studentwork>)list;
    }


    @Override
    public ArrayList<String> findStudentByCwid(String cwid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select studentwork.sid from entity.Studentwork as studentwork where studentwork.cwid='"+cwid+"'");
        List<String> list=hql.list();
        transaction.commit();
        return (ArrayList<String>)list;
    }

    @Override
    public Boolean ifStudentWorkExists(String cwid, String sid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Studentwork as studentwork where studentwork.cwid='"+cwid+"' and studentwork.sid='"+sid+"'");
        List<Studentwork> list=hql.list();
        transaction.commit();
        if(list.size()!=0){
            return true;
        }
        return false;
    }

    /*
    public static void main(String[] args) {
        ApplicationContext appliationContext = new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        StudentworkDaoService studentworkDaoService = (StudentworkDaoService) appliationContext.getBean("StudentworkDaoService");

        Studentwork studentwork=new Studentwork();
        studentwork.setId("sw1");
        studentwork.setFile_url("D:\\Homework\\J2EE与中间件\\lab9\\mycourse\\out\\artifacts\\lab1_war_exploded\\my.bmp");
        studentwork.setSid("s1");
        studentwork.setCwid("cwork1");
        studentwork.setFilename("lab1 of s1");
       // studentworkDaoService.addStudentwork(studentwork);

        Studentwork studentworkGet=studentworkDaoService.findStudentworkById("sw1");
        System.out.println(studentworkGet.getFilename());
        ArrayList<Studentwork> studentworks=studentworkDaoService.findStudentworkByCwid("cwork1");
        System.out.println(studentworks.size()+"  "+studentworks.get(0).getFilename());
        ArrayList<String > students=studentworkDaoService.findStudentByCwid("cwork1");
        System.out.println(students.size()+"  "+students.get(0));
        System.out.println(studentworkDaoService.numOfStudentworks());
        System.out.println(studentworkDaoService.ifStudentWorkExists("cwork1","s1"));

       studentworkGet.setFile_url("D:\\Homework\\J2EE与中间件\\lab9\\mycourse\\out\\artifacts\\lab1_war_exploded\\pic.bmp");
       studentworkDaoService.updateStudentwork(studentworkGet);

    }*/
}
