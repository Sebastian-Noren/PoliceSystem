package pust.model.entity;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.report.CrimeReport;
import pust.model.administrative_functions.report_system.record.Record;

public abstract class Person {

    private String firstName;
    private String surname;
    private PersonalNumber personalNumber;
    private Address address;
    private Record crimeRecord;
    private int height;
    private Identification identification;
    private String phoneNumber;
    private Enum gender;
    private boolean isWanted;
    private boolean isMissing;
    private boolean inCustody;
    private boolean isSuspect;

    Person(
            String firstName,
            String surname,
            PersonalNumber personalNumber,
            Address address,
            Record crimeRecord,
            int height,
            Identification identification,
            String phoneNumber,
            Enum gender,
            boolean isWanted,
            boolean isMissing,
            boolean inCustody,
            boolean isSuspect
    ) {

        this.firstName = firstName;
        this.surname = surname;
        this.personalNumber = personalNumber;
        this.address = address;
        this.crimeRecord = crimeRecord;
        this.height = height;
        this.identification = identification;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.isWanted = isWanted;
        this.isMissing = isMissing;
        this.inCustody = inCustody;
        this.isSuspect = isSuspect;
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

    public Record getCrimeRecord() {
        return crimeRecord;
    }

    public int getHeight() {
        return height;
    }

    public Identification getIdentification() {
        return identification;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPersonalNumber(PersonalNumber personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Enum getGender() {
        return gender;
    }

    public boolean isWanted() {
        return isWanted;
    }

    public boolean isMissing() {
        return isMissing;
    }

    public boolean isInCustody() {
        return inCustody;
    }

    public boolean isSuspect() {
        return isSuspect;
    }
}
