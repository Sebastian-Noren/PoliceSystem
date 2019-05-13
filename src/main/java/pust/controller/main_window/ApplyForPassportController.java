package pust.controller.main_window;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class ApplyForPassportController extends Thread implements Initializable {
    @FXML
    private Button backBtn;
    @FXML
    private AnchorPane anchorPane;
    //will be autofilled later
    @FXML
    private TextField firstName, lastName, dateOfBirth, sex, placeOfBirth, ssn, height, hairColor, eyeColor, weight;
    //auto filled
    @FXML
    private TextField nationality, type, code, dateOfIssue, dateOfExpiry, authority, passportNbr;
    @FXML
    private ImageView iconImage;

    private int captured;

    //-------------------------------------
    //set our webcam to public to be used in class video
    public Webcam webcam;
    public static boolean isCapture = false;
    //we set this public to be accessed in class video.
    public ImageView imageView;
//-------------------------------------


    //upload image
    private Image[] upploadImage = new Image[1];
    //place of birth
    private String[] birthPlace = new String[10];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SecureRandom secureRandom = new SecureRandom();


        //get our default cam
        webcam = webcam.getDefault();

        //fill array with places
        automaticBirthPlace();
        //set random place
        placeOfBirth.setText(birthPlace[secureRandom.nextInt(birthPlace.length)]);

        Image image = new Image("image/swepustlogg.png");
        iconImage.setImage(image);

        nationality.setText("SWEDISH");
        type.setText("P");
        code.setText("SWE");


        //get current date
        Calendar currentDate = Calendar.getInstance();
        //today's date
        Date date = new Date();
        currentDate.setTime(date);
        //format we show it as
        DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd");
        //set the current date
        dateOfIssue.setText(dateFormat.format(date.getTime()));

        currentDate.add(Calendar.YEAR, 5);
        //we get the date + 5 years we added
        dateOfExpiry.setText(dateFormat.format(currentDate.getTime()));
        //set authority text
        authority.setText("P.G.I.S");

        //generate random passport number
        int numberFrom = 1000000;
        int numberTo = 100000000;
        int randomnbr = secureRandom.nextInt(numberTo - numberFrom) + numberFrom;

        passportNbr.setText(String.valueOf(randomnbr));

        //TODO fix
        //When we auto fill ssn then we will activate the method
        //but for now we need to type in the information manually.
        ssn.setOnKeyPressed(e -> {
            automaticDateOfBirth();
            automaticGender();

        });


    }

    public void startCam() {

        // new MultipointMotionDetectionExample();

        if (webcam == null) {
            System.out.println("Camera not found");
        }

        //set with and height of our cam to the size of our imageView
        webcam.setViewSize(new Dimension((int) imageView.getFitWidth(), (int) imageView.getFitHeight()));
        webcam.open();

        // Start camera capture
        // new VideoCapture().start();

        //boolean state = true;

        //checks our motion
        //new DetectMotion().motionDetected(state);


    }

    public void captureImage() {

        BufferedImage image = webcam.getImage();
        Image myCaptured = SwingFXUtils.toFXImage(image, null);

        upploadImage[0] = myCaptured;

    }

    public void paus() {
        webcam.close();
    }

    public void back() {
        try {
            //Set fxml (scene 2) to AnchorPane pane
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/main_window/Passport.fxml"));
            //insert pane to current anchorPane
            anchorPane.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void upploadImage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_window/PassportFinished.fxml"));

        //upload image
        FileChooser fileChooser = new FileChooser();
        File selectedImage = fileChooser.showOpenDialog(null);

        List<File> uploadedImage = new ArrayList<>();
        uploadedImage.add(selectedImage);


        try {
            FileInputStream fileInputStream = new FileInputStream(uploadedImage.get(0));
            Image image1 = new Image(fileInputStream, 160, 194, false, false);

            //insert uploaded image to array
            upploadImage[0] = image1;


            //get uploaded file
            switch (uploadedImage.get(0).getName()) {
                case "woman1.jpg":
                    this.height.setText("164");
                    this.hairColor.setText("Light brown");
                    this.eyeColor.setText("Green");
                    this.weight.setText("62");
                    break;
                case "man1.jpg":
                    this.height.setText("174");
                    this.hairColor.setText("Black");
                    this.eyeColor.setText("Brown");
                    this.weight.setText("77");
                    break;
                case "woman2.jpg":
                    this.height.setText("170");
                    this.hairColor.setText("Blond");
                    this.eyeColor.setText("Blue");
                    this.weight.setText("69");
                    break;
                case "man2.jpg":
                    this.height.setText("178");
                    this.hairColor.setText("Brown/Grey");
                    this.eyeColor.setText("Light brown/Yellow");
                    this.weight.setText("78");
                    break;
                case "woman3.jpg":
                    this.height.setText("174");
                    this.hairColor.setText("Brown");
                    this.eyeColor.setText("Light brown/Grey");
                    this.weight.setText("73");
                    break;
                default:
                    System.out.println("outside the fold of automatic");
                    break;


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void next() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_window/PassportFinished.fxml"));
            AnchorPane pane = loader.load();
            anchorPane.getChildren().setAll(pane);

            PassportFinishedController passportFinishedController = loader.getController();
            //sending information to next scene
            passportFinishedController.setText(firstName.getText(), lastName.getText(), dateOfBirth.getText(), sex.getText(), placeOfBirth.getText(), ssn.getText(), height.getText(),
                    hairColor.getText(), eyeColor.getText(), weight.getText(), nationality.getText(), type.getText(), code.getText(), dateOfIssue.getText(), dateOfExpiry.getText(),
                    authority.getText(), passportNbr.getText());

            passportFinishedController.setProfileImage(upploadImage[0]);


        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void automaticBirthPlace() {
        String place = "LUND";
        birthPlace[0] = place;
        String place1 = "STOCKHOLM";
        birthPlace[1] = place1;
        String place2 = "KRISTIANSTAD";
        birthPlace[2] = place2;
        String place3 = "ESLÖV";
        birthPlace[3] = place3;
        String place4 = "HÖRBY";
        birthPlace[4] = place4;
        String place5 = "MALMÖ";
        birthPlace[5] = place5;
        String place6 = "HÄSSLEHOLM";
        birthPlace[6] = place6;
        String place7 = "BÅSTAD";
        birthPlace[7] = place7;
        String place8 = "HELSINGBORG";
        birthPlace[8] = place8;
        String place9 = "TRELLEBORG";
        birthPlace[9] = place9;

    }

    public void automaticGender() {
        String gender = this.ssn.getText();
        char gender1 = gender.charAt(10);

        boolean even = (gender1 % 2) == 0 ? true : false;

        //if true (true that u can divide the number with 2) then its a even number = gender woman else man (odd number)
        if (even) {
            sex.setText("W");
        } else {
            sex.setText("M");
        }
    }

    //set date of birth based on ssn entered!
    public void automaticDateOfBirth() {

        //take ssn and add it to string
        String ssn = this.ssn.getText();
        //get day
        char day = ssn.charAt(6);
        char day1 = ssn.charAt(7);
        //take specific indexes and create a string (stringBuilder)
        StringBuilder day2 = new StringBuilder();
        day2.append(day);
        day2.append(day1);
        //set our created string (stringBuilder) to a string
        String day3 = day2.toString();


        //cut character at specific index of ssn and create a char of that index [cut at the month]
        char month = ssn.charAt(4);
        char month1 = ssn.charAt(5);

        //take specific indexes and create a string
        StringBuilder month3 = new StringBuilder();
        month3.append(month);
        month3.append(month1);

        //set our created string (stringBuilder) to a string
        String month4 = month3.toString();


        switch (month4) {
            case "01":
                this.dateOfBirth.setText(day3 + " JANUARY");
                break;
            case "02":
                this.dateOfBirth.setText(day3 + " FEBRUARY");
                break;
            case "03":
                this.dateOfBirth.setText(day3 + " MARCH");
                break;
            case "04":
                this.dateOfBirth.setText(day3 + " APRIL");
                break;
            case "05":
                this.dateOfBirth.setText(day3 + " MAY");
                break;
            case "06":
                this.dateOfBirth.setText(day3 + " JUNE");
                break;
            case "07":
                this.dateOfBirth.setText(day3 + " JULY");
                break;
            case "08":
                this.dateOfBirth.setText(day3 + " AUGUST");
                break;
            case "09":
                this.dateOfBirth.setText(day3 + " SEPTEMBER");
                break;
            case "10":
                this.dateOfBirth.setText(day3 + " OCTOBER");
                break;
            case "11":
                this.dateOfBirth.setText(day3 + " NOVEMBER");
                break;
            case "12":
                this.dateOfBirth.setText(day3 + " DECEMBER");
                break;
            default:
                this.dateOfBirth.setText("NOT VALID!");
                break;

        }


    }

    //TODO ändra detta gör det bättre!
    class VideoCapture extends Thread {
        @Override
        public void run() {
            while (!isCapture) { // For each 30 millisecond take picture and set it in image view
                try {
                    imageView.setImage(SwingFXUtils.toFXImage(webcam.getImage(), null));
                    sleep(30);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    ////TODO måste kunna stänga av detta! kolla "while true"
    public class DetectMotion {
        public Thread t;
        public WebcamMotionDetector motionDetector;

        public void motionDetected(boolean state) {

            motionDetector = new WebcamMotionDetector(webcam.getDefault());
            motionDetector.setInterval(500);
            motionDetector.start();

            t = new Thread("motion-printer") {

                @Override
                public void run() {
                    do {
                        try {
                            if (motionDetector.isMotion()) {
                                System.out.println("you are moving");

                            } else if (!motionDetector.isMotion()) {
                                System.out.println("you are still");

                            }
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            break;
                        }
                    } while (state);
                }
            };

            t.setDaemon(true);
            t.start();

        }

        public void shutdown() {
            t.setDaemon(false);
            t.stop();
        }


    }


}