package pust.model.utility.random_person_generator;

import pust.model.administrative_functions.report_system.report.MissingItemReport;
import pust.model.database_functionality.SelectPerson;
import pust.model.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
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
        currentDate = LocalDate.now();
    }

    private String randomRef() {
        return "I-" +
                ThreadLocalRandom.current().nextInt(10, 100) +
                "-" +
                ThreadLocalRandom.current().nextInt(10000000, 100000000) +
                "-" +
                ThreadLocalRandom.current().nextInt(10, 100);
    }
}
