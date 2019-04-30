package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    private Parent fxml;

    @FXML
    private AnchorPane statisticsPane;

    @FXML
    private AnchorPane searchPane;

    @FXML
    private AnchorPane policeChiefViewPane;

    @FXML
    private AnchorPane viewWanted;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //loads up statisticsScene
        statisticsScene();
        //loads up searchScene
        searchScene();
        //loads up PoliceChiefViewScene
        policeChiefViewScene();
        //loads up view wanted people
        viewWanted();

    }
    public void statisticsScene(){
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../../../resources/view/main_window/Statistics.fxml"));
            statisticsPane.getChildren().removeAll();
            statisticsPane.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void searchScene(){

        try {
            fxml = FXMLLoader.load(getClass().getResource("../../../../resources/view/main_window/search.fxml"));
            searchPane.getChildren().removeAll();
            searchPane.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void policeChiefViewScene(){
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../../../resources/view/main_window/PoliceChief.fxml"));
            policeChiefViewPane.getChildren().removeAll();
            policeChiefViewPane.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void viewWanted(){
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../../../resources/view/main_window/ViewWantedScreen.fxml"));

            viewWanted.getChildren().removeAll();
            viewWanted.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
