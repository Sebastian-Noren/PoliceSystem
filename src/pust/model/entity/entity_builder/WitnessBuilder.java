package pust.model.entity.entity_builder;

import pust.model.entity.Witness;

public class WitnessBuilder extends PersonBuilder<Witness> {

    @Override
    public Witness build() {
        return new Witness(
                super.firstName,
                super.surname,
                super.personalNumber,
                super.address,
                super.crimeRecord,
                super.height,
                super.identification
        );
    }
}
