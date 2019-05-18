package pust.model.login;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pust.model.utility.SendMail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Optional;

public class LogInModel {

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
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/image/smallSwepustlogg.png").toString()));
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/view/basicStyleSheet.css").toExternalForm());
        dialog.setTitle("Password Reset");
        dialog.setHeaderText(null);
        dialog.setContentText("E-mail:");
        Optional<String> result = dialog.showAndWait();

        try {
            if (result.isPresent()) {
                String subject = "PUST Password Reset";
                String emailResult = result.get().trim();

                if (validEmail(emailResult)) {
                    String message = "Hello " + emailResult + ", here is your new password: ";
                    SendMail.generateAndSendEmail(emailResult, subject, message);
                    alert("E-mail sent", "Check your inbox, we have sent you a new password");
                } else {
                    alert("Warning", "You did not enter a valid e-mail address");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("error");
        }
    }

    private void alert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/image/smallSwepustlogg.png").toString()));
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/view/basicStyleSheet.css").toExternalForm());
        alert.showAndWait();
    }

    private boolean validEmail(String email) {
        boolean valid = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            valid = false;
        }
        return valid;
    }
}

