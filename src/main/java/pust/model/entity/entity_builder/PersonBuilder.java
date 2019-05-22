package pust.model.entity.entity_builder;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.report.CrimeReport;
import pust.model.administrative_functions.report_system.record.Record;
import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.PersonalNumber;
import pust.model.enumerations.Gender;

public abstract class PersonBuilder <T extends Person>{

    protected String firstName;
    protected String surname;
    protected PersonalNumber personalNumber;
    protected Address address;
    protected Record crimeRecord;
    protected int height;
    protected Identification identification;
    protected String phoneNumber;
    protected Enum gender;
    protected boolean isWanted;
    protected boolean isMissing;
    protected boolean inCustody;
    protected boolean isSuspect;

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

    public PersonBuilder withCrimeRecord(Record crimeRecord) {
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

    public PersonBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PersonBuilder withGender(Gender gender){
        this.gender = gender;
        return this;
    }

    public PersonBuilder isWanted(boolean isWanted){
        this.isWanted = isWanted;
        return this;
    }

    public PersonBuilder isMissing(boolean isMissing){
        this.isMissing = isMissing;
        return this;
    }

    public PersonBuilder inCustody(boolean inCustody){
        this.inCustody = inCustody;
        return this;
    }

    public PersonBuilder isSuspect(boolean isSuspect){
        this.isSuspect = isSuspect;
        return this;
    }



    public abstract T build();
}
