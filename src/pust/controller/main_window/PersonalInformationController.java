package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import pust.model.personal_view.PersonalModel;
import pust.model.personal_view.TempCriminalRecord;
import pust.model.personal_view.TempPerson;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PersonalInformationController implements Initializable {
    @FXML
    private Label labelSSN, labelAge, labelGender, labelFullname, labelFirstname, labelLastname,
            labelStreet, labelZipCode, labelCity, labelCountry, labelMissing, labelWanted, labelSuspect, labelCrimeCount;
    @FXML
    private TextArea criminalTextBox;
    //TODO make so this
    private static final String SSN = "198911105555";
    private int counter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PersonalModel perModel = new PersonalModel();
        TempPerson person = perModel.getPersonalInformation(SSN);
        ArrayList<TempCriminalRecord> criminalRecord = perModel.getPersonalCrimeRecord(SSN);
        printCrimeRecords(criminalRecord);
        setlabel(person);
    }

    private void setlabel(TempPerson person) {
        labelSSN.setText(person.getPersonSSN());
        labelAge.setText("");
        labelSuspect.setText("");
        labelCrimeCount.setText(String.valueOf(counter));
        labelGender.setText(person.getGender());
        labelFullname.setText(person.getLastname() + ", " + person.getFirstname());
        labelFirstname.setText(person.getFirstname());
        labelLastname.setText(person.getLastname());
        labelStreet.setText(person.getAddress().getStreetName());
        labelZipCode.setText(person.getAddress().getZipCode());
        labelCity.setText(person.getAddress().getCity());
        labelCountry.setText(person.getAddress().getCountry());
        labelMissing.setText(String.valueOf(person.isMissing()));
        labelWanted.setText(String.valueOf(person.isWanted()));
    }

    private void printCrimeRecords(ArrayList<TempCriminalRecord> criminalRecord) {
        for (TempCriminalRecord g : criminalRecord) {
            counter++;
            criminalTextBox.appendText("Section:\t"+counter+"\n");
            criminalTextBox.appendText(g.toString());
        }
    }

}
