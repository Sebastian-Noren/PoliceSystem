package pust.controller.main_window;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ReportCrimeController implements Initializable {

    @FXML
    private TextField caseIDField, crimeStreetField, crimeZipField, crimeCityField, notifierFirstNameField,
            notifierLastNameField, notifierSSNField, notifierStreetField, notifierZIPField, notifierCityField,
            notifierPhoneField, suspectFirstNameField, suspectLastNameField, suspectSSNField,
            suspectStreetField, suspectZIPField, suspectCityField, suspectPhoneField, suspectHeightField,
            suspectWeightField, policeIDField, policeNameField, PoliceSSNField;

    @FXML
    private TextArea characteristicsArea, descriptionArea;
    @FXML
    private DatePicker crimeDatePick, reportDatePick;
    @FXML
    private ChoiceBox<String> crimeIDBox;
    @FXML
    private ChoiceBox<Gender> notifierGenderBox;
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
    @FXML
    private Label pustLabel, boldLabel;

    private ObservableList<String> crimeIDList = FXCollections
            .observableArrayList("1","2","3","4");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notifierGenderBox.getItems().setAll(Gender.values());
        crimeIDBox.setItems(crimeIDList);
        suspectGenderBox.getItems().setAll(Gender.values());
        suspectBuildBox.getItems().setAll(Build.values());
        suspectDescGenderBox.getItems().setAll(Gender.values());
        suspectEyeBox.getItems().setAll(Color.eyeColor.values());
        suspectHairBox.getItems().setAll(Color.hairColor.values());
        suspectEthnicityBox.getItems().setAll(Ethnicity.values());
    }

    //These control the buttons, so far only local values, will be filled by database.
    public void notifierAutoBtnPressed(){
        notifierFirstNameField.setText(AppConstant.person.getFirstName());
        notifierLastNameField.setText(AppConstant.person.getSurname());
        notifierCityField.setText(AppConstant.person.getAddress().getCity());
        notifierPhoneField.setText(AppConstant.person.getPhoneNumber());
        notifierSSNField.setText(String.valueOf(AppConstant.person.getPersonalNumber().getPersonalNumber()));
        notifierStreetField.setText(AppConstant.person.getAddress().getStreet());
        notifierZIPField.setText(String.valueOf(AppConstant.person.getAddress().getZipCode()));
    }

    public void suspectAutoBtnPressed(){
        suspectFirstNameField.setText("Lex");
        suspectLastNameField.setText("Luthor");
        suspectCityField.setText("Metropolis");
        suspectPhoneField.setText("0734454033");
        suspectSSNField.setText("33123212");
        suspectStreetField.setText("Evil Road 5c");
        suspectZIPField.setText("546 87");
    }
}