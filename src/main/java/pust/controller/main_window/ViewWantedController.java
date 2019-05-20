package pust.controller.main_window;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Person;
import pust.model.entity.Suspect;
import pust.model.utility.AppConstant;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewWantedController implements Initializable {
    @FXML
    private Label labelHeadName, labelWanted, labelAlias, labelSSN, labelDateBirth, labelGender,
            labelEthnicity, labelHair, labelHeight, labelWeight, labelEyes, labelThin, labelDescription;
    @FXML
    private ListView listWanted;
    private Suspect suspect = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listWanted.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

       // String nameList = String.format("%s %s %s", person.getFirstName(), person.getSurname(), person.getPersonalNumber().getPersonalNumber());
        listWanted.getItems().addAll("Robert Fisher: 196104035555");
    }
    @FXML
    private void clickSuspectWanted() {
        String prepeareString = "";
        ObservableList<String> suspect = listWanted.getSelectionModel().getSelectedItems();
        for (String m: suspect) {
            prepeareString = m;
        }
        String splitSSN ="";
        String[] parts = prepeareString.split(("\\D"));
        for (String theParts: parts) {
            splitSSN = theParts;
        }
        getSuspectedFromList(splitSSN);
        System.out.println(splitSSN);
    }

    private void getSuspectedFromList(String splitSSN){
        Person person = new SelectPerson(splitSSN).loadPerson();
        if (person instanceof Suspect) {
            suspect = (Suspect) person;
        }
        setlabel(person, suspect);
    }

    private void setlabel(Person person, Suspect suspect) {
        labelHeadName.setText(person.getFirstName() + " " + person.getSurname());
        labelWanted.setText(String.valueOf(person.isWanted()));
        labelAlias.setText(person.getFirstName() + " " + person.getSurname());
        labelSSN.setText(person.getPersonalNumber().getPersonalNumber());
        labelDateBirth.setText(AppConstant.dateOfBirth(person.getPersonalNumber().getPersonalNumber()));
        labelGender.setText(person.getGender().toString());
        labelEthnicity.setText(suspect.getEthnicity().toString());
        labelHair.setText(suspect.getHairColor().toString());
        labelHeight.setText(person.getHeight() + " cm");
        labelWeight.setText(suspect.getWeight() + " kg");
        labelEyes.setText(suspect.getEyeColor().toString());
        labelThin.setText(suspect.getBuild().toString());
        labelDescription.setText(suspect.getCharacteristic());
    }
}
