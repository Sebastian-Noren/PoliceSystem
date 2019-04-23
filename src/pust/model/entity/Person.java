package pust.model.entity;

public abstract class Person {

    protected String firstName;
    protected String surname;
    protected PersonalNumber personalNumber;
    protected Address address;
    protected Record<Crime> crimeRecord;
    protected int height;
    protected Identification identification;

    Person(String firstName,
           String surname,
           PersonalNumber personalNumber,
           Address address,
           Record<Crime> crimeRecord,
           int height,
           Identification identification) {

        this.firstName = firstName;
        this.surname = surname;
        this.personalNumber = personalNumber;
        this.address = address;
        this.crimeRecord = crimeRecord;
        this.height = height;
        this.identification = identification;
    }
}
