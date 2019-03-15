package utils;

import entity.*;
import javafx.geometry.Pos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        configuration.addAnnotatedClass(Admin.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Coursework.class);
        configuration.addAnnotatedClass(Courseware.class);
        configuration.addAnnotatedClass(Coursing.class);
        configuration.addAnnotatedClass(Followpost.class);
        configuration.addAnnotatedClass(Posting.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Studentwork.class);
        configuration.addAnnotatedClass(Teacher.class);

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Goods.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static Session getSession(){
        return getSessionFactory().getCurrentSession();
    }

}
