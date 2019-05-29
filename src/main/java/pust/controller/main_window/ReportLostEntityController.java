package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pust.model.database_functionality.SQLDatabase;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.enumerations.Color;
import pust.model.enumerations.Gender;
import pust.model.utility.AppConstant;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ReportLostEntityController implements Initializable {

    @FXML
    private TextField caseIDField, eventStreetField, eventZipField, eventCityField, notifierFirstNameField,
            notifierLastNameField, notifierSSNField, notifierStreetField, notifierZIPField, notifierCityField,
            notifierPhoneField, manufacturerField, markingsField, materialField, modelField, productionField,
            policeIDField, policeFirstNameField, policeLastNameField, policeRankField;

    @FXML
    private TextArea characteristicsArea, areaArea;
    @FXML
    private DatePicker missingDatePick, reportDatePick;
    @FXML
    private ChoiceBox<Gender> notifierGenderBox;
    @FXML
    private ChoiceBox<Color.hairColor> itemColorBox;

    @FXML
    private Label notifierSSNLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPoliceInfo();
        notifierSSNLabel.setVisible(false);
        notifierGenderBox.getItems().setAll(Gender.values());
        itemColorBox.getItems().setAll(Color.hairColor.values());
        caseIDField.setText(String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999)));
        if (AppConstant.person != null) {
            notifierSSNField.setText(AppConstant.person.getPersonalNumber().getPersonalNumber());
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

    private void fillNotifier() {
        notifierFirstNameField.setText(AppConstant.person.getFirstName());
        notifierLastNameField.setText(AppConstant.person.getSurname());
        notifierCityField.setText(AppConstant.person.getAddress().getCity());
        notifierPhoneField.setText(AppConstant.person.getPhoneNumber());
        notifierSSNField.setText(String.valueOf(AppConstant.person.getPersonalNumber().getPersonalNumber()));
        notifierStreetField.setText(AppConstant.person.getAddress().getStreet());
        notifierZIPField.setText(String.valueOf(AppConstant.person.getAddress().getZipCode()));
        notifierGenderBox.setValue(Gender.valueOf(AppConstant.person.getGender().toString()));
    }

    public void submitBtnPressed() {
        AppConstant.alertBoxInformation("Report Submitted", "The report has been submitted.");
    }
}



