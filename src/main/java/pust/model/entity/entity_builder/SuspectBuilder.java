package pust.model.entity.entity_builder;

import pust.model.entity.Suspect;

public class SuspectBuilder extends PersonBuilder<Suspect> {

    private Enum gender;
    private Enum ethnicity;
    private Enum build;
    private Enum hairColor;
    private Enum eyeColor;
    private int weight;
    private String characteristic;

    public SuspectBuilder withGender(Enum gender){
        this.gender = gender;
        return this;
    }

    public SuspectBuilder hasEthnicity(Enum ethnicity){
        this.ethnicity = ethnicity;
        return this;
    }

    public SuspectBuilder hasBuild(Enum build){
        this.build = build;
        return this;
    }

    public SuspectBuilder withHairColor(Enum hairColor){
        this.hairColor = hairColor;
        return this;
    }

    public SuspectBuilder withEyeColor(Enum eyeColor) {
        this.eyeColor = eyeColor;
        return this;
    }

    public SuspectBuilder hasWeight(int weight){
        this.weight = weight;
        assert weight >= 0;
        return this;
    }

    public SuspectBuilder hasCharacteristic(String characteristic){
        this.characteristic = characteristic;
        return this;
    }

    @Override
    public Suspect build() {
        return new Suspect(
                super.firstName,
                super.surname,
                super.personalNumber,
                super.address,
                super.crimeRecord,
                super.height,
                super.identification,
                super.phoneNumber,
                gender,
                ethnicity,
                build,
                hairColor,
                eyeColor,
                weight,
                characteristic
        );
    }
}
