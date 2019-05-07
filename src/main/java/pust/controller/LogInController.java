/*This is the login screen. To enter the system:
 Username: user
 Password: user
*/

package pust.controller;

import com.mysql.cj.protocol.Resultset;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import pust.controller.SceneSwitch;
import pust.model.utility.DatabaseConnection;
import pust.model.LogInModel;
import pust.model.utility.LinuxRemoteConnection;
import pust.model.utility.database_connection.DBCPDataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    long time = System.currentTimeMillis();
    long end = time + 3000;

    @FXML
    TextField userName, passWord;
    @FXML
    Label userWarning, passWarning, passForgot;
    @FXML
    Button logInBtn;
    @FXML
    ImageView warningImage;

    DatabaseConnection database;
    boolean connected;

    private LogInModel model = new LogInModel();

    private int counter;


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

        //temporary log in without database
        SceneSwitch sceneSwitcher = new SceneSwitch();
        if (userName.getText().equals("root") && passWord.getText().equals("root")) {
            //Send you to IT-administrator
            sceneSwitcher.goToAdminCreate(actionEvent);
            //sceneSwitcher.goToMain(actionEvent);
        } else if (userName.getText().equals("user") && passWord.getText().equals("user")) {
            //Sends you to mainWindow
            sceneSwitcher.goToMain(actionEvent);
        }

        //database = new DatabaseConnection();
        //connected = database.Loginconnect(userName.getText(), passWord.getText());

        // IF login returns incorrect username/password
        // this whole thing will be made better.
        counter++;
        passWarning.setText(model.passwordCounter(counter));
        if (model.passwordCounter(counter).equals("warning")) {
            passWarning.setText("");
            counter = 0;
            PauseTransition delay = new PauseTransition(Duration.seconds(5)); // Warning can be set for any time
            lockout();
            delay.setOnFinished(event -> unLockout());
            delay.play();
        }

        //TODO make strings safe for root Admin. Sebastians shit
        /*SceneSwitch sceneSwitcher = new SceneSwitch();
        if (connected && userName.getText().equals("root") && passWord.getText().equals("root")) {
            //Send you to IT-administrator
            sceneSwitcher.goToAdminCreate(actionEvent);
            sceneSwitcher.goToMain(actionEvent); // Tempory block of IT-admin
        } else {
            //Send you to mainWindows
            sceneSwitcher.goToMain(actionEvent);
        }*/
    }

    public void forgotPasswordClicked(ActionEvent event) {
        //placeholder code
        SceneSwitch sceneSwitcher = new SceneSwitch();
        sceneSwitcher.goToAdminCreate(event);
    }

    //to be moved to LogInModel if possible.
    private void lockout() {
        warningImage.setVisible(true);
        userName.setVisible(false);
        userName.setManaged(false);
        passWord.setVisible(false);
        passWord.setManaged(false);
        logInBtn.setVisible(false);
        logInBtn.setManaged(false);
        passForgot.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        passForgot.setText("FORGOT PASSWORD");
        passForgot.setTextFill(Color.WHITE);
    }

    private void unLockout() {
        warningImage.setVisible(false);
        userName.setVisible(true);
        userName.setManaged(true);
        passWord.setVisible(true);
        passWord.setManaged(true);
        logInBtn.setVisible(true);
        logInBtn.setManaged(true);
        passForgot.setFont(Font.font("System", FontWeight.NORMAL, 12));
        passForgot.setText("Forgot password?");
        passForgot.setTextFill(Color.valueOf("#698dae"));
    }

    // this method removes the "invalid password" warning while trying to type a new password
    public void typingReset() {
        passWarning.setText("");
        userWarning.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> logInBtn.requestFocus());
        warningImage.setVisible(false);
        passWarning.setText("");
        userWarning.setText("");
        LinuxRemoteConnection.remoteConnect();
    }
}


