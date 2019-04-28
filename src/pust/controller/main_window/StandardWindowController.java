package pust.controller.main_window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class StandardWindowController implements Initializable {

    @FXML
    private Label labelSSN;
    @FXML private TextField ssnTextSearch;

    public void watchPersonInfo(ActionEvent actionEvent) {
        if (ssnTextSearch.getText().isEmpty() || ssnTextSearch.getText().length() < 12 || ssnTextSearch.getText().length() > 12) {
            labelSSN.setText("SSN needs to be 12 characters!");
            ssnTextSearch.clear();
        }else{
        //TODO  make the switch to person view. And check database rue/false the user don´t exist and display warning.

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    labelSSN.setText("");
    }

    @FXML
    private void typingReset() {
        labelSSN.setText("");
    }
}
