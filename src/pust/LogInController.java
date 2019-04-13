package pust;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LogInController {

    //TODO Create a nice loginscreen Julius.

    @FXML
    TextField userName;
    @FXML
    TextField passWord;
    @FXML
    Button logInBtn;
    DatabaseConnection database;
    boolean connected;

    public void logInBtn(ActionEvent actionEvent) {

        database = new DatabaseConnection();
        connected = database.Loginconnect(userName.getText(), passWord.getText());


        //TODO make strings safe for root Admin. Sebastians shit
        if (connected && userName.getText().equals("root") && userName.getText().equals("root")) {
            SceneSwitch sceneSwitcher = new SceneSwitch();
            sceneSwitcher.goToAdminCreate(actionEvent);
        } else {
            //TODO Something
        }
    }

}


