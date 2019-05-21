package pust.controller.main_window;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import pust.model.database_functionality.InsertPerson;
import pust.model.database_functionality.SQLDatabase;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Person;
import pust.model.entity.Suspect;
import pust.model.utility.AppConstant;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewWantedController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

    @FXML
    private Label labelHeadName, labelWanted, labelAlias, labelSSN, labelDateBirth, labelGender,
            labelEthnicity, labelHair, labelHeight, labelWeight, labelEyes, labelThin, labelDescription, labelCurrentDate;
    @FXML
    private ListView listWanted;
    private Suspect suspect = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateWantedlist();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        labelCurrentDate.setText(String.format("Last Updated: %s", date.format(formatter)));
        if (!(AppConstant.person == null)) {
            if (AppConstant.person.isWanted()) {
                SQLDatabase SQLDatabase = new SQLDatabase();
                String crime = String.format("Wanted for %s.", SQLDatabase.getWantedCrime(AppConstant.person.getPersonalNumber().getPersonalNumber()));
                setlabel(AppConstant.person, AppConstant.suspect, crime);
            }
        }
    }

    @FXML
    private void clickSuspectWanted() {
        String prepeareString = "";
        ObservableList<String> suspect = listWanted.getSelectionModel().getSelectedItems();
        for (String m : suspect) {
            prepeareString = m;
        }
        String splitSSN = "";
        String[] parts = prepeareString.split(("\\D"));
        for (String theParts : parts) {
            splitSSN = theParts;
        }
        getSuspectedFromList(splitSSN);
    }

    private void getSuspectedFromList(String splitSSN) {
        SQLDatabase SQLDatabase = new SQLDatabase();
        Person person = new SelectPerson(splitSSN).loadPerson();
        if (person instanceof Suspect) {
            suspect = (Suspect) person;
        }
        String crime = String.format("Wanted for %s.", SQLDatabase.getWantedCrime(splitSSN));
        setlabel(person, suspect, crime);
    }

    private void updateWantedlist() {
        try {
            Thread thread = new Thread(() -> {
                SQLDatabase SQLDatabase = new SQLDatabase();
                listWanted.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                ArrayList<String> listOfWantedSuspects = SQLDatabase.getSuspects();
                for (String g : listOfWantedSuspects) {
                    listWanted.getItems().add(g);
                }
                listWanted.getSelectionModel().select(0);
            });
            thread.start();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private void setlabel(Person person, Suspect suspect, String crime) {
        labelHeadName.setText(person.getFirstName().toUpperCase() + " " + person.getSurname().toUpperCase());
        labelWanted.setText(crime);
        labelAlias.setText(person.getFirstName().toUpperCase() + " " + person.getSurname().toUpperCase());
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
