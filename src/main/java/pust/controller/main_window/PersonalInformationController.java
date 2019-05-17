package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import pust.model.personal_view.PersonalDatabase;
import pust.model.personal_view.TempCriminalRecord;
import pust.model.personal_view.TempPerson;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class PersonalInformationController implements Initializable {
    @FXML
    private Label labelSSN, labelAge, labelGender, labelFullname, labelFirstname, labelLastname,
            labelStreet, labelZipCode, labelCity, labelCountry, labelMissing, labelWanted, labelSuspect,
            labelCrimeCount,labelInCustody;
    @FXML
    private TextArea criminalTextBox;
    private static final String SSN = "198911105555";
    private int counter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PersonalDatabase perData = new PersonalDatabase();
        TempPerson person = perData.getPerson(SSN);
        ArrayList<TempCriminalRecord> criminalRecord = perData.getCrimeRecord(SSN);
       printCrimeRecords(criminalRecord);
        setlabel(person);
    }

    private void setlabel(TempPerson person) {
        labelSSN.setText(person.getPersonSSN());
        labelAge.setText(calcAge(person.getPersonSSN())); //TODO Make calculation for age and set it
        labelSuspect.setText(""); //TODO suspect calc
        labelInCustody.setText("");
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

    private String calcAge(String personSSN){
        String ssn = personSSN;
        ssn = ssn.substring(0, ssn.length()-4);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthDate = LocalDate.parse(ssn, formatter);

        return Integer.toString(Period.between(birthDate, currentDate).getYears());
    }
}
