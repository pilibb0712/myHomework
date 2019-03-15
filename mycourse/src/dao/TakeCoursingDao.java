package dao;


import daoService.TakeCoursingDaoService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class TakeCoursingDao implements TakeCoursingDaoService {
    @Override
    public void addTakenCoursing(String ccid, String sid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql="insert into coursing_taken(ccid, sid) values('"+ccid+"','"+sid+"')";
        NativeQuery nativeQuery=session.createNativeQuery(sql);
        nativeQuery.executeUpdate();
        transaction.commit();
    }

    /*@Override
    public void addTakenCoursings(ArrayList<String[]> takens) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for(int i=0;i<=takens.size()-1;i++){
            String[] taken=takens.get(i);
            String ccid=taken[0];
            String sid=taken[1];
            String sql="insert into coursing_taken(ccid, sid) values('"+ccid+"','"+sid+"')";
            NativeQuery nativeQuery=session.createNativeQuery(sql);
            nativeQuery.executeUpdate();
        }
        transaction.commit();
    }
*/
    @Override
    public ArrayList<String> findTakenCoursingBySid(String sid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql="select ccid from coursing_taken where sid='"+sid+"'";
        NativeQuery nativeQuery=session.createNativeQuery(sql);
        List<String> list=nativeQuery.list();
        transaction.commit();
        return (ArrayList<String>)list;
    }

    @Override
    public Boolean ifTookCoursingByStudent(String ccid, String sid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql="select sid from coursing_taken where ccid='"+ccid+"' and sid='"+sid+"'";
        NativeQuery nativeQuery=session.createNativeQuery(sql);
        String result=(String)nativeQuery.uniqueResult();
        transaction.commit();
        if(result==null){
            return false;
        }
        return true;
    }

    @Override
    public void deleteTakenCoursing(String ccid, String sid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql="delete from coursing_taken where ccid='"+ccid+"' and sid='"+sid+"'";
        NativeQuery nativeQuery=session.createNativeQuery(sql);
        nativeQuery.executeUpdate();
        transaction.commit();
    }

    @Override
    public ArrayList<String> findTakenStudentsOfCoursing(String ccid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql="select sid from coursing_taken where ccid='"+ccid+"'";
        NativeQuery nativeQuery=session.createNativeQuery(sql);
        List<String> list=nativeQuery.list();
        transaction.commit();
        return (ArrayList<String>)list;
    }
/*
    public static void main(String[] args){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        TakeCoursingDaoService takeCoursingDaoService=(TakeCoursingDaoService)appliationContext.getBean("TakeCoursingDaoService");
        takeCoursingDaoService.addTakenCoursing("cc1","s1");
        takeCoursingDaoService.addTakenCoursing("cc1","s2");
        takeCoursingDaoService.addTakenCoursing("cc2","s1");
        takeCoursingDaoService.addTakenCoursing("cc2","s2");

        System.out.println(takeCoursingDaoService.ifTookCoursingByStudent("cc1","s1"));
        System.out.println(takeCoursingDaoService.ifTookCoursingByStudent("cc2","s3"));


        ArrayList<String> takes=takeCoursingDaoService.findTakenCoursingBySid("s1");
        for(int i=0;i<=takes.size()-1;i++){
            System.out.println(takes.get(i));
        }

        ArrayList<String> students=takeCoursingDaoService.findTakenStudentsOfCoursing("cc1");
        for(int i=0;i<=students.size()-1;i++){
            System.out.println(students.get(i));
        }

        takeCoursingDaoService.deleteTakenCoursing("cc1","s1");
    }
    */
}
