package pust.model.entity.builder;

import pust.model.entity.ItAdministrator;

public class ItAdministratorBuilder extends EmployeeBuilder {

    @Override
    public ItAdministrator build(){
        return new ItAdministrator(
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
