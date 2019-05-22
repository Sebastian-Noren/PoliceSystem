package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pust.model.enumerations.Color;
import pust.model.enumerations.Gender;
import pust.model.utility.AppConstant;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportLostEntityController implements Initializable {

    @FXML
    public TextField caseIDField, eventStreetField, eventZipField, eventCityField, notifierFirstNameField,
            notifierLastNameField, notifierSSNField, notifierStreetField, notifierZIPField, notifierCityField,
            notifierPhoneField, manufacturerField, markingsField, materialField, modelField, productionField,
            policeIDField, policeNameField, PoliceSSNField;

    @FXML
    public TextArea characteristicsArea, areaArea;
    @FXML
    public DatePicker missingDatePick;
    @FXML
    public ChoiceBox<Gender> notifierGenderBox;

    @FXML
    public ChoiceBox<Color.hairColor> itemColorBox;

    @FXML
    public Label pustLabel, boldLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notifierGenderBox.getItems().setAll(Gender.values());
        itemColorBox.getItems().setAll(Color.hairColor.values());
    }

    public void notifierAutoBtnPressed(){
        notifierFirstNameField.setText(AppConstant.person.getFirstName());
        notifierLastNameField.setText(AppConstant.person.getSurname());
        notifierCityField.setText(AppConstant.person.getAddress().getCity());
        notifierPhoneField.setText(AppConstant.person.getPhoneNumber());
        notifierSSNField.setText(String.valueOf(AppConstant.person.getPersonalNumber()));
        notifierStreetField.setText(AppConstant.person.getAddress().getStreet());
        notifierZIPField.setText(String.valueOf(AppConstant.person.getAddress().getZipCode()));
    }
}
