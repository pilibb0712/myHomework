package utils;
import java.util.Properties;
import javax.mail.internet.InternetAddress ;
import javax.mail.internet.AddressException ;
import javax.mail.* ;
import javax.mail.internet.MimeMessage;

public class TestEmail {
    public static void main(String[] args){
        try{
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");
            //你自己的邮箱
            props.put("mail.user", "1292155474@qq.com");
            //你开启pop3/smtp时的验证码
            props.put("mail.password", "byocwmkrmsbygchc");
            props.put("mail.smtp.port", "587");
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
            InternetAddress to = new InternetAddress("161250194@smail.nju.edu.cn");
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            // 设置邮件标题
            message.setSubject("testEmail");                // 设置邮件的内容体
            message.setContent("123456", "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        }catch (AddressException e) {
            e.printStackTrace();
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
