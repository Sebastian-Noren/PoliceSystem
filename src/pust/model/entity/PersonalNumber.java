package pust.model.entity;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;

public class PersonalNumber {

    //FIXME Create a more effective algorithm to calculate the last control digit in the personal number.

    private final SecureRandom random = new SecureRandom();
    private final HashMap<String, String> conversionTable;
    private LocalDate birthDate;
    private int serialNumber;
    private int controlNumber;

    public PersonalNumber(LocalDate birthDate, int serialNumber) {
        conversionTable = new HashMap<>();
        createMapOfMonths();
        this.birthDate = birthDate;
        this.serialNumber = serialNumber;
        //TODO Handle assertion
        assert serialNumber > 99;
        this.controlNumber = calculateControlNumber(birthDate, serialNumber);
    }

    private int calculateControlNumber(LocalDate localDate, int serialNumber){
        int controlNumber = 0;
        int counter = 2;
        char[] dateOfBirth = (localDate.getYear() + getMonthValue(localDate) + getCompleteDayValue(localDate) + serialNumber).toCharArray();
        int[] values = new int[dateOfBirth.length];
        String temp;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < dateOfBirth.length - 2; i++) {
            temp = String.valueOf(dateOfBirth[i + 2]);
            values[i] = Integer.valueOf(temp);
            values[i] = values[i] * counter;
            if (counter == 2) {
                counter = 1;
            } else {
                counter = 2;
            }
            sb.append(values[i]);
        }

        temp = sb.toString();
        char[] toAdd = new char[temp.length()];
        for (int i = 0; i < toAdd.length; i++) {
            toAdd[i] = temp.charAt(i);
            controlNumber += Character.getNumericValue(toAdd[i]);
        }

        temp = String.valueOf(controlNumber);
        controlNumber = Character.getNumericValue(temp.charAt(temp.length() - 1));

        if (controlNumber == 10) {
            return 0;
        } else if (controlNumber == 0) {
            return 0;
        } else {
            return 10 - controlNumber;
        }
    }

    private String getCompleteDayValue(LocalDate localDate) {
        String dayValue = String.valueOf(localDate.getDayOfMonth());
        if (Integer.valueOf(dayValue) < 10) {
            StringBuilder sb = new StringBuilder();
            sb.append(dayValue);
            sb.insert(0, 0);
            dayValue = sb.toString();
        }
        return dayValue;
    }

    private void createMapOfMonths() {
        conversionTable.put("january", "01");
        conversionTable.put("february", "02");
        conversionTable.put("march", "03");
        conversionTable.put("april", "04");
        conversionTable.put("may", "05");
        conversionTable.put("june", "06");
        conversionTable.put("july", "07");
        conversionTable.put("august", "08");
        conversionTable.put("september", "09");
        conversionTable.put("october", "10");
        conversionTable.put("november", "11");
        conversionTable.put("december", "12");
    }

    private String getMonthValue(LocalDate localDate) {
        String monthValue = String.valueOf(localDate.getMonth()).toLowerCase();
        return conversionTable.get(monthValue);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getSerialNumber() {
        return serialNumber;
    }
}
