package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pust.model.administrative_functions.ItemReportReceipt;
import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.record.Record;
import pust.model.administrative_functions.report_system.report.MissingItemReport;
import pust.model.administrative_functions.report_system.report_builder.MissingItemReportBuilder;
import pust.model.database_functionality.SQLDatabase;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.*;
import pust.model.entity.entity_builder.NotifierBuilder;
import pust.model.entity.entity_builder.PoliceBuilder;
import pust.model.enumerations.Color;
import pust.model.enumerations.Gender;
import pust.model.enumerations.Title;
import pust.model.utility.AppConstant;
import pust.model.utility.SendMail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ReportLostEntityController implements Initializable {

    @FXML
    private TextField caseIDField,
            eventStreetField, eventZipField, eventCityField,
            notifierFirstNameField, notifierLastNameField, notifierSSNField,
            notifierStreetField, notifierZIPField, notifierCityField, notifierPhoneField,
            notifierCountryField,
            objectTypeField, quantityField, currencyField, valueField,
            policeIDField, policeFirstNameField, policeLastNameField;

    @FXML
    private TextArea characteristicsArea, areaArea;
    @FXML
    private DatePicker missingDatePick, reportDatePick;
    @FXML
    private ChoiceBox<Gender> notifierGenderBox;
    @FXML
    private ChoiceBox<Color.hairColor> itemColorBox;
    @FXML
    private ChoiceBox<Title> policeRankField;

    @FXML
    private Label notifierSSNLabel;

    private ArrayList<TextField> textFields;

    // notifier
    private String notifierFirstName = AppConstant.person.getFirstName();
    private String notifierLastName = AppConstant.person.getSurname();
    private String notifierPhone = AppConstant.person.getPhoneNumber();
    private Gender notifierGender = AppConstant.person.getGender();
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
    private Gender officerGender = AppConstant.employee.getGender();
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
        policeRankField.getItems().setAll(Title.values());
        caseIDField.setText(String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999)));
        if (AppConstant.person != null) {
            notifierSSNField.setText(AppConstant.person.getPersonalNumber().getPersonalNumber());
        }
        addTextFieldsToList();
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

    public void submitBtnPressed() {
        if (checkInput()) {
            ItemReportReceipt itemReceipt = new ItemReportReceipt(generateMissingItemReport());
            AppConstant.alertBoxInformation("Report Submitted", "The report has been submitted.");
            mailReport();
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
            policeFirstNameField.setText(person.getFirstName());
            policeLastNameField.setText(person.getSurname());
            policeIDField.setText(String.valueOf(employee.getId()));
            policeRankField.setValue(Title.valueOf(employee.getTitle().toString()));
        } catch (NullPointerException ex) {
            AppConstant.alertBoxInformation("Alert", "Enter ID");
        }
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

    private MissingItemReport generateMissingItemReport() {
        /*
         * The names and values in some of the text fields are not represented
         * very well anymore by the variables contained within the
         * MissingItemReport object. This should be changed //FIXME
         */
        // report
        String characteristics = characteristicsArea.getText();
        String area = areaArea.getText();
        String productionNumber = valueField.getText();
        String model = currencyField.getText();
        String manufacturer = objectTypeField.getText();
        String marking = quantityField.getText();
        String caseID = caseIDField.getText();
        LocalDate currentDate = reportDatePick.getValue();
        LocalDate eventDate = missingDatePick.getValue();
        Enum color = itemColorBox.getValue();
        String eventStreet = eventStreetField.getText();
        int eventZip = Integer.parseInt(eventZipField.getText());
        String eventCity = eventCityField.getText();
        Address eventAddress = new Address(eventStreet, eventZip, eventCity, country);
        Notifier notifier = createNotifier();
        Police police = createPolice();

        return (MissingItemReport) new MissingItemReportBuilder()
                .withSpecificCharacteristics(characteristics)
                .withProductionNumber(productionNumber)
                .withModel(model)
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

    private boolean checkInput() {
        if (AppConstant.isInteger(eventZipField.getText())) {
            return true;
        } else {
            AppConstant.alertBoxWarning("Wrong format", "The zip code may only contain digits");
            eventZipField.requestFocus();
            return false;
        }
    }

    private boolean isEmpty() {
        boolean hasData = true;
        for (int i = 0; i < textFields.size(); i++) {
            if (textFields.get(i) == null) {
                AppConstant.alertBoxWarning("Empty", "Enter "
                        + textFields.get(i).getPromptText().toLowerCase());
                hasData = false;
            }
        }
        return hasData;
    }

    private void addTextFieldsToList() {
        textFields = new ArrayList<>();
        textFields.add(caseIDField);
        textFields.add(eventStreetField);
        textFields.add(eventZipField);
        textFields.add(eventCityField);
        textFields.add(notifierFirstNameField);
        textFields.add(notifierLastNameField);
        textFields.add(notifierSSNField);
        textFields.add(notifierStreetField);
        textFields.add(notifierZIPField);
        textFields.add(notifierCityField);
        textFields.add(notifierPhoneField);
        textFields.add(notifierCountryField);
        textFields.add(objectTypeField);
        textFields.add(quantityField);
        textFields.add(currencyField);
        textFields.add(valueField);
        textFields.add(policeIDField);
        textFields.add(policeFirstNameField);
        textFields.add(policeLastNameField);
    }

    private void mailReport() {
        SendMail sendmail = new SendMail();
        TextInputDialog dialog = new TextInputDialog();
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/image/icon.png").toString()));
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/view/basicStyleSheet.css").toExternalForm());
        dialog.setTitle("Send Report");
        dialog.setHeaderText("Enter the e-mail to which \nyou would like to send your report.");
        dialog.setContentText("E-mail:");
        Optional<String> result = dialog.showAndWait();
        String messageText = (", Here is the police report you filed concerning your lost item, ");

        try {
            if (result.isPresent()) {
                String subject = "PUST Password Reset";
                String emailResult = result.get().trim();

                if (validEmail(emailResult)) {
                    String message = "Hello " + emailResult + messageText;
                    String attachment = this.getClass().getResource("/pdf/" + generateMissingItemReport().getRef() + ".png").getPath();

                    sendmail.generateAndSendEmail(emailResult, subject, message, attachment);

                    AppConstant.alertBoxInformation("E-mail sent", "Check your inbox, we have sent you a new password");
                } else {
                    AppConstant.alertBoxInformation("Warning", "You did not enter a valid e-mail address");
                }
            }
        } catch (RuntimeException | MessagingException ex) {
            AppConstant.alertBoxWarning("Warning", "Something went horribly wrong");
        }
    }

    private boolean validEmail(String email) {
        boolean valid = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            valid = false;
        }
        return valid;
    }

    private Notifier createNotifier() {
        return (Notifier) new NotifierBuilder()
                .withFirstName(notifierFirstName)
                .withSurname(notifierLastName)
                .withPersonalNumber(notifierSSN)
                .withAddress(notifierAddress)
                .withCrimeRecord(notifierCrimeRecord)
                .withHeight(notifierHeight)
                .withIdentification(notifierID)
                .withPhoneNumber(notifierPhone)
                .withGender(notifierGender)
                .isWanted(notifierWanted)
                .isMissing(notifierMissing)
                .inCustody(notifierCustody)
                .isSuspect(notifierSuspect)
                .build();
    }

    private Police createPolice() {
        return (Police) new PoliceBuilder()
                .withSalary(officerSalary)
                .withTitle(officerTitle)
                .withId(officerId)
                .withUserName(officerUserName)
                .withPassword(officerPassword)
                .withEmail(officerEmail)
                .withFirstName(officerFirstName)
                .withSurname(officerSurname)
                .withPersonalNumber(officerPersonalNumber)
                .withAddress(officerAddress)
                .withCrimeRecord(officerCrimeRecord)
                .withHeight(officerHeight)
                .withIdentification(officerIdentification)
                .withPhoneNumber(officerPhoneNumber)
                .withGender(officerGender)
                .isWanted(officerIsWanted)
                .isMissing(officerIsMissing)
                .inCustody(officerInCustody)
                .isSuspect(officerIsSuspect)
                .build();
    }
}