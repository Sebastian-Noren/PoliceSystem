package pust.model.utility.random_person_generator;

import org.junit.Test;
import pust.model.administrative_functions.report_system.report.CrimeReport;
import pust.model.administrative_functions.report_system.report_builder.CrimeReportBuilder;
import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;
import pust.model.entity.Witness;
import pust.model.utility.AppConstant;
import pust.model.utility.random_generator.crime.RandomCrimeReport;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class RandomCrimeReportTest {

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

    @Test
    public void testRandomCrimeReport() {
        for (int i = 0; i < 10; i++) {
            RandomCrimeReport randomCrimeReport = new RandomCrimeReport();
            System.out.println("Ref: \t\t\t\t\t" + randomCrimeReport.getRef());
            System.out.println(
                    "Administrative Officer: " + randomCrimeReport.getAdministrativeOfficer().getFirstName()
                            + " " + randomCrimeReport.getAdministrativeOfficer().getSurname());
            System.out.println("Current date: \t\t\t" + randomCrimeReport.getCurrentDate());
            System.out.println("Time and date of event: " + randomCrimeReport.getTimeAndDateOfEvent());
            System.out.println("Street of event: \t\t" + randomCrimeReport.getPlaceOfEvent().getStreet());
            System.out.println("City of event: \t\t\t" + randomCrimeReport.getPlaceOfEvent().getCity());
            System.out.println(
                    "Notifier: \t\t\t\t" + randomCrimeReport.getNotifier().getFirstName()
                            + " " + randomCrimeReport.getNotifier().getSurname());
            System.out.println("Description of event: \t" + randomCrimeReport.getDescriptionOfEvent());
            System.out.println("Tracks: \t\t\t\t" + randomCrimeReport.getTracks());
            System.out.println(
                    "Witness: \t\t\t\t" + randomCrimeReport.getWitness().getFirstName()
                            + " " + randomCrimeReport.getWitness().getSurname());
        }
    }

    @Test
    public void randomCurrentDate() {
        currentDate = randomLocalDate();
        for (int i = 0; i < 100; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = randomLocalDate();
            localDate.format(formatter);
            System.out.println(i + ": " + localDate.toString());

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime localDateTime = randomTimeAndDateOfEvent();
            String formatDateAndTime = localDateTime.format(format);
            System.out.println(i + ": " + formatDateAndTime);
        }
    }

    private LocalDateTime randomTimeAndDateOfEvent() {
        int year = currentDate.getYear() - ThreadLocalRandom.current().nextInt(11);
        int month = currentDate.getMonthValue() - ThreadLocalRandom.current().nextInt(1, currentDate.getMonthValue() - 1);
        if (month == 0) {
            month++;
        }
        int day = dayOfMonth(year, month) - ThreadLocalRandom.current().nextInt(1, 28);
        int hour = ThreadLocalRandom.current().nextInt(24);
        int min = ThreadLocalRandom.current().nextInt(60);
        return LocalDateTime.of(year, month, day, hour, min);
    }

    private LocalDate randomLocalDate() {
        int year = ThreadLocalRandom.current().nextInt(1980, 2019);
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        int day = ThreadLocalRandom.current().nextInt(1, dayOfMonth(year, month));
        return LocalDate.of(year, month, day);
    }

    private int dayOfMonth(int year, int month) {
        int day = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return -1;
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }

    @Test
    public void randomRef() {
        for (int i = 0; i < 100; i++) {
            String sb = "I-" +
                    ThreadLocalRandom.current().nextInt(10, 100) +
                    "-" +
                    ThreadLocalRandom.current().nextInt(10000000, 100000000) +
                    "-" +
                    ThreadLocalRandom.current().nextInt(10, 100);
            System.out.println(sb);
        }
    }

}