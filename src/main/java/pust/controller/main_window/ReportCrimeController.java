package pust.controller.main_window;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Person;
import pust.model.enumerations.*;
import pust.model.utility.AppConstant;

import java.net.URL;
import java.util.Optional;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillChoiceBoxes();
        notifierSSNLabel.setVisible(false);
        suspectSSNLabel.setVisible(false);
        if (AppConstant.person != null) {
            notifierSSNField.setText(AppConstant.person.getPersonalNumber().getPersonalNumber());
        }
    }

    private void fillChoiceBoxes() {
        notifierGenderBox.getItems().setAll(Gender.values());
        crimeIDBox.getItems().setAll(Crime.values());
        suspectGenderBox.getItems().setAll(Gender.values());
        suspectBuildBox.getItems().setAll(Build.values());
        suspectDescGenderBox.getItems().setAll(Gender.values());
        suspectEyeBox.getItems().setAll(Color.eyeColor.values());
        suspectHairBox.getItems().setAll(Color.hairColor.values());
        suspectEthnicityBox.getItems().setAll(Ethnicity.values());
    }

    //These control the buttons, so far only local values, will be filled by database.
    public void notifierAutoBtnPressed() {
        String personSSN = notifierSSNField.getText().trim();
        if (personSSN.isEmpty()) {
            enterSSN();
        } else if (personSSN.length() != 12 || !personSSN.matches("^[0-9]*$")) {
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

    private void fillNotifier() {
        notifierFirstNameField.setText(AppConstant.person.getFirstName());
        notifierLastNameField.setText(AppConstant.person.getSurname());
        notifierPhoneField.setText(AppConstant.person.getPhoneNumber());
        notifierSSNField.setText(String.valueOf(AppConstant.person.getPersonalNumber().getPersonalNumber()));
        notifierStreetField.setText(AppConstant.person.getAddress().getStreet());
        notifierZIPField.setText(String.valueOf(AppConstant.person.getAddress().getZipCode()));
        notifierCityField.setText(AppConstant.person.getAddress().getCity());
        notifierGenderBox.setValue(Gender.valueOf(AppConstant.person.getGender().toString()));

    }

    public void suspectAutoBtnPressed() {
        suspectFirstNameField.setText("Lex");
        suspectLastNameField.setText("Luthor");
        suspectCityField.setText("Metropolis");
        suspectPhoneField.setText("0734454033");
        suspectSSNField.setText("33123212");
        suspectStreetField.setText("Evil Road 5c");
        suspectZIPField.setText("546 87");
    }

    private void enterSSN() {
        TextInputDialog dialog = new TextInputDialog();
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/image/icon.png").toString()));
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("/view/basicStyleSheet.css").toExternalForm());
        dialog.setTitle("Enter SSN");
        dialog.setHeaderText(null);
        dialog.setContentText("SSN:");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && result.get().length() == 12) {
            notifierSSNField.setText(result.get());
            notifierAutoBtnPressed();
        } else if (result.isPresent() && result.get().length() != 12) {
            AppConstant.alertBoxInformation("Format", "SSN must have 12 characters");
        }
    }
}