package pust.applicationWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

 /*
    ApplicationController is under construction!
  */

public class ApplicationController implements Initializable {
    @FXML
    public VBox vBox;
    @FXML
    public Pane multiScenes;
    public Parent fxml;
    @FXML
    private Button policeBtn;
    @FXML
    private Button policeChiefBtn;

    @Override

    public void initialize(URL location, ResourceBundle resources) {

        policeBtn.setVisible(false);
        policeChiefBtn.setVisible(false);

    }

    public void policeButton() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("userButtons/PoliceButton.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }

    }

    public void policeChiefButton() {

        try {
            fxml = FXMLLoader.load(getClass().getResource("userButtons/PoliceChiefButton.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }
    }

    public void applyScene() {

        try {
            fxml = FXMLLoader.load(getClass().getResource("scenes/Apply.fxml"));
            multiScenes.getChildren().removeAll();
            multiScenes.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }

    }

    public void reportCrimeScene() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("scenes/ReportCrime.fxml"));
            multiScenes.getChildren().removeAll();
            multiScenes.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }

    }

    public void reportLostPropertyScene() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("scenes/ReportLostProperty.fxml"));
            multiScenes.getChildren().removeAll();
            multiScenes.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }

    }

    public void viewStatisticsScene() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("scenes/ViewStatistics.fxml"));
            multiScenes.getChildren().removeAll();
            multiScenes.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }

    }

    public void submitArrestReportScene() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("scenes/SubmitArrestReport.fxml"));
            multiScenes.getChildren().removeAll();
            multiScenes.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }

    }

    public void viewCriminalRecordsScene() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("scenes/ViewCriminalRecords.fxml"));
            multiScenes.getChildren().removeAll();
            multiScenes.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }

    }

    public void concludeCaseScene() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("scenes/ConcludeCase.fxml"));
            multiScenes.getChildren().removeAll();
            multiScenes.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }

    }

    public void retrievePersonalInfoScene() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("scenes/RetrievePersonal.fxml"));
            multiScenes.getChildren().removeAll();
            multiScenes.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }

    }


}
