package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pust.model.database_functionality.InsertPerson;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Employee;
import pust.model.entity.Suspect;
import pust.model.utility.AppConstant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StandardWindowController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

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
            if (AppConstant.person instanceof Suspect) {
                AppConstant.suspect = (Suspect) AppConstant.person;
            } else if (AppConstant.person instanceof Employee) {
                AppConstant.employee = (Employee) AppConstant.person;
            }
            if (!(AppConstant.person == null)) {
                AppConstant.setSsnCheck(true);
                if (AppConstant.person.isWanted()) {
                    AppConstant.alertBoxWarning("Wanted!", AppConstant.person.getFirstName() + " is wanted for a Crime!");
                    try {
                        Parent fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ViewWantedScreen.fxml"));
                        IDPane.getChildren().removeAll();
                        IDPane.getChildren().setAll(fxml);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, e.toString(), e);
                    }
                } else {
                    try {
                        Parent fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ApplyForIdentification.fxml"));
                        IDPane.getChildren().removeAll();
                        IDPane.getChildren().setAll(fxml);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, e.toString(), e);
                    }
                }
            } else {
                AppConstant.alertBoxInformation("Person not found", String.format("No person found in system with SSN: %s", ssn));
                ssnTextSearch.clear();
            }
        }
    }

    @FXML
    private void typingReset() {
        labelSSN.setText("");
    }
}

