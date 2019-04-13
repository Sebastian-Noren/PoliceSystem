package pust;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/*
Model for switch scenes
 */

public class SceneSwitch {

    void switchS(Event event) {
        try {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/pust/adminCreate/Admins.fxml"));
            Image image = new Image(getClass().getResourceAsStream("/swepustlogg.png"));
            primaryStage.getIcons().add(image);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(AppConstant.SOFTWARE_NAME);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
