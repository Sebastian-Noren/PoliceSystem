package pust.model.entity.entity_builder;

import pust.model.entity.MissingPerson;

public class MissingPersonBuilder extends PersonBuilder<MissingPerson> {

    @Override
    public MissingPerson build() {
        return new MissingPerson(
                super.firstName,
                super.surname,
                super.personalNumber,
                super.address,
                super.crimeRecord,
                super.height,
                super.identification,
                super.phoneNumber
        );
    }
}
