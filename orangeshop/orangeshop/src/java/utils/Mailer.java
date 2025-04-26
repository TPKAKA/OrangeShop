/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class Mailer {

    public static void send(String to, String sub,
            String msg) {
        Properties props = new Properties(); // Cau hinh gui mail
        String user = "duongnqhe171601@fpt.edu.vn";
        String pass = "dmhp cohq fanm rzvc";
        /* Specifies the IP address of your default mail server
     	   for e.g if you are using gmail server as an email sever
           you will pass smtp.gmail.com as value of mail.smtp host. 
           As shown here in the code. 
           Change accordingly, if your email id is not a gmail id
         */
        props.put("mail.smtp.host", "smtp.gmail.com");//  địa chỉ IP của máy chủ email mặc định
        //below mentioned mail.smtp.port is optional
        props.put("mail.smtp.port", "587"); // cổng dịch vụ SMTP (Simple Mail Transfer Protocol) để gửi email.
        props.put("mail.smtp.auth", "true"); // xác thực (authentication) khi gửi email.
        props.put("mail.smtp.starttls.enable", "true"); // kết nối TLS (Transport Layer Security).
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2"); // phiên bản giao thức SSL (Secure Sockets Layer)
        /* Pass Properties object(props) and Authenticator object   
           for authentication to Session instance 
         */
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {

            /* Create an instance of MimeMessage, 
 	      it accept MIME types and headers 
             */
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html");
            message.setText(msg, "UTF-8");
            message.setSubject(sub, "UTF-8");
            /* Transport class is used to deliver the message to the recipients */
            Transport.send(message);
            System.out.println("Gui Email thanh cong");
        } catch (MessagingException e) {
            e.printStackTrace();
            
            System.out.println("Lỗi: Không thể gửi email");
        }

    }

    public static void main(String[] args) {
        send("rollingdown216@gmail.com", "Verify", "152");
    }

}
