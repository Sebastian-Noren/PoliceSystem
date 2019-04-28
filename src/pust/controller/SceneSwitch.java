package pust.controller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pust.model.utility.AppConstant;

import java.io.IOException;

/*
Model for switch scenes
 */

public class SceneSwitch {

    public void goToAdminCreate(Event event) {
        try {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/pust/view/AdminScreen.fxml"));
            Image image = new Image(getClass().getResourceAsStream("/swepustlogg.png"));
            primaryStage.getIcons().add(image);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle(AppConstant.getSOFTWARE_NAME());
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToLogin(Event event) {
        try {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/pust/view/LogInScreen.fxml"));
            Image image = new Image(getClass().getResourceAsStream("/swepustlogg.png"));
            primaryStage.getIcons().add(image);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(AppConstant.getSOFTWARE_NAME());
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
