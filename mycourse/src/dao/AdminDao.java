package dao;

import daoService.AdminDaoService;
import entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import java.util.List;

@Repository
public class AdminDao implements AdminDaoService {
    @Override
    public Boolean ifAdminNameExist(String name) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Admin as admin where admin.name='"+name+"'");
        Admin admin=(Admin)hql.uniqueResult();
        transaction.commit();
        if(admin==null){
            System.out.println("no admin of the name "+name);
            return false;
        }
        return true;
    }

    @Override
    public Admin findAdminByNameAndPw(String name, String password) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Admin as admin where admin.name='"+name+"' and admin.password='"+password+"'");
        Admin admin=(Admin)hql.uniqueResult();
        transaction.commit();
        return admin;
    }

    @Override
    public int numOfAdmin() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Admin as admin");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    /*
    public static void main(String[] args){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        AdminDaoService adminDaoService=(AdminDaoService)appliationContext.getBean("AdminDaoService");
        System.out.println(adminDaoService.ifAdminNameExist("beibei"));
        Admin admin=adminDaoService.findAdminByNameAndPw("beibei","Bb199861317");
        System.out.println(admin.getId()+" "+admin.getName()+" "+admin.getPassword());
        System.out.println(adminDaoService.numOfAdmin());
    }
    */
}
