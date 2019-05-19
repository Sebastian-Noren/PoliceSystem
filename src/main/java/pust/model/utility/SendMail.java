package pust.model.utility;

import java.io.FileNotFoundException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    public static void generateAndSendEmail(String recipient, String subject, String message) throws MessagingException {

        // This sets up the properties of the mail server
        Properties mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // This brings the server properties into a session and generates the e-mail
        Session session = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage createMail = new MimeMessage(session);
        createMail.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        createMail.setSubject(subject);

        //Creates email with text and image
        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBody = new MimeBodyPart();
        String emailText = message + "<br><br> Regards, <br>PUST Integrated Graphic System";
        messageBody.setContent(emailText, "text/html");
        multipart.addBodyPart(messageBody);
        messageBody = new MimeBodyPart();
        DataSource fds = new FileDataSource("C:\\Dev\\Git\\PoliceSystem\\src\\main\\resources\\image\\swepustText.png");
        messageBody.setDataHandler(new DataHandler(fds));
        messageBody.setHeader("Content -ID", "<image>");
        multipart.addBodyPart(messageBody);
        createMail.setContent(multipart);




        // Here the mail is sent with user ID and password
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", "noreply.pust@gmail.com", "SeigHeil");
        transport.sendMessage(createMail, createMail.getAllRecipients());
        transport.close();
    }
}

