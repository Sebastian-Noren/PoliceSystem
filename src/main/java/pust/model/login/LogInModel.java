package pust.model.login;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.dbcp2.BasicDataSource;
import pust.model.utility.SendMail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

public class LogInModel {
    private static final Logger LOGGER = Logger.getLogger(LogInModel.class.getName());


    private SendMail sendMail = new SendMail();

    public void resetPassword() throws javax.mail.internet.AddressException, javax.mail.MessagingException {
        TextInputDialog dialog = new TextInputDialog();
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/image/icon.png").toString()));
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
                    String message = "Hello " + emailResult + ", here is your new password: test";
                    String attachment = this.getClass().getResource("/image/swepustText.png").getPath();

                    sendMail.generateAndSendEmail(emailResult, subject, message, attachment);

                    alertInfo("E-mail sent", "Check your inbox, we have sent you a new password");
                } else {
                    alertInfo("Warning", "You did not enter a valid e-mail address");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("error");
        }
    }

    private void alertInfo(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/image/icon.png").toString()));
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/view/basicStyleSheet.css").toExternalForm());
        alert.showAndWait();
    }

    public void alertWarning(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/image/icon.png").toString()));
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/view/basicStyleSheet.css").toExternalForm());
        alert.show();
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

    public boolean isValidUser(BasicDataSource bs, String userName, String password) {
        bs.setUrl("jdbc:mysql://localhost:4321/pustgis?&useSSL=FALSE");
        bs.setUsername(userName);
        bs.setPassword(password);
        bs.setMinIdle(5);
        bs.setMaxIdle(10);
        bs.setMaxOpenPreparedStatements(200);

        boolean result = false;
        try (Connection connection = bs.getConnection()) {
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

