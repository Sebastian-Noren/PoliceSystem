package pust.model.entity.entity_builder;

import pust.model.entity.ItAdministrator;

public class ItAdministratorBuilder extends EmployeeBuilder<ItAdministrator> {

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
                super.phoneNumber,
                super.gender,
                super.isWanted,
                super.isMissing,
                super.inCustody,
                super.isSuspect,
                super.salary,
                super.title,
                super.id,
                super.userName,
                super.password,
                super.email
        );
    }
}
