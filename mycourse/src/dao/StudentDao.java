package dao;

import daoService.StudentDaoService;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentDao implements StudentDaoService {
    @Override
    public Boolean ifStudentNameExist(String name) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Student as student where student.name='"+name+"' and student.canceled=false");
        Student student=(Student)hql.uniqueResult();
        transaction.commit();
        if(student==null){
            System.out.println("no current student of the name "+name);
            return false;
        }
        return true;
    }

    @Override
    public Boolean ifStudentEmailExist(String email) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Student as student where student.email='"+email+"'");
        Student student=(Student)hql.uniqueResult();
        transaction.commit();
        if(student==null){
            System.out.println("no student of the email "+email);
            return false;
        }
        return true;
    }

    @Override
    public Boolean ifEmailExistOfCurrentStudents(String email) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Student as student where student.email='"+email+"' and student.canceled=false");
        Student student=(Student)hql.uniqueResult();
        transaction.commit();
        if(student==null){
            System.out.println("no current student of the email "+email);
            return false;
        }
        return true;
    }

    @Override
    public Student findStudentByNameAndPw(String name, String password) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Student as student where student.name='"+name+"' and student.password='"+password+"'");
        Student student=(Student)hql.uniqueResult();
        transaction.commit();
        return student;
    }

    @Override
    public Student findStudentById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
       Student student=session.get(Student.class,id);
        transaction.commit();
        if(student==null){
            System.out.println("no student of the id "+id);
            return student;
        }
        return student;
    }

    @Override
    public Student findStudentByName(String name) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Student as student where student.name='"+name+"'");
        Student student=(Student)hql.uniqueResult();
        transaction.commit();
        if(student==null){
            System.out.println("no student of the name "+name);
            return student;
        }
        return student;
    }

    @Override
    public Student findStudentByEmail(String email) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("from entity.Student as student where student.email='"+email+"'");
        Student student=(Student)hql.uniqueResult();
        transaction.commit();
        if(student==null){
            System.out.println("no student of the email "+email);
            return student;
        }
        return student;
    }

    @Override
    public ArrayList<String> findGradesOfStudents() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select distinct student.grade from entity.Student as student");
        List<String> list=hql.list();
        transaction.commit();
        return (ArrayList<String>)list;
    }

    @Override
    public int findStudentNumOfGrade(String grade) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Student as student where student.grade='"+grade+"'");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    @Override
    public void addStudent(Student student) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
    }

    @Override
    public int numOfStudents() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query hql=session.createQuery("select count(*) from entity.Student as student");
        Number num=(Number)hql.uniqueResult();
        transaction.commit();
        return num.intValue();
    }

    @Override
    public void updateStudent(Student student) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
    }

    /*
    public static void main(String[] args) {
        ApplicationContext appliationContext = new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        StudentDaoService studentDaoService = (StudentDaoService) appliationContext.getBean("StudentDaoService");

        Student student1=new Student();
        student1.setId("s1");
        student1.setName("betty");
        student1.setEmail("1292155474@163.com");
        student1.setGrade("2016");
        student1.setCanceled(false);
        student1.setPassword("1234");
        student1.setStudent_number("161250194");
        studentDaoService.addStudent(student1);

        Student student2=new Student();
        student2.setId("s2");
        student2.setName("cindy");
        student2.setEmail("1292155474@126.com");
        student2.setGrade("2015");
        student2.setCanceled(true);
        student2.setPassword("1234");
        student2.setStudent_number("151250194");
        studentDaoService.addStudent(student2);

        System.out.println(studentDaoService.ifStudentEmailExist("1292155474@163.com"));
        System.out.println(studentDaoService.ifStudentEmailExist("1292155474@qq.com"));
        System.out.println(studentDaoService.ifEmailExistOfCurrentStudents("1292155474@126.com"));
        System.out.println(studentDaoService.ifStudentNameExist("grace"));
        System.out.println(studentDaoService.ifStudentNameExist("betty"));

        Student student=studentDaoService.findStudentById("s1");
        System.out.println(student.getEmail());
        student=studentDaoService.findStudentByName("cindy");
        System.out.println(student.getEmail());

        student=studentDaoService.findStudentByEmail("1292155474@163.com");
        System.out.println(student.getName());
        student=studentDaoService.findStudentByNameAndPw("betty","1234");
        System.out.println(student.getEmail());
        ArrayList<String> grades=studentDaoService.findGradesOfStudents();
        System.out.println(grades.size()+" "+grades.get(0)+" "+grades.get(1));
        System.out.println(studentDaoService.numOfStudents());

        student.setCanceled(true);
        studentDaoService.updateStudent(student);
        System.out.println(studentDaoService.findStudentNumOfGrade("2014"));

    }*/

    }
