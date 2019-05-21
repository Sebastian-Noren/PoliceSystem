package pust.controller.main_window;

import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pust.model.database_functionality.InsertPerson;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PassportFinishedController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

    @FXML
    private AnchorPane anchorPane;
    @FXML
    public ImageView profileImg, profileImg1, profileImage2;
    @FXML
    private ImageView imageViewpass;
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

        String fullname = firstname + " " + lastname;

        autograph(fullname);

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
        //set the font
        this.dateOfExpiry.setFont(font);
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

    @FXML
    private HBox signatureBox;

    public void autograph(String letters) {


        for (int i = 0; i < letters.length(); i++) {

            char characters = letters.charAt(i);




            switch (characters) {
                case 'a':
                    try {
                        FileInputStream a = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\a.png");
                        Image imageA = new Image(a);
                        ImageView aa = new ImageView();
                        //send to method that sets the size and adds image
                        imageSize(imageA, aa);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'A':
                    try {
                        FileInputStream a = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\A.png");
                        Image imageA = new Image(a);
                        ImageView aa = new ImageView();
                        //send to method that sets the size and adds image
                        imageSize(imageA, aa);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'b':
                    try {
                        FileInputStream b = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\b.png");
                        Image imageB = new Image(b);
                        ImageView bb = new ImageView();

                        imageSize(imageB, bb);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'B':
                    try {
                        FileInputStream b = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\B.png");
                        Image imageB = new Image(b);
                        ImageView bb = new ImageView();

                        imageSize(imageB, bb);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;

                case 'c':
                    try {
                        FileInputStream c = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\c.png");
                        Image imageC = new Image(c);
                        ImageView cc = new ImageView();

                        imageSize(imageC, cc);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'C':
                    try {
                        FileInputStream c = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\C.png");
                        Image imageC = new Image(c);
                        ImageView cc = new ImageView();

                        imageSize(imageC, cc);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'd':
                    try {
                        FileInputStream d = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\d.png");
                        Image imageD = new Image(d);
                        ImageView dd = new ImageView();

                        imageSize(imageD, dd);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'D':
                    try {
                        FileInputStream d = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\D.png");
                        Image imageD = new Image(d);
                        ImageView dd = new ImageView();

                        imageSize(imageD, dd);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    try {
                        FileInputStream e = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\e.png");
                        Image imageE = new Image(e);
                        ImageView ee = new ImageView();

                        imageSize(imageE, ee);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'E':
                    try {
                        FileInputStream e = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\E.png");
                        Image imageE = new Image(e);
                        ImageView ee = new ImageView();

                        imageSize(imageE, ee);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'f':
                    try {
                        FileInputStream f = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\f.png");
                        Image imageF = new Image(f);
                        ImageView ff = new ImageView();

                        imageSize(imageF, ff);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'F':
                    try {
                        FileInputStream f = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\F.png");
                        Image imageF = new Image(f);
                        ImageView ff = new ImageView();

                        imageSize(imageF, ff);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'g':
                    try {
                        FileInputStream g = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\g.png");
                        Image imageG = new Image(g);
                        ImageView gg = new ImageView();

                        imageSize(imageG, gg);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'G':
                    try {
                        FileInputStream g = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\G.png");
                        Image imageG = new Image(g);
                        ImageView gg = new ImageView();

                        imageSize(imageG, gg);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'h':
                    try {
                        FileInputStream h = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\h.png");
                        Image imageH = new Image(h);
                        ImageView hh = new ImageView();

                        imageSize(imageH, hh);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'H':
                    try {
                        FileInputStream h = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\H.png");
                        Image imageH = new Image(h);
                        ImageView hh = new ImageView();

                        imageSize(imageH, hh);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'i':
                    try {
                        FileInputStream ii = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\i.png");
                        Image imageI = new Image(ii);
                        ImageView iii = new ImageView();

                        imageSize(imageI, iii);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'I':
                    try {
                        FileInputStream ii = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\I.png");
                        Image imageI = new Image(ii);
                        ImageView iii = new ImageView();

                        imageSize(imageI, iii);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'j':
                    try {
                        FileInputStream j = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\j.png");
                        Image imageJ = new Image(j);
                        ImageView jj = new ImageView();

                        imageSize(imageJ, jj);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'J':
                    try {
                        FileInputStream j = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\J.png");
                        Image imageJ = new Image(j);
                        ImageView jj = new ImageView();

                        imageSize(imageJ, jj);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'k':
                    try {
                        FileInputStream k = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\k.png");
                        Image imageK = new Image(k);
                        ImageView kk = new ImageView();

                        imageSize(imageK, kk);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'K':
                    try {
                        FileInputStream k = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\K.png");
                        Image imageK = new Image(k);
                        ImageView kk = new ImageView();

                        imageSize(imageK, kk);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'l':
                    try {
                        FileInputStream l = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\l.png");
                        Image imageL = new Image(l);
                        ImageView ll = new ImageView();

                        imageSize(imageL, ll);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'L':
                    try {
                        FileInputStream l = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\L.png");
                        Image imageL = new Image(l);
                        ImageView ll = new ImageView();

                        imageSize(imageL, ll);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'm':
                    try {
                        FileInputStream m = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\m.png");
                        Image imageM = new Image(m);
                        ImageView mm = new ImageView();

                        imageSize(imageM, mm);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'M':
                    try {
                        FileInputStream m = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\M.png");
                        Image imageM = new Image(m);
                        ImageView mm = new ImageView();

                        imageSize(imageM, mm);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'n':
                    try {
                        FileInputStream n = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\n.png");
                        Image imageN = new Image(n);
                        ImageView nn = new ImageView();

                        imageSize(imageN, nn);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'N':
                    try {
                        FileInputStream n = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\N.png");
                        Image imageN = new Image(n);
                        ImageView nn = new ImageView();

                        imageSize(imageN, nn);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'o':
                    try {
                        FileInputStream o = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\o.png");
                        Image imageO = new Image(o);
                        ImageView oo = new ImageView();

                        imageSize(imageO, oo);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'O':
                    try {
                        FileInputStream o = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\O.png");
                        Image imageO = new Image(o);
                        ImageView oo = new ImageView();

                        imageSize(imageO, oo);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'p':
                    try {
                        FileInputStream p = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\p.png");
                        Image imageP = new Image(p);
                        ImageView pp = new ImageView();

                        imageSize(imageP, pp);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'P':
                    try {
                        FileInputStream p = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\P.png");
                        Image imageP = new Image(p);
                        ImageView pp = new ImageView();

                        imageSize(imageP, pp);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'q':
                    try {
                        FileInputStream q = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\q.png");
                        Image imageQ = new Image(q);
                        ImageView qq = new ImageView();

                        imageSize(imageQ, qq);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'Q':
                    try {
                        FileInputStream q = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\Q.png");
                        Image imageQ = new Image(q);
                        ImageView qq = new ImageView();

                        imageSize(imageQ, qq);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'r':
                    try {
                        FileInputStream r = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\r.png");
                        Image imageR = new Image(r);
                        ImageView rr = new ImageView();

                        imageSize(imageR, rr);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'R':
                    try {
                        FileInputStream r = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\R.png");
                        Image imageR = new Image(r);
                        ImageView rr = new ImageView();

                        imageSize(imageR, rr);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 's':
                    try {
                        FileInputStream s = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\s.png");
                        Image imageS = new Image(s);
                        ImageView ss = new ImageView();

                        imageSize(imageS, ss);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'S':
                    try {
                        FileInputStream s = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\S.png");
                        Image imageS = new Image(s);
                        ImageView ss = new ImageView();

                        imageSize(imageS, ss);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 't':
                    try {
                        FileInputStream t = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\t.png");
                        Image imageT = new Image(t);
                        ImageView tt = new ImageView();

                        imageSize(imageT, tt);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'T':
                    try {
                        FileInputStream t = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\T.png");
                        Image imageT = new Image(t);
                        ImageView tt = new ImageView();

                        imageSize(imageT, tt);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'u':
                    try {
                        FileInputStream u = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\u.png");
                        Image imageU = new Image(u);
                        ImageView uu = new ImageView();

                        imageSize(imageU, uu);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'U':
                    try {
                        FileInputStream u = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\U.png");
                        Image imageU = new Image(u);
                        ImageView uu = new ImageView();

                        imageSize(imageU, uu);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'v':
                    try {
                        FileInputStream v = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\v.png");
                        Image imageV = new Image(v);
                        ImageView vv = new ImageView();

                        imageSize(imageV, vv);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'V':
                    try {
                        FileInputStream v = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\V.png");
                        Image imageV = new Image(v);
                        ImageView vv = new ImageView();

                        imageSize(imageV, vv);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'w':
                    try {
                        FileInputStream w = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\w.png");
                        Image imageW = new Image(w);
                        ImageView ww = new ImageView();

                        imageSize(imageW, ww);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'W':
                    try {
                        FileInputStream w = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\W.png");
                        Image imageW = new Image(w);
                        ImageView ww = new ImageView();

                        imageSize(imageW, ww);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'x':
                    try {
                        FileInputStream x = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\x.png");
                        Image imageX = new Image(x);
                        ImageView xx = new ImageView();

                        imageSize(imageX, xx);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case'X':
                    try {
                        FileInputStream x = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\X.png");
                        Image imageX = new Image(x);
                        ImageView xx = new ImageView();

                        imageSize(imageX, xx);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'y':
                    try {
                        FileInputStream y = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\y.png");
                        Image imageY = new Image(y);
                        ImageView yy = new ImageView();

                        imageSize(imageY, yy);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'Y':
                    try {
                        FileInputStream y = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\Y.png");
                        Image imageY = new Image(y);
                        ImageView yy = new ImageView();

                        imageSize(imageY, yy);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'z':
                    try {
                        FileInputStream z = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\z.png");
                        Image imageZ = new Image(z);
                        ImageView zz = new ImageView();

                        imageSize(imageZ, zz);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'Z':
                    try {
                        FileInputStream z = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\Z.png");
                        Image imageZ = new Image(z);
                        ImageView zz = new ImageView();

                        imageSize(imageZ, zz);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'å':
                    try {
                        FileInputStream å = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\å.png");
                        Image imageÅ = new Image(å);
                        ImageView åå = new ImageView();

                        imageSize(imageÅ, åå);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'Å':
                    try {
                        FileInputStream å = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\Å.png");
                        Image imageÅ = new Image(å);
                        ImageView åå = new ImageView();

                        imageSize(imageÅ, åå);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'ä':
                    try {
                        FileInputStream ä = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\ä.png");
                        Image imageÄ = new Image(ä);
                        ImageView ää = new ImageView();

                        imageSize(imageÄ, ää);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'Ä':
                    try {
                        FileInputStream ä = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\Ä.png");
                        Image imageÄ = new Image(ä);
                        ImageView ää = new ImageView();

                        imageSize(imageÄ, ää);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'ö':
                    try {
                        FileInputStream ö = new FileInputStream("src\\main\\resources\\autograph\\lowercase\\ö.png");
                        Image imageÖ = new Image(ö);
                        ImageView öö = new ImageView();

                        imageSize(imageÖ, öö);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case'Ö':
                    try {
                        FileInputStream ö = new FileInputStream("src\\main\\resources\\autograph\\uppercase\\Ö.png");
                        Image imageÖ = new Image(ö);
                        ImageView öö = new ImageView();

                        imageSize(imageÖ, öö);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;


            }
        }

    }

    //Send imageView send image, sets size and inserts to vBox
    public void imageSize(Image image, ImageView imageView) {
        imageView.setImage(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(15);
        signatureBox.getChildren().add(imageView);

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
            //myCaptured is not "used" but needs to be there in able to create a screenShot
            Image myCaptured = SwingFXUtils.toFXImage(image, null);

            ImageIO.write(image, "jpg", new File("src\\main\\resources\\image\\people\\" + ssn.getText() + ".jpg"));

            Log log = new Log();
            log.saveToFile("CREATED PASSPORT FOR " + ssn.getText());

        } catch (AWTException e) {
            e.printStackTrace();


        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }
}