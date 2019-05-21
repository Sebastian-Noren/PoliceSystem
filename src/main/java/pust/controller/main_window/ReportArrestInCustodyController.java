package pust.controller.main_window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import pust.model.enumerations.Build;
import pust.model.enumerations.Color;
import pust.model.enumerations.Ethnicity;
import pust.model.enumerations.Gender;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportArrestInCustodyController implements Initializable {

    @FXML
    private ChoiceBox<Gender> suspectGenderBox;
    @FXML
    private ChoiceBox<Build> suspectBuildBox;
    @FXML
    private ChoiceBox<Gender> suspectDescGenderBox;
    @FXML
    private ChoiceBox<Color.eyeColor> suspectEyeBox;
    @FXML
    private ChoiceBox<Color.hairColor> suspectHairBox;
    @FXML
    private ChoiceBox<Ethnicity> suspectEthnicityBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    fillMultiboxes();
    }

    @FXML
    private void suspectAutoBtnPressed(ActionEvent actionEvent) {

    }


    private void fillMultiboxes(){
        suspectGenderBox.getItems().setAll(Gender.values());
        suspectBuildBox.getItems().setAll(Build.values());
        suspectDescGenderBox.getItems().setAll(Gender.values());
        suspectEyeBox.getItems().setAll(Color.eyeColor.values());
        suspectHairBox.getItems().setAll(Color.hairColor.values());
        suspectEthnicityBox.getItems().setAll(Ethnicity.values());
    }


}
