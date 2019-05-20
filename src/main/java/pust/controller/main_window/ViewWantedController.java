package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Employee;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Person person = new SelectPerson("196104035555").loadPerson();

        Suspect suspect = null;
        Employee employee = null;
        if (person instanceof Suspect) {
            suspect = (Suspect) person;
        } else if (person instanceof Employee) {
            employee = (Employee) person;
        }
        setlabel(person, suspect);
        String nameList = person.getFirstName() + " " + person.getSurname();

        listWanted.getItems().addAll(nameList);
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
        labelHeight.setText(String.valueOf(person.getHeight()) + " cm");
        labelWeight.setText(String.valueOf(suspect.getWeight()) + " kg");
        labelEyes.setText(suspect.getEyeColor().toString());
        labelThin.setText(suspect.getBuild().toString());
        labelDescription.setText(suspect.getCharacteristic());
    }
}
