package pust;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    //TODO Create a nice loginscreen Julius.

    @FXML
    TextField userName;
    @FXML
    TextField passWord;
    @FXML
    Label passWarning;
    @FXML
    Label passForgot;
    @FXML
    Button logInBtn;
    DatabaseConnection database;
    boolean connected;

    int counter;

    public void logInBtn(ActionEvent actionEvent) {
        counter++;

        database = new DatabaseConnection();
        connected = database.Loginconnect(userName.getText(), passWord.getText());

        passwordCounter(counter);

        //TODO make strings safe for root Admin. Sebastians shit
        if (connected && userName.getText().equals("root") && userName.getText().equals("root")) {
            SceneSwitch sceneSwitcher = new SceneSwitch();
            sceneSwitcher.goToAdminCreate(actionEvent);
        } else {
            //TODO Something
        }
    }

    public void passwordCounter(int wrongPass){
        if (wrongPass >0 && wrongPass < 3){
            passWarning.setText("Incorrect username or password");
        } else if (wrongPass >= 3) {
            passWarning.setText("YOU'VE BEEN LOCKED OUT BITCH");
            //create method to lock out
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater( () -> logInBtn.requestFocus() );
        passWarning.setText("");
    }
}


