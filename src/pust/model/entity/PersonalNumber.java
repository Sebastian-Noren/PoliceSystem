package pust.model.entity;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;

public class PersonalNumber {

    private LocalDate birthDate;
    private int serialNumber;
    private int controlNumber;

    public PersonalNumber(LocalDate birthDate, int serialNumber, int controlNumber) {
        this.birthDate = birthDate;
        this.serialNumber = serialNumber;
        this.controlNumber = controlNumber;
    }



    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getSerialNumber() {
        return serialNumber;
    }
}
