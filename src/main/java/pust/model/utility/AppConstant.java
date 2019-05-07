package pust.model.utility;

/*
This class can hold all variable or methods that can be used everywhere in the program.
 */

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppConstant {
    private static final String SOFTWARE_NAME = "PUST GIS";
    private static final String DATABASE_NAME = "pustgis"; // TODO Change to new database name
    private static final String DATABASE_HOST = "localhost"; // TODO Change Server.
    private static String CURRENT_USER = ""; //Save the current user in the program
    private static String CURRENT_USER_PASS = ""; //save the current password in the program.
    public static String SAVE_FOLDER_PATH = "src/pust/images/";

    public static String getSOFTWARE_NAME() {
        return SOFTWARE_NAME;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static String getDatabaseHost() {
        return DATABASE_HOST;
    }

    public static String getCurrentUser() {
        return CURRENT_USER;
    }

    public static void setCurrentUser(String currentUser) {
        CURRENT_USER = currentUser;
    }

    public static String getCurrentUserPass() {
        return CURRENT_USER_PASS;
    }

    public static void setCurrentUserPass(String currentUserPass) {
        CURRENT_USER_PASS = currentUserPass;
    }

    public static boolean isFemale(int serialNumber) {
        return serialNumber % 2 == 0;
    }

    public static void switchScene(Event event, String changeScene) {
        try {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(AppConstant.class.getResource(changeScene));
            Image image = new Image(AppConstant.class.getResourceAsStream("/image/swepustlogg.png"));
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

