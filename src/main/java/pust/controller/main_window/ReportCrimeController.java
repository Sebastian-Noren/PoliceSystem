package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportCrimeController implements Initializable {

    private Parent fxml;

    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reportCrime();
    }


    private void reportCrime() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ReportCrime.fxml"));

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
