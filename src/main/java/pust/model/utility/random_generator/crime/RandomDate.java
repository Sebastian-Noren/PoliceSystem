package pust.model.utility.random_generator.crime;

import pust.model.utility.AppConstant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Creates two random dates tied to each other to be used
 * in a random crime report.
 */

public class RandomDate {

    /**
     * @param randomDate        generates a random date object between 1980 and 2018
     * @param randomDateAndTime generates a random date and time object based
     *                          on the randomDate object stretching no more than
     *                          ten years back in time.
     */

    private LocalDate randomDate;
    private LocalDateTime randomDateAndTime;

    public RandomDate() {
        randomDate = randomizeDate();
        randomDateAndTime = randomizeDateAndTime();
    }

    private LocalDate randomizeDate() {
        int year = ThreadLocalRandom.current().nextInt(1980, 2019);
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        int day = ThreadLocalRandom.current().nextInt(1, maxDaysInMonth(year, month));
        return LocalDate.of(year, month, day);
    }

    private LocalDateTime randomizeDateAndTime() {
        int year = randomDate.getYear() - ThreadLocalRandom.current().nextInt(11);
        int month = randomDate.getMonthValue() - ThreadLocalRandom.current().nextInt(1, randomDate.getMonthValue() + 1);
        while (month < 1 || month > 12) {
            month = randomDate.getMonthValue() - ThreadLocalRandom.current().nextInt(1, randomDate.getMonthValue() + 1);
        }
        int day = maxDaysInMonth(year, month) - ThreadLocalRandom.current().nextInt(1, 28);
        int hour = ThreadLocalRandom.current().nextInt(24);
        int min = ThreadLocalRandom.current().nextInt(60);
        return LocalDateTime.of(year, month, day, hour, min);
    }

    private int maxDaysInMonth(int year, int month) {
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
                if (AppConstant.isLeapYear(year)) {
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

    public LocalDate getRandomDate() {
        return randomDate;
    }

    public LocalDateTime getRandomDateAndTime() {
        return randomDateAndTime;
    }
}
