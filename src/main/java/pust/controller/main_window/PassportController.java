package pust.controller.main_window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassportController implements Initializable {


    @FXML
    private ImageView iconImage;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button nextBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("image/swepustlogg.png");
        iconImage.setImage(image);

    //set button on action
    nextBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            applyForPassport();
        }
    });


    }

    public void applyForPassport() {
        try {
            //set new scene to anchorPane named pane
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/main_window/applyForPassport.fxml"));
            //insert pane to current anchorPane
            anchorPane.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
