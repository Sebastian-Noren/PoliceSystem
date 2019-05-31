package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pust.model.database_functionality.SQLDatabase;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.enumerations.Build;
import pust.model.enumerations.Color;
import pust.model.enumerations.Ethnicity;
import pust.model.enumerations.Gender;
import pust.model.utility.AppConstant;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ReportMissingPersonController implements Initializable {

    @FXML
    private TextField caseIDField, eventStreetField, eventZipField, eventCityField, notifierFirstNameField,
            notifierLastNameField, notifierSSNField, notifierStreetField, notifierZIPField, notifierCityField,
            notifierCountryField, notifierPhoneField, missingFirstNameField, missingLastNameField, missingSSNField,
            missingStreetField, missingZIPField, missingCityField, missingCountryField, missingPhoneField, missingHeightField,
            missingWeightField, policeIDField, policeFirstNameField, policeLastNameField, policeRankField;

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
    private Label notifierSSNLabel, missingSSNLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillChoiceBoxes();
        setPoliceInfo();
        caseIDField.setText(String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999)));
        notifierSSNLabel.setVisible(false);
        missingSSNLabel.setVisible(false);
        if (AppConstant.person != null) {
            notifierSSNField.setText(AppConstant.person.getPersonalNumber().getPersonalNumber());
        }
        // these check if the weight and height of the missing person are reasonable
        missingWeightField.focusedProperty().addListener((ov, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                checkWeight();
            }
        });
        missingHeightField.focusedProperty().addListener((ov, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                checkHeight();
            }
        });
    }


    public void notifierAutoBtnPressed() {
        String personSSN = notifierSSNField.getText().trim();
        if (personSSN.length() != 12 || !personSSN.matches("^[0-9]*$")) {
            notifierSSNLabel.setVisible(true);
        } else {
            AppConstant.person = new SelectPerson(notifierSSNField.getText()).loadPerson();
            if (AppConstant.person == null) {
                AppConstant.alertBoxInformation("Person not found", String.format
                        ("No person found in system with SSN: %s", notifierSSNField.getText()));
                notifierSSNField.clear();
                notifierSSNLabel.setVisible(false);
            } else {
                notifierSSNLabel.setVisible(false);
                fillNotifier();
            }
        }
    }

    public void missingAutoBtnPressed() {
        String personSSN = missingSSNField.getText().trim();
        if (personSSN.length() != 12 || !personSSN.matches("^[0-9]*$")) {
            missingSSNLabel.setVisible(true);
        } else {
            AppConstant.person2 = new SelectPerson(missingSSNField.getText()).loadPerson();
            if (AppConstant.person2 == null) {
                AppConstant.alertBoxInformation("Person not found", String.format
                        ("No person found in system with SSN: %s", missingSSNField.getText()));
                missingSSNField.clear();
                missingSSNLabel.setVisible(false);
            } else {
                missingSSNLabel.setVisible(false);
                fillMissing();
            }
        }
    }

    private void checkWeight() throws NumberFormatException {
        try {
            if (!missingWeightField.getText().matches("^[0-9]*$")) {
                AppConstant.alertBoxWarning("Letter Warning", "Use digits only when entering weight in kg");
                missingWeightField.clear();
            } else if (Integer.parseInt(missingWeightField.getText()) > 500) {
                AppConstant.alertBoxWarning("Fatness", missingWeightField.getText() + " kg? Nobody is that fat.");
                missingWeightField.clear();
            }
        } catch (NumberFormatException ex) {
            AppConstant.alertBoxWarning("Wrong Weight", "Weight entered incorrectly");
        }
    }

    private void checkHeight() {
        try {
            if (!missingHeightField.getText().matches("^[0-9]*$")) {
                AppConstant.alertBoxWarning("Letter Warning", "Use digits only when entering height in cm.");
                missingHeightField.clear();
            } else if (Integer.parseInt(missingHeightField.getText()) > 350) {
                AppConstant.alertBoxWarning("Giant Warning", missingHeightField.getText() + " cm tall? Call a giant-finder.");
                missingHeightField.clear();
            } else if (Integer.parseInt(missingHeightField.getText()) < 10) {
                AppConstant.alertBoxWarning("Embryo Warning", missingHeightField.getText() + " cm short? Check the abortion clinic.");
                missingHeightField.clear();
            }
        } catch (NumberFormatException ex) {
            AppConstant.alertBoxWarning("Wrong", "Height entered incorrectly");
        }
    }

    private void fillChoiceBoxes(){
        notifierGenderBox.getItems().setAll(Gender.values());
        missingGenderBox.getItems().setAll(Gender.values());
        missingBuildBox.getItems().setAll(Build.values());
        missingDescGenderBox.getItems().setAll(Gender.values());
        missingEyeBox.getItems().setAll(Color.eyeColor.values());
        missingHairBox.getItems().setAll(Color.hairColor.values());
        missingEthnicityBox.getItems().setAll(Ethnicity.values());
    }

    private void fillNotifier() {
        try {
            notifierFirstNameField.setText(AppConstant.person.getFirstName());
            notifierLastNameField.setText(AppConstant.person.getSurname());
            notifierPhoneField.setText(AppConstant.person.getPhoneNumber());
            notifierSSNField.setText(String.valueOf(AppConstant.person.getPersonalNumber().getPersonalNumber()));
            notifierStreetField.setText(AppConstant.person.getAddress().getStreet());
            notifierZIPField.setText(String.valueOf(AppConstant.person.getAddress().getZipCode()));
            notifierCityField.setText(AppConstant.person.getAddress().getCity());
            notifierCountryField.setText(AppConstant.person.getAddress().getCountry());
            notifierGenderBox.setValue(Gender.valueOf(AppConstant.person.getGender().toString()));
        } catch (NullPointerException ex) {
            AppConstant.alertBoxWarning("Alert", "Fill in missing information");
        }
    }

    private void fillMissing() {
        try {
            missingFirstNameField.setText(AppConstant.person2.getFirstName());
            missingLastNameField.setText(AppConstant.person2.getSurname());
            missingSSNField.setText(String.valueOf(AppConstant.person2.getPersonalNumber().getPersonalNumber()));
            missingPhoneField.setText(AppConstant.person2.getPhoneNumber());
            missingStreetField.setText(AppConstant.person2.getAddress().getStreet());
            missingZIPField.setText(String.valueOf(AppConstant.person2.getAddress().getZipCode()));
            missingCityField.setText(AppConstant.person2.getAddress().getCity());
            missingGenderBox.setValue(Gender.valueOf(AppConstant.person2.getGender().toString()));
            missingCountryField.setText(AppConstant.person.getAddress().getCountry());
        } catch (NullPointerException ex) {
            AppConstant.alertBoxWarning("Alert", "Fill in missing information");
        }
    }

    private void setPoliceInfo() {
        SQLDatabase sqlDatabase = new SQLDatabase();
        String ssn = sqlDatabase.getPolice(AppConstant.getCurrentUser());
        Person person = new SelectPerson(ssn).loadPerson();
        Employee employee = null;
        if (person instanceof Employee) {
            employee = (Employee) person;
        }
        policeFirstNameField.setText(person.getFirstName());
        policeLastNameField.setText(person.getSurname());
        policeIDField.setText(String.valueOf(employee.getId()));
        policeRankField.setText(employee.getTitle().toString());
    }

    public void submitBtnPressed() {
        AppConstant.alertBoxInformation("Report Submitted", "The report has been submitted.");
    }
}