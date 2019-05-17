package pust.controller.main_window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
public class ReportCrimeController implements Initializable {

    @FXML
    public TextField caseIDField, crimeStreetField, crimeZipField, crimeCityField, notifierFirstNameField,
            notifierLastNameField, notifierSSNField, notifierStreetField, notifierZIPField, notifierCityField,
            notifierPhoneField, suspectFirstNameField, suspectLastNameField, suspectSSNField,
            suspectStreetField, suspectZIPField, suspectCityField, suspectPhoneField, suspectHeightField,
            suspectWeightField, policeIDField, policeNameField, PoliceSSNField;

    @FXML
    public TextArea characteristicsArea, descriptionArea;

    @FXML
    public DatePicker crimeDatePick, reportDatePick;

    @FXML
    public ChoiceBox crimeIDBox, notifierGenderBox, suspectGenderBox, suspectBuildBox, suspectDescGenderBox,
            suspectEyeBox, suspectHairBox, suspectEthnicityBox;

    @FXML
    private Button notifierAutoBtn, suspectAutoBtn, descriptionSaveBtn, crimeSaveBtn, submitBtn;

    @FXML
    public Label pustLabel, boldLabel;

    //this sets up the choiceBoxes, will be replaced by database info.
    private ObservableList<String> genderList = FXCollections.observableArrayList("M","F");
    private ObservableList<String> crimeIDList = FXCollections
            .observableArrayList("1","2","3","4");
    private ObservableList<String> buildList = FXCollections
            .observableArrayList("Small","Fat","Obese","Skinny");
    private ObservableList<String> eyeList = FXCollections
            .observableArrayList("Green","Blue","Brown","Beautiful");
    private ObservableList<String> hairList = FXCollections
            .observableArrayList("Blonde","Brown","Black","Purple");
    private ObservableList<String> ethnicityList = FXCollections
            .observableArrayList("White","Black","Yellow","Purple");

    //These control the buttons, so far only local values, will be filled by database.
    public void notifierAutoBtnPressed(){
        notifierFirstNameField.setText("Julius");
        notifierLastNameField.setText("Soutine");
        notifierCityField.setText("Stockholm");
        notifierPhoneField.setText("0734454072");
        notifierSSNField.setText("8112027431");
        notifierStreetField.setText("Gräsåkersvägen 2b");
        notifierZIPField.setText("178 51");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crimeIDBox.setItems(crimeIDList);
        notifierGenderBox.setItems(genderList);
        suspectGenderBox.setItems(genderList);
        suspectBuildBox.setItems(buildList);
        suspectDescGenderBox.setItems(genderList);
        suspectEyeBox.setItems(eyeList);
        suspectHairBox.setItems(hairList);
        suspectEthnicityBox.setItems(ethnicityList);
    }
}