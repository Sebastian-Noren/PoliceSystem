package pust.model.utility;

import org.apache.commons.logging.impl.ServletContextCleaner;

import java.io.File;
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

    public void generateAndSendEmail(String recipient, String subject, String message, String attachment) throws MessagingException {

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

        //Creates email with text

        File file = new File("/image/swepustText.png");
        attachment = this.getClass().getResource("/image/swepustText.png").getPath();

        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBody = new MimeBodyPart();
        String emailText = message + "<br><br> Regards, <br>PUST Integrated Graphic System";
        messageBody.setContent(emailText, "text/html");
        multipart.addBodyPart(messageBody);
        messageBody = new MimeBodyPart();
        DataSource source = new FileDataSource(attachment);
        messageBody.setDataHandler(new DataHandler(source));
        messageBody.setFileName(attachment);
        multipart.addBodyPart(messageBody);
        createMail.setContent(multipart);

        // Here the mail is sent with user ID and password
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", "noreply.pust@gmail.com", "FluffyBunny");
        transport.sendMessage(createMail, createMail.getAllRecipients());
        transport.close();
    }
}