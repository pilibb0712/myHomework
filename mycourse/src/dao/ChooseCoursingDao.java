package dao;

import daoService.ChooseCoursingDaoService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ChooseCoursingDao implements ChooseCoursingDaoService {
    @Override
    public void addChooseCoursing(String ccid, String sid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql="insert into coursing_choose(ccid, sid) values('"+ccid+"','"+sid+"')";
        NativeQuery nativeQuery=session.createNativeQuery(sql);
        nativeQuery.executeUpdate();
        transaction.commit();
    }

    @Override
    public void deleteChooseCoursing(String ccid, String sid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql="delete from coursing_choose where ccid='"+ccid+"' and sid='"+sid+"'";
        NativeQuery nativeQuery=session.createNativeQuery(sql);
        nativeQuery.executeUpdate();
        transaction.commit();
    }

    @Override
    public ArrayList<String> findChoosenStudentsOfCoursing(String ccid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql="select sid from coursing_choose where ccid='"+ccid+"'";
        NativeQuery nativeQuery=session.createNativeQuery(sql);
        List<String> list=nativeQuery.list();
        transaction.commit();
        return (ArrayList<String>)list;
    }

    @Override
    public Boolean ifChoosedCoursingByStudent(String ccid, String sid) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql="select sid from coursing_choose where ccid='"+ccid+"' and sid='"+sid+"'";
        NativeQuery nativeQuery=session.createNativeQuery(sql);
        String result=(String)nativeQuery.uniqueResult();
        transaction.commit();
        if(result==null){
            return false;
        }
        return true;
    }

    /*
    public static void main(String[] args){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        ChooseCoursingDaoService chooseCoursingDaoService=(ChooseCoursingDaoService)appliationContext.getBean("ChooseCoursingDaoService");
        chooseCoursingDaoService.addChooseCoursing("cc1","s1");
        chooseCoursingDaoService.addChooseCoursing("cc1","s2");
        chooseCoursingDaoService.addChooseCoursing("cc1","s3");
        chooseCoursingDaoService.addChooseCoursing("cc2","s1");
        chooseCoursingDaoService.addChooseCoursing("cc2","s2");

        System.out.println(chooseCoursingDaoService.ifChoosedCoursingByStudent("cc1","s1"));
        System.out.println(chooseCoursingDaoService.ifChoosedCoursingByStudent("cc2","s3"));

       chooseCoursingDaoService.deleteChooseCoursing("cc1","s3");
        ArrayList<String> chooses=chooseCoursingDaoService.findChoosenStudentsOfCoursing("cc1");
        for(int i=0;i<=chooses.size()-1;i++){
            System.out.println(chooses.get(i));
        }
    }
    */
}
