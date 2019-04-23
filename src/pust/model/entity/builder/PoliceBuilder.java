package pust.model.entity.builder;

import pust.model.entity.Person;
import pust.model.entity.Police;

public class PoliceBuilder extends EmployeeBuilder {

    @Override
    public Person build() {
        return new Police(
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
