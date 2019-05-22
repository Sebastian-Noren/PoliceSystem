package pust.controller.main_window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import pust.model.enumerations.Build;
import pust.model.enumerations.Color;
import pust.model.enumerations.Ethnicity;
import pust.model.enumerations.Gender;
import pust.model.utility.AppConstant;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportArrestInCustodyController implements Initializable {

    @FXML
    public TextField suspectFirstNameField, suspectLastNameField, suspectSSNField,
            suspectStreetField, suspectZIPField, suspectCityField, suspectPhoneField, suspectHeightField,
            suspectWeightField;

    @FXML
    private ChoiceBox<Gender> suspectGenderBox;
    @FXML
    private ChoiceBox<Build> suspectBuildBox;
    @FXML
    private ChoiceBox<Color.eyeColor> suspectEyeBox;
    @FXML
    private ChoiceBox<Color.hairColor> suspectHairBox;
    @FXML
    private ChoiceBox<Ethnicity> suspectEthnicityBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    fillMultiboxes();
    setSuspectInit();
    }

    @FXML
    private void suspectAutoBtnPressed(ActionEvent actionEvent) {


    }

    private void fillMultiboxes(){
        suspectGenderBox.getItems().setAll(Gender.values());
        suspectBuildBox.getItems().setAll(Build.values());
        suspectEyeBox.getItems().setAll(Color.eyeColor.values());
        suspectHairBox.getItems().setAll(Color.hairColor.values());
        suspectEthnicityBox.getItems().setAll(Ethnicity.values());
    }

    private void setSuspectInit(){
        if (AppConstant.isSsnCheck()) {
            suspectFirstNameField.setText(AppConstant.person.getFirstName());
            suspectLastNameField.setText(AppConstant.person.getSurname());
            suspectSSNField.setText(AppConstant.person.getPersonalNumber().getPersonalNumber());
            suspectStreetField.setText(AppConstant.person.getAddress().getStreet());
            suspectZIPField.setText(String.valueOf(AppConstant.person.getAddress().getZipCode()));
            suspectCityField.setText(AppConstant.person.getAddress().getCity());
            suspectPhoneField.setText(AppConstant.person.getPhoneNumber());
            suspectGenderBox.setValue(Gender.valueOf(AppConstant.person.getGender().toString()));
            if (!(AppConstant.suspect == null)) {
                suspectHeightField.setText(String.valueOf(AppConstant.person.getHeight()));
                suspectWeightField.setText(String.valueOf(AppConstant.suspect.getWeight()));
                suspectBuildBox.setValue(Build.valueOf(AppConstant.suspect.getBuild().toString()));
                suspectEyeBox.setValue(Color.eyeColor.valueOf(AppConstant.suspect.getEyeColor().toString()));
                suspectHairBox.setValue(Color.hairColor.valueOf(AppConstant.suspect.getHairColor().toString()));
                suspectEthnicityBox.setValue(Ethnicity.valueOf(AppConstant.suspect.getEthnicity().toString()));
            }
        }
    }
}
