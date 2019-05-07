package pust.controller.main_window;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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


        Image image = new Image("/image/user_accounts.png");
        imageView.setImage(image);

        Image image1 = new Image("/image/swepustlogg.png");
        notifyImg.setImage(image1);
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/StandardWindow.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e1) {

        }
    }

    public void ReportTab() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/Report.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void applyForIdentification() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ApplyForIdentification.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void view() {
        try {

            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/View.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void back() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/StandardWindow.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException e1) {

        }
    }

    public void firstNotify() {
        //set the notify image (number)
        Image notify = new Image("image/one.png");
        notifyNumber.setImage(notify);
    }

    public void secondNotify(){
        Image notify1 = new Image("image/two.png");
        notifyNumber.setImage(notify1);
    }

    public void scrollText() {

        //uploading the scrolling text
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ScrollText.fxml"));
            vBoxText.getChildren().removeAll();
            vBoxText.getChildren().setAll(fxml);

        } catch (IOException e1) {

        }
    }

    //TODO DONT WORK WHYYYYYY??
    @FXML
    private void menuLogOut(Event actionEvent) {
//        String strSceneFXML = "/view/LogInScreen.fxml";
//        AppConstant.switchScene(actionEvent,strSceneFXML);
    }

    @FXML
    private void menuExit() {
        System.exit(0);
    }

}
