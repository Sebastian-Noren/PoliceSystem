package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pust.model.administrative_functions.report_system.report_builder.CrimeReportBuilder;
import pust.model.database_functionality.SQLDatabase;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.enumerations.*;
import pust.model.utility.AppConstant;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ReportCrimeController implements Initializable {

    @FXML
    private TextField caseIDField, crimeStreetField, crimeZipField, crimeCityField, notifierFirstNameField,
            notifierLastNameField, notifierSSNField, notifierStreetField, notifierZIPField, notifierCityField,
            notifierPhoneField, notifierCountryField, suspectFirstNameField, suspectLastNameField, suspectSSNField,
            suspectStreetField, suspectZIPField, suspectCityField, suspectPhoneField, suspectCountryField, suspectHeightField,
            suspectWeightField, policeIDField, policeFirstNameField, policeLastNameField, policeRankField;

    @FXML
    private TextArea characteristicsArea, descriptionArea;
    @FXML
    private DatePicker crimeDatePick, reportDatePick;
    @FXML
    private ChoiceBox<Crime> crimeIDBox;
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
    private Label notifierSSNLabel, suspectSSNLabel;

    CrimeReportBuilder crimeReport = new CrimeReportBuilder();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillChoiceBoxes();
        setPoliceInfo();
        caseIDField.setText(String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999)));
        notifierSSNLabel.setVisible(false);
        suspectSSNLabel.setVisible(false);
        suspectSSNField.setText("193001015555");
        if (AppConstant.person != null) {
            notifierSSNField.setText(AppConstant.person.getPersonalNumber().getPersonalNumber());
        }
        // these check if the weight and height of the suspect is reasonable
        suspectWeightField.focusedProperty().addListener((ov, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                checkWeight();
            }
        });
        suspectHeightField.focusedProperty().addListener((ov, oldValue, newValue) -> {
            if (!newValue) { // focus lost
                checkHeight();
            }
        });
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

    private void fillChoiceBoxes() {
        crimeIDBox.getItems().setAll(Crime.values());
        notifierGenderBox.getItems().setAll(Gender.values());
        suspectGenderBox.getItems().setAll(Gender.values());
        suspectBuildBox.getItems().setAll(Build.values());
        suspectDescGenderBox.getItems().setAll(Gender.values());
        suspectEyeBox.getItems().setAll(Color.eyeColor.values());
        suspectHairBox.getItems().setAll(Color.hairColor.values());
        suspectEthnicityBox.getItems().setAll(Ethnicity.values());
    }

    private void fillNotifier() {
        notifierFirstNameField.setText(AppConstant.person.getFirstName());
        notifierLastNameField.setText(AppConstant.person.getSurname());
        notifierPhoneField.setText(AppConstant.person.getPhoneNumber());
        notifierSSNField.setText(String.valueOf(AppConstant.person.getPersonalNumber().getPersonalNumber()));
        notifierStreetField.setText(AppConstant.person.getAddress().getStreet());
        notifierZIPField.setText(String.valueOf(AppConstant.person.getAddress().getZipCode()));
        notifierCityField.setText(AppConstant.person.getAddress().getCity());
        notifierCountryField.setText(AppConstant.person.getAddress().getCountry());
        notifierGenderBox.setValue(Gender.valueOf(AppConstant.person.getGender().toString()));
    }

    // TODO resolve catch
    private void fillSuspect() {
        try {
            suspectFirstNameField.setText(AppConstant.person2.getFirstName());
            suspectLastNameField.setText(AppConstant.person2.getSurname());
            suspectSSNField.setText(String.valueOf(AppConstant.person2.getPersonalNumber().getPersonalNumber()));
            suspectPhoneField.setText(AppConstant.person2.getPhoneNumber());
            suspectStreetField.setText(AppConstant.person2.getAddress().getStreet());
            suspectZIPField.setText(String.valueOf(AppConstant.person2.getAddress().getZipCode()));
            suspectCityField.setText(AppConstant.person2.getAddress().getCity());
            suspectGenderBox.setValue(Gender.valueOf(AppConstant.person2.getGender().toString()));
            suspectCountryField.setText(AppConstant.person.getAddress().getCountry());
        } catch (NullPointerException ex) {
            AppConstant.alertBoxWarning("Alert", "Fill in missing information");
        }
    }

    private void checkWeight() throws NumberFormatException {
        try {
            if (!suspectWeightField.getText().matches("^[0-9]*$")) {
                AppConstant.alertBoxWarning("Letter Warning", "Use digits only when entering weight in kg");
                suspectWeightField.clear();
            } else if (Integer.parseInt(suspectWeightField.getText()) > 500) {
                AppConstant.alertBoxWarning("Fatness", suspectWeightField.getText() + " kg? Nobody is that fat.");
                suspectWeightField.clear();
            } else if (Integer.parseInt(suspectWeightField.getText()) < 10) {
                AppConstant.alertBoxWarning("Baby criminal", suspectWeightField.getText() + " kg? We don't report babies.");
                suspectWeightField.clear();
            }
        } catch (NumberFormatException ex) {
            AppConstant.alertBoxWarning("Wrong Weight", "Weight entered incorrectly");
        }
    }

    private void checkHeight() {
        try {
            if (!suspectHeightField.getText().matches("^[0-9]*$")) {
                AppConstant.alertBoxWarning("Letter Warning", "Use digits only when entering height in cm.");
                suspectHeightField.clear();
            } else if (Integer.parseInt(suspectHeightField.getText()) > 350) {
                AppConstant.alertBoxWarning("Giant Warning", suspectHeightField.getText() + " cm tall? Call a giant-slayer.");
                suspectHeightField.clear();
            } else if (Integer.parseInt(suspectHeightField.getText()) < 10) {
                AppConstant.alertBoxWarning("Embryo Warning", suspectHeightField.getText() + " cm short? Call the embryo police.");
                suspectHeightField.clear();
            }
        } catch (NumberFormatException ex) {
            AppConstant.alertBoxWarning("Wrong", "Height entered incorrectly");
        }
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

    public void suspectAutoBtnPressed() {
        String personSSN = suspectSSNField.getText().trim();
        if (personSSN.length() != 12 || !personSSN.matches("^[0-9]*$")) {
            suspectSSNLabel.setVisible(true);
        } else {
            AppConstant.person2 = new SelectPerson(suspectSSNField.getText()).loadPerson();
            if (AppConstant.person2 == null) {
                AppConstant.alertBoxInformation("Person not found", String.format
                        ("No person found in system with SSN: %s", suspectSSNField.getText()));
                suspectSSNField.clear();
                suspectSSNLabel.setVisible(false);
            } else {
                suspectSSNLabel.setVisible(false);
                fillSuspect();
            }
        }
    }

    // check if any fields are empty before confirming
    public void submitBtnPressed() {
        crimeReport.theNotifier(AppConstant.person);



        AppConstant.alertBoxInformation("Report Submitted", "The report has been submitted.");
    }
}