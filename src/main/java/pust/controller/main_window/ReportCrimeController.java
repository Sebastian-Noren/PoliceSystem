package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportCrimeController implements Initializable {

    @FXML
    TextField caseIDField, crimeStreetField, crimeZipField, crimeCityField, notifierFirstNameField,
            notifierLastNameField, notifierSSNField, notifierStreetField, notifierZIPField, notifierCityField,
            notifierPhoneField, suspectFirstNameField, suspectLastNameField, suspectSSNField,
            suspectStreetField, suspectZIPField, suspectCityField, suspectPhoneField, suspectHeightField,
            suspectWeightField, policeIDField, policeNameField, PoliceSSNField;

    @FXML
    TextArea characteristicsArea, descriptionArea;

    @FXML
    DatePicker crimeDatePick, reportDatePick;

    @FXML
    ListView crimeIDList, notifierGenderList, suspectGenderList, supectBuildList, supectDescGenderList,
            supectEyeList, supectHairList, supectEthnicityList;

    @FXML
    Button notifierAutoBtn, suspectAutoBtn, descriptionSaveBtn, crimeSaveBtn, submitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reportCrime();
    }

    private void reportCrime() {
        try {
            FXMLLoader.load(getClass().getResource("/view/main_window/ReportCrime.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
