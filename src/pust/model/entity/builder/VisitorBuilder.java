package pust.model.entity.builder;

import pust.model.entity.Person;
import pust.model.entity.Visitor;

public class VisitorBuilder extends PersonBuilder<Visitor> {

    @Override
    public Visitor build() {
        return new Visitor(super.firstName,
                super.surname,
                super.personalNumber,
                super.address,
                super.crimeRecord,
                super.height,
                super.identification);
    }
}
