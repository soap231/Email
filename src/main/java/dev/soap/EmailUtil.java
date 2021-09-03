package dev.soap;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DecimalFormat;
import java.util.Properties;

public class EmailUtil {
    private static String myEmail = System.getenv("email");          //"revature.team.3@gmail.com";
    private static String myPassword = System.getenv("password");

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static Session createEmailSession(){
        System.out.println("Prepare to send email...");

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, myPassword);
            }
        });
        return session;
    }


    public static boolean sendEmailWithHtml(String recipientEmail, String subject, String html){
        if(recipientEmail == null){
            System.out.println("email is null");
            return false;
        }

        Session session = createEmailSession();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setContent(html, "text/html");
            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean sendEmailWithText(String recipientEmail, String subject, String text){

        Session session = createEmailSession();
        // Message message = prepareMessage(session, myEmail, recipient);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

}
