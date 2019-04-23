package pust.model.entity.builder;

import pust.model.application_functions.Identification;
import pust.model.entity.*;
import pust.model.report_system.Crime;
import pust.model.report_system.Record;

public abstract class PersonBuilder <T extends Person>{

    protected String firstName;
    protected String surname;
    protected PersonalNumber personalNumber;
    protected Address address;
    protected Record<Crime> crimeRecord;
    protected int height;
    protected Identification identification;

    public PersonBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder withPersonalNumber(PersonalNumber personalNumber) {
        this.personalNumber = personalNumber;
        return this;
    }

    public PersonBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public PersonBuilder withCrimeRecord(Record<Crime> crimeRecord) {
        this.crimeRecord = crimeRecord;
        return this;
    }

    public PersonBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    public PersonBuilder withIdentification(Identification identification) {
        this.identification = identification;
        return this;
    }

    public abstract T build();
}
