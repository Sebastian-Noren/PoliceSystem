package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassportController implements Initializable {

    private Parent fxml;
    @FXML
    private ImageView iconImage;
    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("image/swepustlogg.png");
        iconImage.setImage(image);
        applyForPassport();
    }

    public void applyForPassport() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../../../resources/view/main_window/applyForPassport.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}
