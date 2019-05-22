package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplyForIdentificationController implements Initializable {

    private Parent fxml;

    @FXML
    private AnchorPane IDPane;

    @FXML
    private AnchorPane passportPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IDScene();
        passportScene();
    }

    public void IDScene() {

        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/PersonalInformation.fxml"));
            IDPane.getChildren().removeAll();
            IDPane.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void passportScene() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/Passport.fxml"));
            passportPane.getChildren().removeAll();
            passportPane.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
