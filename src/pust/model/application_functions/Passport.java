package pust.model.application_functions;

import pust.model.entity.Person;

import java.time.LocalDate;

public class Passport extends Identification{
    public Passport(Person person, LocalDate expirationDate) {
        super(person, expirationDate);
    }
}
