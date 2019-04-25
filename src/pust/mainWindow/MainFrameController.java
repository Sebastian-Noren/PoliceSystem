package pust.mainWindow;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {

    @FXML
    private VBox vBox;
    @FXML
    private Parent fxml;
    @FXML
    private ImageView imageView;
    @FXML
    private VBox vBoxText;
    @FXML
    private ImageView notifyImg;
    @FXML
    private ImageView notifyNumber;


    Timeline timeline;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //first notify number
        timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                ae -> firstNotify()));
        timeline.play();
        //scroll text containing two texts
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> scrollText()));
        timeline.play();
        //second notification matching second text
        timeline = new Timeline(new KeyFrame(
                Duration.millis(10000),
                ae ->  secondNotify()));
        timeline.play();
        //set image to null
        timeline = new Timeline(new KeyFrame(
                Duration.millis(18000),
                ae ->  notifyNumber.setImage(null)));
        timeline.play();




        Image image = new Image("user_accounts.png");
        imageView.setImage(image);

        Image image1 = new Image("swepustlogg.png");
        notifyImg.setImage(image1);
        try {
            fxml = FXMLLoader.load(getClass().getResource("View/standardWindow.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e1) {

        }
    }

    public void ReportTab() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("View/Report.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e1) {

        }
    }

    public void applyForIdentification() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("View/ApplyForIdentification.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void view() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("View/View.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void back() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("View/standardWindow.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e1) {

        }
    }

    public void firstNotify() {
        //set the notify image (number)
        Image notify = new Image("one.png");
        notifyNumber.setImage(notify);
    }

    public void secondNotify(){
        Image notify1 = new Image("two.png");
        notifyNumber.setImage(notify1);
    }

    public void scrollText() {

        //uploading the scrolling text
        try {
            fxml = FXMLLoader.load(getClass().getResource("View/ScrollText.fxml"));
            vBoxText.getChildren().removeAll();
            vBoxText.getChildren().setAll(fxml);

        } catch (IOException e1) {

        }
    }


}
