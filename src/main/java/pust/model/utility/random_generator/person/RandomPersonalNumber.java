package pust.model.utility.random_generator.person;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

/*
 * The main job is to calculate the last control number in the
 * personal number using the method calculateControlNumber.
 */

public class RandomPersonalNumber {

    private int year;
    private int month;
    private int day;
    private int serialNumber;
    private int controlNumber;

    public RandomPersonalNumber() {
        year = randomYear();
        month = randomMonth();
        day = calculateDaysInMonth(month, isLeapYear(year));
        serialNumber = randomSerialNumber();
        controlNumber = calculateControlNumber(year, month, day, serialNumber);
    }

    private int calculateControlNumber(int year, int month, int day, int serialNumber) {
        int controlNumber = 0;
        int counter = 2;
        int[] ssn = parseSsnToArray(year, month, day, serialNumber);
        ArrayList<Integer> toAdd = new ArrayList<>();
        for (int i = 0; i < ssn.length; i++) {
            ssn[i] = ssn[i] * counter;
            if (counter == 2) {
                counter = 1;
            } else {
                counter = 2;
            }

        }
        for (int i = 0; i < ssn.length; i++) {
            int[] tempArr = splitSsnToArray(ssn[i]);
            for (int j = 0; j < tempArr.length; j++) {
                toAdd.add(tempArr[j]);
            }
        }

        for (int i = 0; i < toAdd.size(); i++) {
            controlNumber += toAdd.get(i);
        }

        ssn = splitSsnToArray(controlNumber);
        controlNumber = ssn[ssn.length - 1];
        if (controlNumber == 10) {
            return 0;
        } else if (controlNumber == 0) {
            return 0;
        } else {

            return 10 - controlNumber;
        }
    }

    private int randomYear() {
        return ThreadLocalRandom.current().nextInt(1930, LocalDate.now().getYear() - 18);
    }

    private int randomMonth() {
        return ThreadLocalRandom.current().nextInt(1, 13);
    }

    private int randomSerialNumber() {
        return ThreadLocalRandom.current().nextInt(100, 1000);
    }

    private int calculateDaysInMonth(int month, boolean leapYear) {
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
                if (leapYear) {
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

    private StringBuilder concatSsn(int year, int month, int day, int serialNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(year)
                .append(checkLeadingZero(month))
                .append(checkLeadingZero(day))
                .append(serialNumber);
        return sb;
    }

    private int[] parseSsnToArray(int year, int month, int day, int serialNumber) {
        StringBuilder sb = concatSsn(year, month, day, serialNumber).deleteCharAt(0).deleteCharAt(0);
        int tempValue = Integer.parseInt(sb.toString());
        int[] ssnArray = new int[sb.length()];

        return splitSsnToArray(tempValue);
    }

    private int[] splitSsnToArray(int toSplit) {
        Stack<Integer> stack = new Stack<>();

        while (toSplit > 0) {
            stack.push(toSplit % 10);
            toSplit = toSplit / 10;
        }
        toSplit = 0;
        int[] array = new int[stack.size()];
        while (!stack.isEmpty()) {
            array[toSplit] = stack.pop();
            toSplit++;

        }
        return array;
    }

    private String checkLeadingZero(int num) {
        if (num < 10 && num > 0) {
            return "0" + num;
        } else {
            return "" + num;
        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getControlNumber() {
        return controlNumber;
    }
}
