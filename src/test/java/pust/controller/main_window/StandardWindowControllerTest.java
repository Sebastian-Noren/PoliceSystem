package pust.controller.main_window;

import org.junit.Test;
import pust.model.database_functionality.InsertPerson;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.Suspect;
import pust.model.utility.AppConstant;
import pust.model.utility.LinuxRemoteConnection;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class StandardWindowControllerTest {
    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

    //Person variables
    private String ssn = "193208317481";      //Person Margret
    //private String ssn = "194512316193";      //Suspect Folke
    //private String ssn = "198802297419";        //Employee Vitalis

    @Test
    public void instantiateRunnableSelectPerson() {
        configureLogger();
        LinuxRemoteConnection.remoteConnect();
        SelectPerson selectPerson = new SelectPerson(ssn);
        Person person = selectPerson.loadPerson();

        System.out.println("SSN: " + person.getPersonalNumber().getPersonalNumber());
        System.out.println("First name: " + person.getFirstName());
        System.out.println("Surname: " + person.getSurname());

    }

    @Test
    public void selectPersonOnNewThread() {
        configureLogger();
        LinuxRemoteConnection.remoteConnect();
        var ref = new Object() {
            Person person;
        };

        System.out.println("Outside: " + Thread.currentThread().getName());
        Runnable runnable = () -> {
            System.out.println("Inside: " + Thread.currentThread().getName());
            ref.person = new SelectPerson(ssn).loadPerson();
        };

        System.out.println("Creating thread");
        Thread t1 = new Thread(runnable);
        System.out.println("Starting thread");
        t1.start();

        Person person = ref.person;
        Employee employee = null;
        Suspect suspect = null;

        if (ref.person instanceof Suspect) {
            suspect = (Suspect) ref.person;
        } else if (ref.person instanceof Employee) {
            employee = (Employee) ref.person;
        }
        System.out.println("SSN: " + person.getPersonalNumber().getPersonalNumber());
        System.out.println("First name: " + person.getFirstName());
        System.out.println("Surname: " + person.getSurname());
        System.out.println("Age: " + (2019 - person.getPersonalNumber().getBirthYear()));
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
        if (ref.person instanceof Suspect) {
            suspect = (Suspect) ref.person;
            System.out.println("Weight: " + suspect.getWeight());
            System.out.println("Ethnicity: " + suspect.getEthnicity().toString());
            System.out.println("Build: " + suspect.getBuild().toString());
            System.out.println("Eye color: " + suspect.getEyeColor().toString());
            System.out.println("Hair color: " + suspect.getHairColor().toString());
            System.out.println("Characteristics: " + suspect.getCharacteristic());
        } else if (ref.person instanceof Employee) {
            employee = (Employee) ref.person;
            System.out.println("ID: " + employee.getId());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("User name: " + employee.getUserName());
            System.out.println("Password: " + employee.getPassword());
            System.out.println("Title: " + employee.getTitle());
            System.out.println("E-mail: " + employee.getEmail());
        }

        System.out.println(LOGGER.getResourceBundle());
    }


    private void configureLogger() {
        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        LOGGER.addHandler(handlerObj);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
    }

}