 package pust;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pust.model.utility.AppConstant;

public class Main extends Application {

    private static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {


        window = primaryStage;
        Image image = new Image(getClass().getResourceAsStream("/image/icon.png"));
        primaryStage.getIcons().add(image);
        Parent root = FXMLLoader.load(getClass().getResource("/view/LogInScreen.fxml"));
        window.setResizable(false);
        window.setTitle(AppConstant.getSOFTWARE_NAME());
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
        //When someone press X
        window.setOnCloseRequest(event -> System.exit(0));

    }

    public static void main(String[] args) {
        launch(args);

    }
}
