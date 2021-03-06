package pust.model.entity.entity_builder;

import pust.model.entity.Visitor;

public class VisitorBuilder extends PersonBuilder<Visitor> {

    @Override
    public Visitor build() {
        return new Visitor(
                super.firstName,
                super.surname,
                super.personalNumber,
                super.address,
                super.crimeRecord,
                super.height,
                super.identification,
                super.phoneNumber,
                super.gender,
                super.isWanted,
                super.isMissing,
                super.inCustody,
                super.isSuspect
        );
    }
}
