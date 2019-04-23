package pust.model.entity;

public class Visitor extends Person {

    public Visitor(String firstName, String surname, PersonalNumber personalNumber, Address address, Record<Crime> crimeRecord, int height, Identification identification) {
        super(firstName, surname, personalNumber, address, crimeRecord, height, identification);
    }
}
