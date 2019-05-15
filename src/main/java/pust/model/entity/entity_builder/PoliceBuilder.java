package pust.model.entity.entity_builder;

import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.Police;

public class PoliceBuilder extends EmployeeBuilder<Police> {

    @Override
    public Police build() {
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
