package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import pust.model.database_functionality.InsertPerson;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

    @FXML
    private TabPane tabPane;

    @FXML
    public  Parent fxml;

    @FXML
    private Tab reportCrimeTab;

    @FXML
    private AnchorPane reportCrimeAnchorPane;

    @FXML
    private Tab reportCustodyTab;

    @FXML
    private AnchorPane reportArrestPane;

    @FXML
    private Tab reportLostEntityTab;

    @FXML
    private AnchorPane reportLostEntityPane;

    @FXML
    private Tab reportMissingPersonTab;

    @FXML
    private AnchorPane reportMissingPersonPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     //loads up reportCrimeScene
        reportCrimeScene();
     //loads up reportArrestScene
        reportArrestScene();
     //loads up reportLostEntityScene
        reportLostEntity();
     //loads up missingPersonScene
        reportMissingPerson();
    }

    public void reportCrimeScene(){
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ReportCrime.fxml"));
            reportCrimeAnchorPane.getChildren().removeAll();
            reportCrimeAnchorPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reportArrestScene(){
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ReportArrestInCustody.fxml"));
            reportArrestPane.getChildren().removeAll();
            reportArrestPane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void reportLostEntity(){
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ReportLostEntity.fxml"));
            reportLostEntityPane .getChildren().removeAll();
            reportLostEntityPane .getChildren().setAll(fxml);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void reportMissingPerson(){

        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ReportMissingPerson.fxml"));
            reportMissingPersonPane .getChildren().removeAll();
            reportMissingPersonPane .getChildren().setAll(fxml);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }
}
