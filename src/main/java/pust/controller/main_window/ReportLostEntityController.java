package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pust.model.enumerations.Color;
import pust.model.enumerations.Gender;
import pust.model.utility.AppConstant;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportLostEntityController implements Initializable {

    @FXML
    private TextField caseIDField, eventStreetField, eventZipField, eventCityField, notifierFirstNameField,
            notifierLastNameField, notifierSSNField, notifierStreetField, notifierZIPField, notifierCityField,
            notifierPhoneField, manufacturerField, markingsField, materialField, modelField, productionField,
            policeIDField, policeNameField, PoliceSSNField;

    @FXML
    private TextArea characteristicsArea, areaArea;
    @FXML
    private DatePicker missingDatePick;
    @FXML
    private ChoiceBox<Gender> notifierGenderBox;
    @FXML
    private ChoiceBox<Color.hairColor> itemColorBox;

    @FXML
    private Label pustLabel, boldLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notifierGenderBox.getItems().setAll(Gender.values());
        itemColorBox.getItems().setAll(Color.hairColor.values());
    }

    public void notifierAutoBtnPressed(){
        try {
            notifierFirstNameField.setText(AppConstant.person.getFirstName());
            notifierLastNameField.setText(AppConstant.person.getSurname());
            notifierCityField.setText(AppConstant.person.getAddress().getCity());
            notifierPhoneField.setText(AppConstant.person.getPhoneNumber());
            notifierSSNField.setText(String.valueOf(AppConstant.person.getPersonalNumber().getPersonalNumber()));
            notifierStreetField.setText(AppConstant.person.getAddress().getStreet());
            notifierZIPField.setText(String.valueOf(AppConstant.person.getAddress().getZipCode()));
        } catch (RuntimeException ex) {
            System.out.println("huh");
        }
    }
}
