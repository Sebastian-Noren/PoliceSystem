package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pust.model.administrative_functions.ItemReportReceipt;
import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.record.Record;
import pust.model.administrative_functions.report_system.report.MissingItemReport;
import pust.model.administrative_functions.report_system.report_builder.MissingItemReportBuilder;
import pust.model.database_functionality.SQLDatabase;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.*;
import pust.model.enumerations.Color;
import pust.model.enumerations.Gender;
import pust.model.utility.AppConstant;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ReportLostEntityController implements Initializable {

    @FXML
    private TextField caseIDField, eventStreetField, eventZipField, eventCityField, notifierFirstNameField,
            notifierLastNameField, notifierSSNField, notifierStreetField, notifierZIPField, notifierCityField,
            notifierPhoneField, notifierCountryField, manufacturerField, markingsField, materialField, modelField, productionField,
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

    // notifier
    private String notifierFirstName = AppConstant.person.getFirstName();
    private String notifierLastName = AppConstant.person.getSurname();
    private String notifierPhone = AppConstant.person.getPhoneNumber();
    private Enum notifierGender = AppConstant.person.getGender();
    private Record notifierCrimeRecord = AppConstant.person.getCrimeRecord();
    private Identification notifierID = AppConstant.person.getIdentification();
    private PersonalNumber notifierSSN = AppConstant.person.getPersonalNumber();
    private int notifierHeight = AppConstant.person.getHeight();
    private boolean notifierWanted = AppConstant.person.isWanted();
    private boolean notifierMissing = AppConstant.person.isMissing();
    private boolean notifierCustody = AppConstant.person.isInCustody();
    private boolean notifierSuspect = AppConstant.person.isSuspect();
    private String notifierStreet = AppConstant.person.getAddress().getStreet();
    private int notifierZip = AppConstant.person.getAddress().getZipCode();
    private String notifierCity = AppConstant.person.getAddress().getCity();
    private String country = "Sweden";
    private Address notifierAddress = new Address(notifierStreet, notifierZip, notifierCity, country);

    // officer
    private String officerFirstName = AppConstant.employee.getFirstName();
    private String officerSurname = AppConstant.employee.getSurname();
    private PersonalNumber officerPersonalNumber = AppConstant.employee.getPersonalNumber();
    private Address officerAddress = AppConstant.employee.getAddress();
    private Record officerCrimeRecord = AppConstant.employee.getCrimeRecord();
    private int officerHeight = AppConstant.employee.getHeight();
    private Identification officerIdentification = AppConstant.employee.getIdentification();
    private String officerPhoneNumber = AppConstant.employee.getPhoneNumber();
    private Enum officerGender = AppConstant.employee.getGender();
    private boolean officerIsWanted = AppConstant.employee.isWanted();
    private boolean officerIsMissing = AppConstant.employee.isMissing();
    private boolean officerInCustody = AppConstant.employee.isInCustody();
    private boolean officerIsSuspect = AppConstant.employee.isSuspect();
    private int officerSalary = AppConstant.employee.getSalary();
    private Enum officerTitle = AppConstant.employee.getTitle();
    private int officerId = AppConstant.employee.getId();
    private String officerUserName = AppConstant.employee.getUserName();
    private String officerPassword = AppConstant.employee.getPassword();
    private String officerEmail = AppConstant.employee.getEmail();

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
        try {
            policeFirstNameField.setText(officerFirstName);
            policeLastNameField.setText(person.getSurname());
            policeIDField.setText(String.valueOf(employee.getId()));
            policeRankField.setText(employee.getTitle().toString());
        } catch (NullPointerException ex) {
            AppConstant.alertBoxInformation("Alert", "Enter ID");
        }
    }

    private void fillNotifier() {
        notifierFirstNameField.setText(notifierFirstName);
        notifierLastNameField.setText(notifierLastName);
        notifierCityField.setText(notifierCity);
        notifierPhoneField.setText(notifierPhone);
        notifierSSNField.setText(AppConstant.person.getPersonalNumber().getPersonalNumber());
        notifierStreetField.setText(notifierStreet);
        notifierZIPField.setText(String.valueOf(notifierZip));
        notifierGenderBox.setValue(Gender.valueOf(AppConstant.person.getGender().toString()));
    }

    private MissingItemReport generateMissingItemReport() {
        // report
        String characteristics = characteristicsArea.getText();
        String area = areaArea.getText();
        String productionNumber = productionField.getText();
        String model = modelField.getText();
        String material = materialField.getText();
        String manufacturer = manufacturerField.getText();
        String marking = markingsField.getText();
        String caseID = caseIDField.getText();
        LocalDate currentDate = reportDatePick.getValue();
        LocalDate eventDate = missingDatePick.getValue();
        Enum color = itemColorBox.getValue();

        // TODO see so that only numbers can be entered in ZIP
        // event address
        String eventStreet = eventStreetField.getText();
        int eventZip = Integer.parseInt(eventZipField.getText());
        String eventCity = eventCityField.getText();
        Address eventAddress = new Address(eventStreet, eventZip, eventCity, country);

        Notifier notifier = new Notifier(notifierFirstName, notifierLastName, notifierSSN, notifierAddress, notifierCrimeRecord, notifierHeight,
                notifierID, notifierPhone, notifierGender, notifierWanted, notifierMissing, notifierCustody, notifierSuspect);

        Police police = new Police(officerFirstName, officerSurname, officerPersonalNumber, officerAddress, officerCrimeRecord, officerHeight, officerIdentification,
                officerPhoneNumber, officerGender, officerIsWanted, officerIsMissing, officerInCustody, officerIsSuspect, officerSalary, officerTitle,
                officerId, officerUserName, officerPassword, officerEmail);

        return (MissingItemReport) new MissingItemReportBuilder()
                .withSpecificCharacteristics(characteristics)
                .withProductionNumber(productionNumber)
                .withModel(model)
                .withMaterial(material)
                .withMarking(marking)
                .withManufacturer(manufacturer)
                .withColor(color)
                .withAreaOfUse(area)
                .hasRef(caseID)
                .isCurrentDate(currentDate)
                .wasTimeAndDateOfEvent(eventDate)
                .wasPlaceOfEvent(eventAddress)
                .theNotifier(notifier)
                .hasAdministrativeOfficer(police)
                .build();
    }

    private boolean checkEmpty() {

        StringBuilder empty = new StringBuilder();

        // Confirms fields are filled out
        if (caseIDField.getText().trim().isEmpty()) {
            empty.append("Enter a case ID.\n");
        }
        if (reportDatePick.getValue() == null) {
            empty.append("Enter date reported.\n");
        }
        if (missingDatePick.getValue() == null) {
            empty.append("Enter a date occured.\n");
        }
        if (eventStreetField.getText().trim().isEmpty()) {
            empty.append("Enter a street address of event.\n");
        }
        if (eventCityField.getText().trim().isEmpty()) {
            empty.append("Enter the city of the event.\n");
        }
        if (eventZipField.getText().trim().isEmpty()) {
            empty.append("Enter the ZIP code of the event.\n");
        }
        if (notifierFirstNameField.getText().trim().isEmpty()) {
            empty.append("Enter the notifiers first name.\n");
        }
        if (notifierLastNameField.getText().trim().isEmpty()) {
            empty.append("Enter the notifiers last name.\n");
        }
        if (notifierPhoneField.getText().trim().isEmpty()) {
            empty.append("Enter the notifiers phone number.\n");
        }
        if (notifierGenderBox.toString().trim().isEmpty()) {
            empty.append("Enter the notifiers gender.\n");
        }
        if (notifierStreetField.getText().trim().isEmpty()) {
            empty.append("Enter the notifiers street address.\n");
        }
        if (notifierZIPField.getText().trim().isEmpty()) {
            empty.append("Enter the notifiers zip number.\n");
        }
        if (notifierCountryField.getText().trim().isEmpty()) {
            empty.append("Enter the notifiers country.\n");
        }
        if (characteristicsArea.getText().trim().isEmpty()) {
            empty.append("Enter the characteristics of the item.\n");
        }
        if (areaArea.getText().trim().isEmpty()) {
            empty.append("Enter the area of use of the item.\n");
        }
        if (manufacturerField.getText().trim().isEmpty()) {
            empty.append("Enter the manufacturer of the item.\n");
        }
        if (markingsField.getText().trim().isEmpty()) {
            empty.append("Enter the items markings.\n");
        }
        if (materialField.getText().trim().isEmpty()) {
            empty.append("Enter the items material.\n");
        }
        if (modelField.getText().trim().isEmpty()) {
            empty.append("Enter the items model.\n");
        }
        if (productionField.getText().trim().isEmpty()) {
            empty.append("Enter the items production number.\n");
        }
        if (itemColorBox.toString().trim().isEmpty()) {
            empty.append("Enter the items color.\n");
        }

        // If missing information is found, show the error messages and return false
        if (empty.length() > 0) {
            AppConstant.alertBoxWarning("Empty", empty.toString());
            return false;
        }
        return true;
    }

    public void submitBtnPressed() {
        if (checkEmpty()) {
            ItemReportReceipt itemReceipt = new ItemReportReceipt(generateMissingItemReport(),"files/pdf/horse.pdf");
            itemReceipt.createPdf("test");
            AppConstant.alertBoxInformation("Report Submitted", "The report has been submitted.");
        }
    }
}