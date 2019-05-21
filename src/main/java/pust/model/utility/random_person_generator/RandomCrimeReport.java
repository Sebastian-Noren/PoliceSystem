package pust.model.utility.random_person_generator;

import pust.model.entity.*;
import pust.model.enumerations.PersonType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

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
        ref = randomRef();
        currentDate = randomLocalDate();
        administrativeOfficer = randomPolice();

    }

    private String randomRef() {
        return "I-" +
                ThreadLocalRandom.current().nextInt(10, 100) +
                "-" +
                ThreadLocalRandom.current().nextInt(10000000, 100000000) +
                "-" +
                ThreadLocalRandom.current().nextInt(10, 100);
    }

    private LocalDate randomLocalDate() {
        int year = ThreadLocalRandom.current().nextInt(1980, 2019);
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        int day = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                if (isLeapYear(year)) {
                    day = 29;
                    break;
                } else {
                    day = 28;
                    break;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
        }
        return LocalDate.of(year, month, day);
    }

    private Police randomPolice(){
        return (Police) new RandomPerson(PersonType.POLICE).generateRandomPerson();
    }

    private LocalDateTime randomTimeAndDateOfEvent(){
        int year = currentDate.getYear() - ThreadLocalRandom.current().nextInt(11);
        int month = currentDate.getMonthValue() - ThreadLocalRandom.current().nextInt(currentDate.getMonthValue() - 1);
        int day = currentDate.getDayOfMonth() - ThreadLocalRandom.current().nextInt(currentDate.getDayOfMonth() - 1);
        int hour = ThreadLocalRandom.current().nextInt(25);
        int min = ThreadLocalRandom.current().nextInt(60);
        return LocalDateTime.of(year, month, day, hour, min);
    }

    private boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
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
