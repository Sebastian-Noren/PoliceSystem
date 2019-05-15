package pust.model.administrative_functions.application_functions;

import pust.model.entity.Person;

import java.time.LocalDate;

public abstract class Identification {
    private Person person;
    private LocalDate expirationDate;

    public Identification(Person person, LocalDate expirationDate) {
        this.person = person;
        this.expirationDate = createExpirationDate(person);
    }

    private LocalDate createExpirationDate(Person person){
        //TODO Calculate expiration date from today's date and five years forward
        return expirationDate;
    }
}
