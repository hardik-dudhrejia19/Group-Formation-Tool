package CSCI5308.GroupFormationTool.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {

    public boolean sendMail(String email, String bannerID, String courseName) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("advsdcgrp17@gmail.com", "grp17@2020");
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("advsdcgrp17@gmail.com", false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("Credentials Details");

            msg.setContent("Welcome to the course of "+courseName+"\n" +
                    "Your Banner ID is: "+bannerID + "\n" +
                    "Your password is: blank(Just leave the textbox empty)", "text/html");

            Transport.send(msg);
        }
        catch (MessagingException ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}