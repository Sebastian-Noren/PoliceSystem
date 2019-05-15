package pust.controller.main_window;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    @FXML
    private Region region;
    private int i = 0;


    Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RandomCrimeSpot randomCrimeSpot = new RandomCrimeSpot();

//----------------------------------------------------------------------------------------------------------------------
        //First ScrollText  //10 sec between each
        timeline = new Timeline(new KeyFrame(
                Duration.seconds(2),
                //insert specific text here AND also call method to get latLong and marker description to send to google maps
                ae -> scrollText(randomCrimeSpot.getCrimeMark()[0].getScrolltextDescription())));

//----------------------------------------------------------------------------------------------------------------------

        timeline.play();
        //second notify + text
        timeline = new Timeline(new KeyFrame(
                Duration.seconds(17),
                ae -> scrollText(randomCrimeSpot.getCrimeMark()[1].getScrolltextDescription())));
        timeline.play();
//----------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------

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


    public void googleMaps() {
        notifyNumber.setImage(null);
        System.out.println("third time, remove notification");

        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/GoogleMaps.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);


        } catch (IOException e) {
            e.printStackTrace();

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


    public void scrollText(String crimeDescription) {

        i++;
        switch (i) {
            case 1:
                Image notify = new Image("image/one.png");
                notifyNumber.setImage(notify);
                System.out.println("notify 1");

                break;
            case 2:
                Image notify1 = new Image("image/two.png");
                notifyNumber.setImage(notify1);
                System.out.println("notify 2");
                break;
            case 3:
                break;


        }

        // Create the Text
        Text text = new Text(crimeDescription);
        // Set the Font of the Text
        text.setFont(Font.font(20));
        text.setStyle("-fx-text-color:#ee0c1b");

        // add text inside the vBox
        vBoxText.getChildren().addAll(text);

        vBoxText.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: white;");
        TranslateTransition translateTransition = new TranslateTransition();
        //Duration of text movement
        translateTransition.setDuration(Duration.seconds(15));
        //to what position in the X-axis the text should move

        translateTransition.setFromX(800);
        translateTransition.setToX(-800);




           vBoxText.clipProperty();



        //autoReverse
        //translateTransition.setAutoReverse(true);
        //times the text will repeat
        translateTransition.setCycleCount(3);
        //text inside a node inside Vbox that will move
        translateTransition.setNode(text);
        translateTransition.play();

        Timeline timeline;
        timeline = new Timeline(new KeyFrame(

                Duration.seconds(15),
                //add marker metoden som lägger till en marker
                ae -> vBoxText.getChildren().removeAll(text)));
        timeline.play();


        //uploading the scrolling text from the class scrollTextController
//        try {
//            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ScrollText.fxml"));
//            vBoxText.getChildren().removeAll();
//            vBoxText.getChildren().setAll(fxml);
//
//        } catch (IOException e1) {
//
//        }
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
