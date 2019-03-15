package dao;


import daoService.TeacherDaoService;
import entity.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;




public class TeacherDao implements TeacherDaoService {
    @Override
    public Boolean ifTeacherNameExist(String name) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Teacher as teacher where teacher.name='"+name+"'");
        Teacher teacher=(Teacher)hql.uniqueResult();
        transaction.commit();
        if(teacher==null){
            System.out.println("no teacher of the name "+name);
            return false;
        }
        return true;
    }

    @Override
    public Boolean ifTeacherEmailExist(String email) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Teacher as teacher where teacher.email='"+email+"'");
        Teacher teacher=(Teacher)hql.uniqueResult();
        transaction.commit();
        if(teacher==null){
            System.out.println("no teacher of the email "+email);
            return false;
        }
        return true;
    }

    @Override
    public Teacher findTeacherByNameAndPw(String name, String password) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Teacher as teacher where teacher.name='"+name+"' and teacher.password='"+password+"'");
        Teacher teacher=(Teacher) hql.uniqueResult();
        transaction.commit();
        return teacher;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(teacher);
        transaction.commit();
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(teacher);
        transaction.commit();
    }

    @Override
    public int numOfTeachers() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Teacher as teacher");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    @Override
    public Teacher findTeacherById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Teacher teacher=session.get(Teacher.class,id);
        transaction.commit();
        if(teacher==null){
            System.out.println("no teacher of the id "+id);
            return teacher;
        }
        return teacher;
    }

    @Override
    public Teacher findTeacherByName(String name) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Teacher as teacher where teacher.name='"+name+"'");
        Teacher teacher=(Teacher)hql.uniqueResult();
        transaction.commit();
        if(teacher==null){
            System.out.println("no teacher of the name "+name);
            return teacher;
        }
        return teacher;
    }
/*
    public static void main(String[] args) {
        ApplicationContext appliationContext = new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        TeacherDaoService teacherDaoService = (TeacherDaoService) appliationContext.getBean("TeacherDaoService");

        Teacher teacher1=new Teacher();
        teacher1.setId("t1");
        teacher1.setEmail("1292155475@163.com");
        teacher1.setName("tinny");
        teacher1.setPassword("1234");
        //teacherDaoService.addTeacher(teacher1);

        System.out.println(teacherDaoService.ifTeacherEmailExist("1292155474@163.com"));
        System.out.println(teacherDaoService.ifTeacherEmailExist("1292155475@163.com"));
        System.out.println(teacherDaoService.ifTeacherNameExist("tinny"));

        Teacher teacher=teacherDaoService.findTeacherById("t1");
        System.out.println(teacher.getEmail());
        teacher=teacherDaoService.findTeacherByName("tinny");
        System.out.println(teacher.getEmail());

        teacher = teacherDaoService.findTeacherByNameAndPw("tinny","1234");
        System.out.println(teacher.getEmail());
        System.out.println(teacherDaoService.numOfTeachers());

        teacher.setName("teddy");
        teacherDaoService.updateTeacher(teacher);

    }
    */
}
