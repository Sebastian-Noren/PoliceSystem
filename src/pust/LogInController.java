package pust;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    //TODO Create a nice loginscreen.

    @FXML
    TextField userName;
    @FXML
    TextField passWord;
    @FXML
    Button logInBtn;
    DatabaseConnection database;

    public void logInBtn(ActionEvent actionEvent) {

        database = new DatabaseConnection(userName.getText(), passWord.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
