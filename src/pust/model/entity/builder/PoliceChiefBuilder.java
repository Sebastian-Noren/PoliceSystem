package pust.model.entity.builder;

import pust.model.entity.Person;
import pust.model.entity.PoliceChief;

public class PoliceChiefBuilder extends EmployeeBuilder {

    @Override
    public Person build() {
        return new PoliceChief(
                super.firstName,
                super.surname,
                super.personalNumber,
                super.address,
                super.crimeRecord,
                super.height,
                super.identification,
                super.salary,
                super.title,
                super.id,
                super.userName,
                super.password
        );
    }
}
