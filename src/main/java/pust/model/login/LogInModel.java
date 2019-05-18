package pust.model.login;

import com.google.protobuf.Message;
import com.jcraft.jsch.Session;
import javafx.scene.control.TextInputDialog;
import pust.controller.PopupController;
import pust.model.utility.SendMail;

import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;


public class LogInModel {

    PopupController pop = new PopupController();
    SendMail mail = new SendMail();

    // this method takes the count of failed log in attempts and displays appropriate messsage
    public String passwordCounter(int wrongPass) {
        if (wrongPass > 0 && wrongPass < 3) {
            return "Incorrect username or password";
        } else if (wrongPass >= 3) {
            return "warning";
        } else {
            return "";
        }
    }

    public void resetPassword() throws javax.mail.internet.AddressException, javax.mail.MessagingException {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Password Reset");
        dialog.setHeaderText("Enter your E-mail address:");
        dialog.setContentText("E-mail:");
        Optional<String> result = dialog.showAndWait();

        if(result.isPresent()){
            String subject = "PUST Password Reset";
            String emailResult = result.get();
            String message = "Hello " + emailResult +", here is your new password: ";
            SendMail.generateAndSendEmail(emailResult, subject, message);
        }
        //pass.show();
    }
}

