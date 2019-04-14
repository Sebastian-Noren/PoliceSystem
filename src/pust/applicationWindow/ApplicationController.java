package pust.applicationWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {


    @FXML
    public  VBox vBox;



    @FXML
    public Pane multiScenes;

    public Parent fxml;


    @Override
    public void initialize(URL location, ResourceBundle resources) {




    }

    public void policeButton(){
        try {
            fxml = FXMLLoader.load(getClass().getResource("userButtons/PoliceButton.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }

    }

    public void policeChiefButton(){

        try {
            fxml = FXMLLoader.load(getClass().getResource("userButtons/PoliceChiefButton.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);
        } catch (IOException e1) {

        }
}
    public void applyScene(){

            try {

                Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/pust/applicationWindow/scenes/Apply.fxml"));
                multiScenes.getChildren().addAll(newLoadedPane);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

//    public void reportCrimeScene(){
//
//    }
//
//    public void reportLostPropertyScene(){
//
//    }
//
//    public void viewStatisticsScene(){
//
//    }
//
//    public void submitArrestReportScene(){
//
//    }
//
//    public void viewCriminalRecordsScene(){
//
//    }
//
//    public void concludeCaseScene(){
//
//    }
//
//    public void retrievePersonalInfoScene(){
//
//    }





}
