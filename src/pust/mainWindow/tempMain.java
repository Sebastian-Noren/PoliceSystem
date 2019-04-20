package pust.mainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class tempMain  extends Application {

    private static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Image image = new Image(getClass().getResourceAsStream("/swepustlogg.png"));
        primaryStage.getIcons().add(image);
        Parent root = FXMLLoader.load(getClass().getResource("MainFrame.fxml"));

        Scene scene = new Scene(root);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



