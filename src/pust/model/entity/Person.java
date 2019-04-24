package pust.model.entity;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.Crime;
import pust.model.administrative_functions.report_system.record.Record;

public abstract class Person {

    private String firstName;
    private String surname;
    private PersonalNumber personalNumber;
    private Address address;
    private Record<Crime> crimeRecord;
    private int height;
    private Identification identification;

    Person(
            String firstName,
            String surname,
            PersonalNumber personalNumber,
            Address address,
            Record<Crime> crimeRecord,
            int height,
            Identification identification
    ) {

        this.firstName = firstName;
        this.surname = surname;
        this.personalNumber = personalNumber;
        this.address = address;
        this.crimeRecord = crimeRecord;
        this.height = height;
        this.identification = identification;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public PersonalNumber getPersonalNumber() {
        return personalNumber;
    }

    public Address getAddress() {
        return address;
    }

    public Record<Crime> getCrimeRecord() {
        return crimeRecord;
    }

    public int getHeight() {
        return height;
    }

    public Identification getIdentification() {
        return identification;
    }
}
