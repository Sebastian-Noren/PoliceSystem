package pust.controller.main_window;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pust.model.database_functionality.SQLDatabase;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.utility.AppConstant;

import pust.model.database_functionality.InsertPerson;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private AnchorPane anchorPaneRight;
    @FXML
    private AnchorPane anchorPaneLeft;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Label notifyLabelNumber, labelPoliceID, labelPoliceRole, labelPoliceName, dateTime;
    private int i = 0;
    private int notify = 0;

    private Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RandomCrimeSpot randomCrimeSpot = new RandomCrimeSpot();
        initClock();
        setPoliceInfo();
        choiceBox.getItems().add("C͙u͙r͙r͙e͙n͙t͙ ͙c͙r͙i͙m͙e͙s͙");
        notifyLabelNumber.setVisible(false);
        choiceBox.setStyle("-fx-background-color: #d7d7d7;");
        anchorPaneRight.setStyle("-fx-background-color:#d7d7d7;");
        anchorPaneLeft.setStyle("-fx-background-color: #d7d7d7");

        timeline = new Timeline(new KeyFrame(
                Duration.seconds(2),
                ae -> scrollText(randomCrimeSpot.getCrimeMark()[0].getScrolltextDescription())));
        timeline.play();

        timeline = new Timeline(new KeyFrame(
                Duration.seconds(18),
                ae -> scrollText(randomCrimeSpot.getCrimeMark()[1].getScrolltextDescription())));
        timeline.play();


        timeline = new Timeline(new KeyFrame(
                Duration.seconds(34),
                ae -> scrollText(randomCrimeSpot.getCrimeMark()[2].getScrolltextDescription())
        ));
        timeline.play();

        Image image = new Image("/image/police.jpg");

        imageView.setImage(image);

        Image image1 = new Image("/image/icon.png");
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

        notifyLabelNumber.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //goToGoogleMaps();
                openChoiceBox();
            }
        });
    }

    public void openChoiceBox() {
        choiceBox.show();
        choiceBox.getSelectionModel().selectedItemProperty().addListener((V, oldValue, newValue) -> {

            if (choiceBox.getValue().equals("Aggravated assault")) {


                notifyLabelNumber.setText(String.valueOf(2));
                GoogleMapsController.goToCrimeLocation("Aggravated");

                choiceBox.getItems().remove("Aggravated assault");


            } else if (choiceBox.getValue().equals("Vandalism")) {


                notifyLabelNumber.setText(String.valueOf(1));


                GoogleMapsController.goToCrimeLocation("Vandalism");

                choiceBox.getItems().remove("Vandalism");

            } else if (choiceBox.getValue().equals("Theft")) {


                notifyLabelNumber.setText(String.valueOf(0));

                GoogleMapsController.goToCrimeLocation("Theft");

                choiceBox.getItems().remove("Theft");
            }
        });
    }

    public void goToGoogleMaps() {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/main_window/GoogleMaps.fxml"));
            vBox.getChildren().removeAll();
            vBox.getChildren().setAll(fxml);
            Log log = new Log();
            log.saveToFile("OPENED GOOGLE MAPS");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReportTab() {
        try {
            if (AppConstant.isSsnCheck()) {
                fxml = FXMLLoader.load(getClass().getResource("/view/main_window/Report.fxml"));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll(fxml);
            } else {
                AppConstant.alertBoxInformation("NO SSN", "First enter a SSN number.");
                fxml = FXMLLoader.load(getClass().getResource("/view/main_window/StandardWindow.fxml"));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll(fxml);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public void applyForIdentification() {
        try {
            if (AppConstant.isSsnCheck()) {
                fxml = FXMLLoader.load(getClass().getResource("/view/main_window/ApplyForIdentification.fxml"));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll(fxml);
            } else {
                AppConstant.alertBoxInformation("NO SSN", "First enter a SSN number.");
                fxml = FXMLLoader.load(getClass().getResource("/view/main_window/StandardWindow.fxml"));
                vBox.getChildren().removeAll();
                vBox.getChildren().setAll(fxml);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
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
        RandomCrimeSpot randomCrimeSpot = new RandomCrimeSpot();
        i++;
        switch (i) {
            case 1:
                notifyLabelNumber.setVisible(true);
                notify++;
                notifyLabelNumber.setText(String.valueOf(notify));
                choiceBox.getItems().add(randomCrimeSpot.getCrimeMark()[0].getTitle());
                break;
            case 2:
                notify++;
                notifyLabelNumber.setText(String.valueOf(notify));
                choiceBox.getItems().add(randomCrimeSpot.getCrimeMark()[1].getTitle());
                break;
            case 3:
                notify++;
                notifyLabelNumber.setText(String.valueOf(notify));
                choiceBox.getItems().add(randomCrimeSpot.getCrimeMark()[2].getTitle());
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

    //Sebs new//
    private void setPoliceInfo() {
        SQLDatabase sqlDatabase = new SQLDatabase();
        String ssn = sqlDatabase.getPolice(AppConstant.getCurrentUser());
        Person person = new SelectPerson(ssn).loadPerson();
        Employee employee = null;
        if (person instanceof Employee) {
            employee = (Employee) person;
        }
        labelPoliceName.setText(person.getFirstName() + " " + person.getSurname());
        labelPoliceID.setText(String.format("ID: %d", employee.getId()));
        labelPoliceRole.setText(employee.getTitle().toString());
    }

    private void initClock() {
        try {
            Thread thread = new Thread(() -> {
                Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");
                    dateTime.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
                clock.setCycleCount(Animation.INDEFINITE);
                clock.play();
            });
            thread.start();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }
}


