package pust.controller.main_window;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassportFinishedController implements Initializable {


    @FXML
    private AnchorPane anchorPane;
    @FXML
    public ImageView profileImg, profileImg1, profileImage2;
    @FXML
    private ImageView imageViewpass;
    @FXML
    private Button backBtn;
    @FXML
    public Button screenshot;
    //user fill in
    @FXML
    private Label name, dateOfBirth, sex, placeOfBirth, ssn, height, hairColor, eyeColor, weight;

    //labels that are autofilled
    @FXML
    private Label nationality, type, code, dateOfIssue, dateOfExpiry, signature, passportNbr, authority;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //screenshot.addEventHandler(KeyEvent.KEY_PRESSED,event -> {

            //if (event.getCode() == KeyCode.ENTER){
            //    screenShot();
          //  }
        //});

        screenshot.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                screenShot();
            }
        });

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                back();
            }
        });


    }


    public void setText(String firstname, String lastname, String dateOfBirth, String sex, String placeOfBirth, String ssn, String height,
                        String hairColor, String eyeColor, String weight, String nationality, String type, String code, String dateOfIssue, String dateOfExpiry,
                        String authority, String passportNbr) {

        //Standard text for information
        Font font = Font.font("TimesRoman", FontWeight.THIN, 15);
        //for nationality,Code,Type
        Font font1 = Font.font("Verdana", FontWeight.BOLD, 15);
        //for signature
        Font font2 = Font.font("Serif", FontWeight.EXTRA_LIGHT, 20);

        //labels that users fill out (will later change to autofill but these labels will still have this logic)
        String fullname = firstname + " " + lastname;
        this.name.setFont(font);
        this.name.setText(fullname);

        this.dateOfBirth.setFont(font);
        this.dateOfBirth.setText(dateOfBirth);

        this.sex.setFont(font);
        this.sex.setText(sex);

        this.placeOfBirth.setFont(font);
        this.placeOfBirth.setText(placeOfBirth);

        this.ssn.setFont(font2);
        this.ssn.setText(ssn);

        this.height.setFont(font);
        this.height.setText(height + " " + "cm");

        this.hairColor.setFont(font);
        this.hairColor.setText(hairColor);

        this.eyeColor.setFont(font);
        this.eyeColor.setText(eyeColor);

        this.weight.setFont(font);
        this.weight.setText(weight + " " + "kg");

        //labels that are autoFilled

        //set the font
        this.nationality.setFont(font1);
        //set text colour
        this.nationality.setTextFill(Color.PURPLE);
        //set text
        this.nationality.setText(nationality);

        //set the font
        this.type.setFont(font1);
        //set text colour
        this.type.setTextFill(Color.PURPLE);
        //set text
        this.type.setText(type);

        //set the font
        this.code.setFont(font1);
        //set text colour
        this.code.setTextFill(Color.PURPLE);
        //set text
        this.code.setText(code);

        //set the font
        this.dateOfIssue.setFont(font);
        //set the current date
        this.dateOfIssue.setText(dateOfIssue);
        //set the text (current date + 5 years)
        this.dateOfExpiry.setText(dateOfExpiry);

        //work in progress to make the text realistic if needed
        //set the font
        signature.setFont(font2);
        //get the text
        signature.setText(name.getText());

        //Set the font
        this.authority.setFont(font1);
        //set the text
        this.authority.setText(authority);
        //set the font
        this.passportNbr.setFont(font);
        //set the random number
        this.passportNbr.setText(passportNbr);
    }

    public void back() {
        try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/main_window/applyForPassport.fxml"));
            //insert pane to current anchorPane
            anchorPane.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public void setProfileImage(Image image) {
        profileImg.setImage(image);

        GaussianBlur gaussianBlur = new GaussianBlur();

        profileImage2.setEffect(gaussianBlur);
        profileImage2.setImage(image);


        profileImg1.setImage(image);
    }

    public void screenShot() {
        try {

            Robot robot = new Robot();
            //get node location relative to the screen
            Bounds bounds = imageViewpass.localToScreen(imageViewpass.getBoundsInLocal());
            //using node location to define where a rectangle will be placed on the screen and define the node width and height
            Rectangle rect = new Rectangle((int) bounds.getMinX(), (int) bounds.getMinY(), (int) imageViewpass.getFitWidth(), (int) imageViewpass.getFitHeight());

            BufferedImage image = robot.createScreenCapture(rect);

            Image myCaptured = SwingFXUtils.toFXImage(image, null);

            ImageIO.write(image, "jpg", new File("src\\main\\resources\\image\\people\\" + ssn.getText() + ".jpg"));



        } catch (AWTException e) {
            e.printStackTrace();
            // }catch(IOException e){

        } catch (IOException e) {

        }


    }


}
