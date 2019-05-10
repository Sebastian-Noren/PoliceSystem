package pust.model.entity.entity_builder;

import pust.model.entity.Notifier;

public class NotifierBuilder extends PersonBuilder<Notifier> {

    @Override
    public Notifier build() {
        return new Notifier(
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
