package pust.controller;

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
import pust.model.utility.DatabaseConnection;
import pust.model.LogInModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    long time = System.currentTimeMillis();
    long end = time + 3000;

    //TODO Create a nice loginscreen Julius.

    @FXML
    TextField userName;
    @FXML
    TextField passWord;
    @FXML
    Label userWarning;
    @FXML
    Label passWarning;
    @FXML
    Label passForgot;
    @FXML
    Button logInBtn;
    @FXML
    ImageView warningImage;

    DatabaseConnection database;
    boolean connected;

    private LogInModel model = new LogInModel();

    private int counter;

    public void logInBtn(ActionEvent actionEvent) {

        if (userName.getText().equals("")) {
            userWarning.setText("Enter a username");
            return;
        }
        if (passWord.getText().equals("")) {
            passWarning.setText("Enter a password");
            return;
        }

        database = new DatabaseConnection();
        connected = database.Loginconnect(userName.getText(), passWord.getText());

        // IF loginconnect returns incorrect username/password
        // this whole thing will be made better, code is all over the place for now
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
        if (connected && userName.getText().equals("root") && userName.getText().equals("root")) {
            SceneSwitch sceneSwitcher = new SceneSwitch();
            sceneSwitcher.goToAdminCreate(actionEvent);
        } else {
            //TODO Something
        }
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
    }
}


