package pust.model.entity;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.report.CrimeReport;
import pust.model.administrative_functions.report_system.record.Record;
import pust.model.enumerations.Gender;

/*
 * FIXME Suspect, MissingItemReport and MissingPersonReport all have a variable called characteristics, perhaps a class should handle the attribute
 */

public class Suspect extends Person {
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
            Record crimeRecord,
            int height,
            Identification identification,
            String phoneNumber,
            Gender gender,
            boolean isWanted,
            boolean isMissing,
            boolean inCustody,
            boolean isSuspect,
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
                identification,
                phoneNumber,
                gender,
                isWanted,
                isMissing,
                inCustody,
                isSuspect

        );
        this.ethnicity = ethnicity;
        this.build = build;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.weight = weight;
        this.characteristic = characteristic;
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
