package pust.controller.main_window;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pust.model.database_functionality.InsertPerson;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFrameController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

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
    private AnchorPane anchorPaneRight;
    @FXML
    private AnchorPane anchorPaneLeft;
    @FXML
    private ChoiceBox<String> choiceBox;
    private int i = 0;

    private Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RandomCrimeSpot randomCrimeSpot = new RandomCrimeSpot();

        choiceBox.setStyle("-fx-background-color: #d7d7d7;");
        anchorPaneRight.setStyle("-fx-background-color:#d7d7d7;");
        anchorPaneLeft.setStyle("-fx-background-color: #d7d7d7");


//----------------------------------------------------------------------------------------------------------------------
        //First ScrollText  //10 sec between each
        timeline = new Timeline(new KeyFrame(
                Duration.seconds(2),
                //insert specific text here AND also call method to get latLong and marker description to send to google maps
                ae -> scrollText(randomCrimeSpot.getCrimeMark()[0].getScrolltextDescription())));
        choiceBox.getItems().add(randomCrimeSpot.getCrimeMark()[0].getTitle());
        timeline.play();
//----------------------------------------------------------------------------------------------------------------------
        //second notify + text
        timeline = new Timeline(new KeyFrame(
                Duration.seconds(18),
                ae -> scrollText(randomCrimeSpot.getCrimeMark()[1].getScrolltextDescription())));
        choiceBox.getItems().add(randomCrimeSpot.getCrimeMark()[1].getTitle());
        timeline.play();
//----------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------

        Image image = new Image("/image/user_accounts.png");
        imageView.setImage(image);

        Image image1 = new Image("/image/smallSwepustlogg.png");
        notifyImg.setImage(image1);
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/StandardWindow.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }

        notifyImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               // goToGoogleMaps();

            }
        });

        notifyNumber.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                goToGoogleMaps();
                openChoiceBox();
            }
        });
    }
    //removes notification open google maps
    public void openChoiceBox() {
        choiceBox.show();
        choiceBox.getSelectionModel().selectedItemProperty().addListener((V, oldValue, newValue) -> {

            if (choiceBox.getValue().equals("Aggravated assault")) {
                GoogleMapsController.goToCrimeLocation("Aggravated");
            } else if (choiceBox.getValue().equals("Vandalism")) {
                GoogleMapsController.goToCrimeLocation("Vandalism");
            }
        });
    }
    //  public void sendInfoToGoogle(String type) {

    //    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_window/GoogleMaps.fxml"));
    //  GoogleMapsController temp = loader.getController();
    // temp.textField(type);

    //}

    public void goToGoogleMaps() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/GoogleMaps.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

           // GoogleMapsController google = FXMLLoader.load(getClass().getResource("/view/main_window/GoogleMapsController.fxml"));
            //google.goToCrimeLocation("Aggravated");

            Log log = new Log();
            log.saveToFile("OPENED GOOGLE MAPS");

            // FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_window/GoogleMaps.fxml"));
            //GoogleMapsController temp = loader.getController();
            //temp.goToCrimeLocation("Aggravated");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ReportTab() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/Report.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void applyForIdentification() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ApplyForIdentification.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void view() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/View.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void back() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/StandardWindow.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void scrollText(String crimeDescription) {
        i++;
        switch (i) {
            //notify 1
            case 1:
                Image notify = new Image("image/one.png");
                notifyNumber.setImage(notify);
                break;
            //notify 2
            case 2:
                Image notify1 = new Image("image/two.png");
                notifyNumber.setImage(notify1);
                break;
            case 3:
                break;
        }

        // Create the Text
        Text text = new Text(crimeDescription);
        // Set the Font of the Text
        text.setFont(Font.font(20));
        text.setStyle("-fx-text-fill:#ee0c1b");
        //vBoxText.setPrefWidth(634);
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
    }
}

