package pust.model.entity.entity_builder;

import pust.model.entity.Person;
import pust.model.entity.Police;
import pust.model.entity.PoliceChief;

public class PoliceChiefBuilder extends EmployeeBuilder<PoliceChief> {

    @Override
    public PoliceChief build() {
        return new PoliceChief(
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
