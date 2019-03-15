package serviceImpl;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import daoService.*;
import entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import service.EmailService;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

@Repository
public class EmailServiceImpl implements EmailService {
    private static CourseDaoService courseDaoService;
    private static CoursingDaoService coursingDaoService;
    private static TeacherDaoService teacherDaoService;
    private static StudentDaoService studentDaoService;
    private static TakeCoursingDaoService takeCoursingDaoService;

    public EmailServiceImpl(){
        ApplicationContext appliationContext=new ClassPathXmlApplicationContext("DaoApplicationContext.xml");
        courseDaoService=(CourseDaoService)appliationContext.getBean("CourseDaoService");
        coursingDaoService=(CoursingDaoService)appliationContext.getBean("CoursingDaoService");
        teacherDaoService=(TeacherDaoService)appliationContext.getBean("TeacherDaoService");
        takeCoursingDaoService=(TakeCoursingDaoService)appliationContext.getBean("TakeCoursingDaoService");
        studentDaoService=(StudentDaoService)appliationContext.getBean("StudentDaoService");
    }
    @Override
    public String validateEmail(String toAddress) {
        int validateNum=(int)(Math.random()*9000)+1000;
        String validateString=String .valueOf(validateNum);
        String topic="validate from mycourse";
        String content="Your validate num is "+validateString+", please register quickly!";
        sendEmail(toAddress,topic,content);
        return validateString;
    }
/*
    public static void main(String args[]) {
        ApplicationContext serviceAppliationContext = new ClassPathXmlApplicationContext("ServiceApplicationContext.xml");
        EmailService emailService = (EmailService) serviceAppliationContext.getBean("EmailService");

        String number=emailService.validateEmail("161250194@smail.nju.edu.cn");
        System.out.println(number);
    }*/


private void sendEmail(String toAddress,String topic,String content){
    try{
        System.out.println("****************************server sent11111");
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.163.com");
        //自己的邮箱
        props.put("mail.user", "18018691650@163.com");
        //开启pop3/smtp时的验证码
        props.put("mail.password", "B2199861317");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.starttls.enable", "true");
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);                  }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        String username = props.getProperty("mail.user");
        InternetAddress from = new InternetAddress(username);
        message.setFrom(from);
        // 设置收件人
        InternetAddress to = new InternetAddress(toAddress);
        message.setRecipient(MimeMessage.RecipientType.TO, to);
        // 设置邮件标题
        message.setSubject(topic);                // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
        System.out.println("****************************server sent");
    }catch (AddressException e) {
        e.printStackTrace();
    }catch (MessagingException e){
        e.printStackTrace();
    }
}
    @Override
    public void sendEmailToAllStudents(String ccid, String topic,String content) {
        //already set topic and content
         ArrayList<String> studentIds=takeCoursingDaoService.findTakenStudentsOfCoursing(ccid);
        if(studentIds!=null){
            for(int i=0;i<=studentIds.size()-1;i++){
                String sid=studentIds.get(i);
                Student student=studentDaoService.findStudentById(sid);
                String studentEmail=student.getEmail();
                sendEmail(studentEmail,topic,content);
            }
        }
    }
}
