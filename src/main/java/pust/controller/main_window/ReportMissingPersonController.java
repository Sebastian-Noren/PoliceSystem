package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pust.model.enumerations.Build;
import pust.model.enumerations.Color;
import pust.model.enumerations.Ethnicity;
import pust.model.enumerations.Gender;
import pust.model.utility.AppConstant;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportMissingPersonController implements Initializable {

    @FXML
    private TextField caseIDField, eventStreetField, eventZipField, eventCityField, notifierFirstNameField,
            notifierLastNameField, notifierSSNField, notifierStreetField, notifierZIPField, notifierCityField,
            notifierPhoneField, missingFirstNameField, missingLastNameField, missingSSNField,
            missingStreetField, missingZIPField, missingCityField, missingPhoneField, suspectHeightField,
            suspectWeightField, policeIDField, policeNameField, PoliceSSNField;

    @FXML
    private TextArea characteristicsArea, clothesArea, reasonArea;
    @FXML
    private DatePicker missingDatePick;
    @FXML
    private ChoiceBox<Gender> notifierGenderBox;
    @FXML
    private ChoiceBox<Gender> missingGenderBox;
    @FXML
    private ChoiceBox<Build> missingBuildBox;
    @FXML
    private ChoiceBox<Gender> missingDescGenderBox;
    @FXML
    private ChoiceBox<Color.eyeColor> missingEyeBox;
    @FXML
    private ChoiceBox<Color.hairColor> missingHairBox;
    @FXML
    private ChoiceBox<Ethnicity> missingEthnicityBox;
    @FXML
    private Label pustLabel, boldLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notifierGenderBox.getItems().setAll(Gender.values());
        missingGenderBox.getItems().setAll(Gender.values());
        missingBuildBox.getItems().setAll(Build.values());
        missingDescGenderBox.getItems().setAll(Gender.values());
        missingEyeBox.getItems().setAll(Color.eyeColor.values());
        missingHairBox.getItems().setAll(Color.hairColor.values());
        missingEthnicityBox.getItems().setAll(Ethnicity.values());
    }

    public void notifierAutoBtnPressed(){
        notifierFirstNameField.setText(AppConstant.person.getFirstName());
        notifierLastNameField.setText(AppConstant.person.getSurname());
        notifierCityField.setText(AppConstant.person.getAddress().getCity());
        notifierPhoneField.setText(AppConstant.person.getPhoneNumber());
        notifierSSNField.setText(String.valueOf(AppConstant.person.getPersonalNumber().getPersonalNumber()));
        notifierStreetField.setText(AppConstant.person.getAddress().getStreet());
        notifierZIPField.setText(String.valueOf(AppConstant.person.getAddress().getZipCode()));
    }

    public void missingAutoBtnPressed(){
        missingFirstNameField.setText("Zara");
        missingLastNameField.setText("Larsson");
        missingCityField.setText("Stockholm");
        missingPhoneField.setText("0734454033");
        missingSSNField.setText("33123212");
        missingStreetField.setText("Star Road 5c");
        missingZIPField.setText("546 87");
    }
}
