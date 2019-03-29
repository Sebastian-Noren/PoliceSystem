import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle("Police System");
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("riper.css");
        window.setScene(scene);
        window.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
