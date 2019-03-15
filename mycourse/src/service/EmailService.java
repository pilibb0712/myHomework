package service;

import javax.mail.internet.MimeMessage;

public interface EmailService {
    //all emails are from mycourse!!!

    public String validateEmail(String toAddress);
    //return validate number
    public void sendEmailToAllStudents(String ccid,String topic,String content);
    //message已设置好标题/内容等教师输入的内容
    //from mycourse not teacherEmail
}
