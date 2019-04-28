package pust;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pust.model.utility.AppConstant;

/*
 * To run this program Assert must be enabled.
 * 1. Edit the run configuration
 * 2. Under VM options enter: -ea
 * 3. Run
 */

public class Main extends Application {

    private static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        Image image = new Image(getClass().getResourceAsStream("/swepustlogg.png"));
        primaryStage.getIcons().add(image);
        Parent root = FXMLLoader.load(getClass().getResource("/pust/view/LogInScreen.fxml"));
        window.setResizable(false);
        window.setTitle(AppConstant.getSOFTWARE_NAME());
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
