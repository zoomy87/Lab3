/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author BillyLim
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import org.jboss.logging.Logger;

public class JavaMailApp {
    private static final Logger log= Logger.getLogger(JavaMailApp.class);
    
    public static void email(User user) {

        // Recipient's email ID needs to be mentioned.
        String to = user.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "ejzumba@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "outlook.office365.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        
        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("email", "password");
            }
        });
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Thanks for signing up!");

            // Send the actual HTML message, as big as you like
            String content= "<h1>You have officially been added to the DB!</h1><br></br>"
                    + "<p>First Name: "+user.getfName()+"<br></br>Last Name: "+user.getlName()
                    +"<br></br>User ID: "+user.getUserID()
                    +"<br></br>Password: "+user.getPassword()
                    +"<br></br>Security Question: "+user.getQuestion()
                    +"<br></br>Security Answer: "+user.getAnswer()+"<br></br>";
            
            message.setContent( content,"text/html");

            // Send message
            Transport.send(message);
            log.info("Sent message successfully....");
        } catch (MessagingException mex) {
            log.log(Logger.Level.FATAL, "Messaging Exception: "+mex.getMessage());
        }
    }
}
