package pust.model.database_functionality;

import org.junit.Test;
import pust.model.entity.*;
import pust.model.utility.LinuxRemoteConnection;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectPersonTest {
    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

    //Person variables
    //private String ssn = "193208317481";      //Person Margret
    //private String ssn = "194512316193";      //Suspect Folke
    private String ssn = "198802297419";        //Employee Vitalis

    @Test
    public void loadPerson() {
        configureLogger();
        LinuxRemoteConnection.remoteConnect();
        System.out.println(LOGGER.getResourceBundle());
        Person person = new SelectPerson(ssn).loadPerson();
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

    private void configureLogger() {
        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        LOGGER.addHandler(handlerObj);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
    }

    @Test
    public void parsePersonalNumber(){
        String ssn = "198306193435";
        int year;
        int month;
        int day;
        int serialNumber;
        int controlNumber;
        StringBuilder sb = new StringBuilder();
        char[] ssnArray = ssn.toCharArray();
        for (int i = 0; i < 4; i++) {
            sb.append(ssnArray[i]);
        }
        year = Integer.valueOf(sb.toString());
        sb = new StringBuilder();
        for (int i = 4; i < 6; i++) {
            sb.append(ssnArray[i]);
        }
        month = Integer.valueOf(sb.toString());
        sb = new StringBuilder();
        for (int i = 6; i < 8; i++) {
            sb.append(ssnArray[i]);
        }
        day = Integer.valueOf(sb.toString());
        sb = new StringBuilder();
        for (int i = 8; i < 11; i++) {
            sb.append(ssnArray[i]);
        }
        serialNumber = Integer.valueOf(sb.toString());
        sb = new StringBuilder();
        sb.append(ssnArray[ssnArray.length - 1]);
        controlNumber = Integer.valueOf(sb.toString());
    }
}