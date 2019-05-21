
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
import pust.model.utility.AppConstant;
import pust.model.login.LogInModel;
import pust.model.utility.LinuxRemoteConnection;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class LogInController implements Initializable {

    @FXML
    TextField userName, passWord;
    @FXML
    Label userWarning, passWarning, passForgot;
    @FXML
    Button logInBtn;

    private String lockedAccount;
    private int counter;
    private long startTime;
    private int lockDuration = 60;

    private LogInModel model = new LogInModel();

    @FXML
    private void logInBtn(ActionEvent actionEvent) {
        if (userName.getText().isEmpty()) {
            userWarning.setText("Enter a username");
            return;
        }
        if (passWord.getText().isEmpty()) {
            passWarning.setText("Enter a password");
            return;
        }
        if (userName.getText().equals("root")) {
            if (model.LogInAuth(userName.getText(), passWord.getText())) {
                //Send you to IT-administrator
                String strSceneFXML = "/view/AdminScreen.fxml";
                AppConstant.switchScene(actionEvent, strSceneFXML);
            }
        } else if (model.LogInAuth(userName.getText(), passWord.getText())) {
            //Sends you to mainWindow
            String strSceneFXML = "/view/main_window/MainFrame.fxml";
            AppConstant.switchScene(actionEvent, strSceneFXML);
        }

        //temporary log in without database
        if (userName.getText().equals("root") && passWord.getText().equals("root")) {
            String strSceneFXML = "/view/AdminScreen.fxml";
            AppConstant.switchScene(actionEvent,strSceneFXML);
        } else if (userName.getText().equals("user") && passWord.getText().equals("user")) {
            String strSceneFXML = "/view/main_window/MainFrame.fxml";
            AppConstant.switchScene(actionEvent,strSceneFXML);
        }
        counter++;
        passWordWarning();
    }

    private void passWordWarning(){
        passWarning.setText("Incorrect username or password");
        if (counter >= 3) {
            startTime = System.nanoTime();
            passWarning.setText(null);
            counter = 0;
            lockout(userName.getText());
        }
        //if (model.passwordCounter(counter).equals("warning"))
    }

    // flawed but cool lock-out. To be improved.
    private void lockout(String userInfo) {
        long lockTimeLeft;
        long endTime;
        lockedAccount = userInfo;
        if (userName.getText().equals(lockedAccount) ){
            userName.setText(null);
            endTime = System.nanoTime();
            lockTimeLeft = (endTime - startTime);
            lockTimeLeft = TimeUnit.SECONDS.convert(lockTimeLeft, TimeUnit.NANOSECONDS);
            lockTimeLeft = lockDuration - lockTimeLeft;
            model.alertWarning("Warning", "Due to repeated failed log in attempts, " +
                    "your account has been locked for "
                    + lockDuration + " seconds. "
                    + lockTimeLeft + " seconds remaining.");
        }
    }

    private void checkLockout()  {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> logInBtn.requestFocus());
        passWarning.setText(null);
        userWarning.setText(null);
        LinuxRemoteConnection.remoteConnect();
        userName.focusedProperty().addListener((ov, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                checkLockout();
            }
        });
    }
}


