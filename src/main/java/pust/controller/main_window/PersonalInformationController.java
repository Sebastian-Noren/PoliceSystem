package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import pust.model.personal_view.PersonalCrimeRecordDatabase;
import pust.model.personal_view.CriminalRecord;
import pust.model.utility.AppConstant;

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
            labelCrimeCount, labelInCustody;
    @FXML
    private TextArea criminalTextBox;
    private int counter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PersonalCrimeRecordDatabase perData = new PersonalCrimeRecordDatabase();
        ArrayList<CriminalRecord> criminalRecord = perData.getCrimeRecord(AppConstant.person.getPersonalNumber().getPersonalNumber());
        printCrimeRecords(criminalRecord);
        setlabel();
    }

    private void setlabel() {
        labelSSN.setText(AppConstant.person.getPersonalNumber().getPersonalNumber());
        labelAge.setText(calcAge(AppConstant.person.getPersonalNumber().toString()));
        //labelAge.setText(String.valueOf(2019 - AppConstant.person.getPersonalNumber().getBirthYear()));
        labelSuspect.setText(String.valueOf(AppConstant.person.isSuspect()));
        labelInCustody.setText(String.valueOf(AppConstant.person.isWanted()));
        labelCrimeCount.setText(String.valueOf(counter));
        labelGender.setText(AppConstant.person.getGender().toString());
        labelFullname.setText(AppConstant.person.getSurname() + ", " + AppConstant.person.getFirstName());
        labelFirstname.setText(AppConstant.person.getFirstName());
        labelLastname.setText(AppConstant.person.getSurname());
        labelStreet.setText(AppConstant.person.getAddress().getStreet());
        labelZipCode.setText(String.valueOf(AppConstant.person.getAddress().getZipCode()));
        labelCity.setText(AppConstant.person.getAddress().getCity());
        labelCountry.setText(AppConstant.person.getAddress().getCountry());
        labelMissing.setText(String.valueOf(AppConstant.person.isMissing()));
        labelWanted.setText(String.valueOf(AppConstant.person.isWanted()));
    }

    private void printCrimeRecords(ArrayList<CriminalRecord> criminalRecord) {
        for (CriminalRecord g : criminalRecord) {
            counter++;
            criminalTextBox.appendText("Section:\t" + counter + "\n");
            criminalTextBox.appendText(g.toString());
        }
    }

    private String calcAge(String personSSN) {
        String ssn = personSSN;
        ssn = ssn.substring(0, ssn.length() - 4);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthDate = LocalDate.parse(ssn, formatter);
        return Integer.toString(Period.between(birthDate, currentDate).getYears());
    }
}

