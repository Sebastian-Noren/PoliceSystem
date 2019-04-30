package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class ViewWantedController implements Initializable {
    @FXML
    private Label labelHeadName, labelWanted, labelAlias,labelSSN,labelDateBirth,labelGender,
            labelEthnicity,labelHair,labelHeight,labelWeight,labelEyes,labelThin,labelDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    setlabel();
    }


    private void setlabel() {
    labelHeadName.setText("");
    labelWanted.setText("");
    labelAlias.setText("");
    labelSSN.setText("");
    labelDateBirth.setText("");
    labelGender.setText("");
    labelEthnicity.setText("");
    labelHair.setText("");
    labelHeight.setText("");
    labelWeight.setText("");
    labelEyes.setText("");
    labelThin.setText("");
    labelDescription.setText("");
    }
}
