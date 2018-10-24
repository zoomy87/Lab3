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
    
    public static void email(String email) {

        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "ejzumba@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "smtp.ilstu.edu";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "false");
        properties.setProperty("mail.user", "ejzumba@ilstu.edu"); // if needed
        properties.setProperty("mail.password", "Vincent128706"); // if needed

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

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
            message.setContent("<h1>You have officially been added to the DB!</h1>",
                    "text/html");

            // Send message
            Transport.send(message);
            log.info("Sent message successfully....");
        } catch (MessagingException mex) {
            log.log(Logger.Level.FATAL, "Messaging Exception: "+mex.getMessage());
        }
    }
}
