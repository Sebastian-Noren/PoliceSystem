package pust.model.administrative_functions.application_functions;

import pust.model.entity.Employee;
import pust.model.entity.Person;

import java.util.concurrent.ThreadLocalRandom;


public class Passport extends Identification {

    private int passportId;
    private int height;
    private int policeId;
    private String personalNumber;

    public Passport(Person person, Employee employee) {
        this.passportId = createPassportId();
        this.height = person.getHeight();
        this.policeId = employee.getId();
        this.personalNumber = person.getPersonalNumber().getPersonalNumber();
    }

    private int createPassportId() {
        return ThreadLocalRandom.current().nextInt(10000, 1000000);
    }

    public int getPassportId() {
        return passportId;
    }

    public int getHeight() {
        return height;
    }

    public int getPoliceId() {
        return policeId;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPoliceId(int policeId) {
        this.policeId = policeId;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
}
