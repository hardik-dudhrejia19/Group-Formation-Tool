package CSCI5308.GroupFormationTool.AccessControl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class UserNotification implements IUserNotifications{

    private static final String EMAIL = "advsdcgrp17@gmail.com";
    private static final String PASSWORD = "grp17@2020";

    @Override
    public void sendUserLoginCredentials(User user, String rawPassword)
    {
        try
        {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(EMAIL,PASSWORD);
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(EMAIL, false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            msg.setSubject("Credentials Details");

            msg.setContent(
                    "Your Banner ID is: "+ user.getBanner() + "\n" +
                    "Your password is: " + user.getPassword() , "text/html");

            Transport.send(msg);
        }
        catch (MessagingException ex)
        {
            ex.printStackTrace();
        }
    }
}
