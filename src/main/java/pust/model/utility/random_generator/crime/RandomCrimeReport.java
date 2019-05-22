package pust.model.utility.random_generator.crime;

import pust.model.entity.*;
import pust.model.enumerations.PersonType;
import pust.model.utility.random_generator.address.RandomAddress;
import pust.model.utility.random_generator.person.RandomPerson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Creates a random crime report object to be used when populating the database.
 * To create the random object use the standard syntax for the build pattern e.g.
 * RandomCrime rc = new RandomCrime();
 * CrimeReport crimeReport = (CrimeReport) new CrimeReportBuilder()
 *                                          ..withTracks(rc.getTracks)
 *                                          ...
 *                                          ...
 *                                          .build();
 */

public class RandomCrimeReport {

    // BaseReport variables
    private String ref;
    private LocalDate currentDate;
    private Police administrativeOfficer;
    private LocalDateTime timeAndDateOfEvent;
    private Address placeOfEvent;
    private Person notifier;
    private String descriptionOfEvent;

    // CrimeReport variables
    private String tracks;
    private Witness witness;

    public RandomCrimeReport() {
        RandomDate randomDate = new RandomDate();
        currentDate = randomDate.getRandomDate();
        timeAndDateOfEvent = randomDate.getRandomDateAndTime();

        RandomAddress randomAddress = new RandomAddress();
        placeOfEvent = new Address(
                randomAddress.getStreet(), randomAddress.getZipCode(),
                randomAddress.getCity(), randomAddress.getCountry());

        ref = randomRef();
        administrativeOfficer = randomPolice();
        notifier = randomNotifier();
        descriptionOfEvent = new DescriptionSource().random();
        tracks = new TrackSource().random();
        witness = randomWitness();

    }

    private String randomRef() {
        return "I-" +
                ThreadLocalRandom.current().nextInt(10, 100) +
                "-" +
                ThreadLocalRandom.current().nextInt(10000000, 100000000) +
                "-" +
                ThreadLocalRandom.current().nextInt(10, 100);
    }

    private Police randomPolice() {
        return (Police) new RandomPerson(PersonType.POLICE).generateRandomPerson();
    }

    private Notifier randomNotifier() {
        return (Notifier) new RandomPerson(PersonType.NOTIFIER).generateRandomPerson();
    }

    private Witness randomWitness() {
        return (Witness) new RandomPerson(PersonType.WITNESS).generateRandomPerson();
    }

    public String getRef() {
        return ref;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public Police getAdministrativeOfficer() {
        return administrativeOfficer;
    }

    public LocalDateTime getTimeAndDateOfEvent() {
        return timeAndDateOfEvent;
    }

    public Address getPlaceOfEvent() {
        return placeOfEvent;
    }

    public Person getNotifier() {
        return notifier;
    }

    public String getDescriptionOfEvent() {
        return descriptionOfEvent;
    }

    public String getTracks() {
        return tracks;
    }

    public Witness getWitness() {
        return witness;
    }
}
