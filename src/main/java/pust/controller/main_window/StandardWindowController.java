package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.Suspect;
import pust.model.utility.AppConstant;

import java.net.URL;
import java.util.ResourceBundle;


public class StandardWindowController implements Initializable {

    @FXML
    private Label labelSSN;
    @FXML private TextField ssnTextSearch;

    public void watchPersonInfo() {
        if (ssnTextSearch.getText().isEmpty() || ssnTextSearch.getText().length() < 12 || ssnTextSearch.getText().length() > 12) {
            labelSSN.setText("SSN needs to be 12 characters!");
            ssnTextSearch.clear();
        }else{

            String ssn = ssnTextSearch.getText();
            Person person = new SelectPerson(ssn).loadPerson();
            AppConstant.person =person;

            //TODO Make a swich in the scene here

            Suspect suspect;
            Employee employee;
            System.out.println("SSN: " + person.getPersonalNumber().getPersonalNumber());
            System.out.println("First name: " + person.getFirstName());
            System.out.println("Surname: " + person.getSurname());
            System.out.println("Age: "+ (2019 - person.getPersonalNumber().getBirthYear()));
            System.out.println("Wanted: " + person.isWanted());
            System.out.println("Missing: " + person.isMissing());
            System.out.println("In custody: " + person.isInCustody());
            System.out.println("Suspect: " + person.isSuspect());
            System.out.println("Height: " + person.getHeight());
            System.out.println("Gender: " + person.getGender().toString());
            System.out.println("Street: " + person.getAddress().getStreet());
            System.out.println("City: " + person.getAddress().getCity());
            System.out.println("Zip code: " + person.getAddress().getZipCode());
            System.out.println("Country: " + person.getAddress().getCountry());
            if (person instanceof Suspect) {
                suspect = (Suspect) person;
                System.out.println("Weight: " + suspect.getWeight());
                System.out.println("Ethnicity: " + suspect.getEthnicity().toString());
                System.out.println("Build: " + suspect.getBuild().toString());
                System.out.println("Eye color: " + suspect.getEyeColor().toString());
                System.out.println("Hair color: " + suspect.getHairColor().toString());
                System.out.println("Characteristics: " + suspect.getCharacteristic());
            } else if (person instanceof Employee) {
                employee = (Employee) person;
                System.out.println("ID: " + employee.getId());
                System.out.println("Salary: " + employee.getSalary());
                System.out.println("User name: " + employee.getUserName());
                System.out.println("Password: " + employee.getPassword());
                System.out.println("Title: " + employee.getTitle());
                System.out.println("E-mail: " + employee.getEmail());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    labelSSN.setText("");
    }

    @FXML
    private void typingReset() {
        labelSSN.setText("");
    }
}
