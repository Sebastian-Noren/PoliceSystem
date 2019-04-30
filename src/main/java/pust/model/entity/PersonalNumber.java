package pust.model.entity;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;

public class PersonalNumber {

    private LocalDate birthDate;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private int serialNumber;
    private int controlNumber;

    public PersonalNumber(int birthYear, int birthMonth, int birthDay, int serialNumber, int controlNumber) {
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.serialNumber = serialNumber;
        this.controlNumber = controlNumber;
    }

    public LocalDate getBirthDate() {
        //TODO Convert birthYear, birthMonth, birthDay to a LocalDate object
        return birthDate;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getControlNumber() {
        return controlNumber;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getPersonalNumber(){
        if (birthMonth < 10){
            return "" + birthYear + "-0" + birthMonth + "-" + birthDay + "-" + serialNumber + "" + controlNumber;
        } else if (birthDay < 10){
            return "" + birthYear + "-" + birthMonth + "-0" + birthDay + "-" + serialNumber + "" + controlNumber;

        } else if (birthMonth < 10 && birthDay < 10){
            return "" + birthYear + "-0" + birthMonth + "-0" + birthDay + "-" + serialNumber + "" + controlNumber;

        }
        return "" + birthYear + "-" + birthMonth + "-" + birthDay + "-" + serialNumber + "" + controlNumber;
    }
}
