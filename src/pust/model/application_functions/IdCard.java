package pust.model.application_functions;

import pust.model.entity.Person;

import java.time.LocalDate;

public class IdCard extends Identification {
    public IdCard(Person person, LocalDate expirationDate) {
        super(person, expirationDate);
    }
}
