package pust.model.entity;

import pust.model.application_functions.Identification;
import pust.model.report_system.Crime;
import pust.model.report_system.Record;

public class Suspect extends Person {
    private Enum gender;
    private Enum ethnicity;
    private Enum build;
    private Enum hairColor;
    private Enum eyeColor;
    private int weight;
    private String characteristic;

    public Suspect(
            String firstName,
            String surname,
            PersonalNumber personalNumber,
            Address address,
            Record<Crime> crimeRecord,
            int height,
            Identification identification,
            Enum gender,
            Enum ethnicity,
            Enum build,
            Enum hairColor,
            Enum eyeColor,
            int weight,
            String characteristic
    ) {
        super(
                firstName,
                surname,
                personalNumber,
                address,
                crimeRecord,
                height,
                identification
        );
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.build = build;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.weight = weight;
        this.characteristic = characteristic;
    }

    public Enum getGender() {
        return gender;
    }

    public Enum getEthnicity() {
        return ethnicity;
    }

    public Enum getBuild() {
        return build;
    }

    public Enum getHairColor() {
        return hairColor;
    }

    public Enum getEyeColor() {
        return eyeColor;
    }

    public int getWeight() {
        return weight;
    }

    public String getCharacteristic() {
        return characteristic;
    }
}
