
package pust.controller;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.apache.commons.dbcp2.BasicDataSource;
import pust.model.utility.AppConstant;
import pust.model.login.LogInModel;
import pust.model.utility.LinuxRemoteConnection;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogInController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(LogInController.class.getName());

    @FXML
    private TextField userNameTextField, passwordTextField;
    @FXML
    private Label userWarning, passWarning;
    @FXML
    private Button logInBtn;

    private String lockedAccount;
    private String userName;
    private String password;
    private int counter;
    private long startTime;
    private int lockDuration = 60;

    private LogInModel model = new LogInModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> logInBtn.requestFocus());
        passWarning.setText(null);
        userWarning.setText(null);
        userName = userNameTextField.getText().trim();
        password = passwordTextField.getText().trim();
        LinuxRemoteConnection.remoteConnect();
        userNameTextField.focusedProperty().addListener((ov, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                checkLockout();
            }
        });
    }

    @FXML
    private void logInBtn(ActionEvent actionEvent) {
        userName = userNameTextField.getText().trim();
        password = passwordTextField.getText().trim();

        if (userName.isEmpty()) {
            userWarning.setText("Enter a username");
            return;
        }
        if (password.isEmpty()) {
            passWarning.setText("Enter a password");
            return;
        }

        //Implements the BasicDataSource library and pass it to isValidUser method
        BasicDataSource basicDataSource = new BasicDataSource();
        if (userName.equals("root")) {
            if (model.isValidUser(basicDataSource, userName, password)) {
                AppConstant.dataSource = basicDataSource;

                //Send you to IT-administrator
                String strSceneFXML = "/view/AdminScreen.fxml";
                AppConstant.switchScene(actionEvent, strSceneFXML);
            }

        } else if (model.isValidUser(basicDataSource, userName, password)) {
            AppConstant.dataSource = basicDataSource;
            //Sends you to mainWindow
            AppConstant.setCurrentUser(userName);
            String strSceneFXML = "/view/main_window/MainFrame.fxml";
            AppConstant.switchScene(actionEvent, strSceneFXML);
        } else {
            try {
                basicDataSource.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        counter++;
        passWordWarning();
    }

    private void passWordWarning() {
        passWarning.setText("Incorrect username or password");
        if (counter >= 3) {
            startTime = System.nanoTime();
            passWarning.setText(null);
            counter = 0;
            lockout(userName);
        }
    }

    // flawed but cool lock-out. To be improved.
    private void lockout(String userInfo) {
        long lockTimeLeft;
        long endTime;
        lockedAccount = userInfo;
        if (userNameTextField.getText().equals(lockedAccount)) {
            userNameTextField.setText(null);
            endTime = System.nanoTime();
            lockTimeLeft = (endTime - startTime);
            lockTimeLeft = TimeUnit.SECONDS.convert(lockTimeLeft, TimeUnit.NANOSECONDS);
            lockTimeLeft = lockDuration - lockTimeLeft;
            model.alertWarning("Warning", "Due to repeated failed log in attempts, " +
                    "your account \"" + lockedAccount + "\" has been locked for "
                    + lockDuration + " seconds. "
                    + lockTimeLeft + " seconds remaining.");
        }
    }

    private void checkLockout() {
        PauseTransition delay = new PauseTransition(Duration.seconds(lockDuration));
        lockout(lockedAccount);
        delay.setOnFinished(event -> lockedAccount = (null));
        delay.play();
    }

    public void forgotPasswordClicked() throws javax.mail.internet.AddressException, javax.mail.MessagingException {
        model.resetPassword();
    }

    // this method removes the "invalid ****" warning while trying to type new credentials
    public void typingReset() {
        passWarning.setText(null);
        userWarning.setText(null);
    }
}