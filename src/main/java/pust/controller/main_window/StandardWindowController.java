package pust.controller.main_window;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pust.model.database_functionality.SelectPerson;
import pust.model.utility.AppConstant;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StandardWindowController implements Initializable {

    @FXML
    private Label labelSSN;
    @FXML
    private TextField ssnTextSearch;
    @FXML
    private AnchorPane IDPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelSSN.setText("");
    }

    @FXML
    private void watchPersonInfo() {
        if (ssnTextSearch.getText().isEmpty() || ssnTextSearch.getText().length() < 12 || ssnTextSearch.getText().length() > 12) {
            labelSSN.setText("SSN needs to be 12 characters!");
            ssnTextSearch.clear();
        } else {
            String ssn = ssnTextSearch.getText().trim();
            AppConstant.person = new SelectPerson(ssn).loadPerson();
            AppConstant.setSsnCheck(true);
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ApplyForIdentification.fxml"));
                IDPane.getChildren().removeAll();
                IDPane.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void typingReset() {
        labelSSN.setText("");
    }
}

