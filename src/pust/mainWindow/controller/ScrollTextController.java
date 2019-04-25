package pust.mainWindow.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ScrollTextController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //first text matching first notifier
        Timeline timeline;
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> movingText()));
        timeline.play();

        //second text matching second notifier
        timeline = new Timeline(new KeyFrame(
                Duration.millis(9000),
                ae -> movingTextTwo()));
        timeline.play();
    }
    @FXML
    private VBox VboxText;


    public void movingText() {
        // Create the Text
        Text text = new Text("A Scrolling Text!");
        // Set the Font of the Text
        text.setFont(Font.font(15));
        text.setStyle("-fx-text-color:#ee0c1b");

        // add text inside the vBox
        VboxText.getChildren().addAll(text);

        //set style to vbox
        VboxText.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: white;");

        TranslateTransition translateTransition = new TranslateTransition();
        //Duration of text movement
        translateTransition.setDuration(Duration.seconds(5));
        //to what position in the X-axis the text should move
        translateTransition.setToX(380);
        //autoReverse
        //translateTransition.setAutoReverse(true);
        //times the text will repeat
        translateTransition.setCycleCount(3);
        //text inside a node inside Vbox that will move
        translateTransition.setNode(text);
        translateTransition.play();

        Timeline timeline;
        timeline = new Timeline(new KeyFrame(

                Duration.millis(8000),
                //add marker metoden som lägger till en marker
                ae -> VboxText.getChildren().removeAll(text)));
        timeline.play();

    }

    public void movingTextTwo() {
        // Create the Text
        Text text = new Text("Another random text that is much longer than the other");
        // Set the Font of the Text
        text.setFont(Font.font(15));
        // add text inside the vBox
        VboxText.getChildren().addAll(text);
        VboxText.setPrefSize(VboxText.getPrefWidth() + 65, VboxText.getHeight());

        //set style to vbox
        VboxText.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: white;");

        TranslateTransition translateTransition = new TranslateTransition();
        //Duration of text movement
        translateTransition.setDuration(Duration.seconds(5));
        //to what position in the X-axis the text should move
        translateTransition.setToX(600); //190
        //autoReverse
        //translateTransition.setAutoReverse(true);
        //times the text will repeat
        translateTransition.setCycleCount(3);
        //text inside a node inside Vbox that will move
        translateTransition.setNode(text);
        translateTransition.play();
        Timeline timeline;
        timeline = new Timeline(new KeyFrame(
                Duration.millis(8000),
                //add marker metoden som lägger till en marker
                ae -> VboxText.getChildren().removeAll(text)));
        timeline.play();

    }

}
