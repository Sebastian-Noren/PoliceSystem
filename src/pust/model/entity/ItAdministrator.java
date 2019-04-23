package pust.model.entity;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.report_system.Crime;
import pust.model.report_system.Record;

public class ItAdministrator extends Employee {

    public ItAdministrator(
            String firstName,
            String surname,
            PersonalNumber personalNumber,
            Address address,
            Record<Crime> crimeRecord,
            int height,
            Identification identification,
            int salary,
            Enum title,
            int id,
            String userName,
            String password
    ) {
        super(
                firstName,
                surname,
                personalNumber,
                address,
                crimeRecord,
                height,
                identification,
                salary,
                title,
                id,
                userName,
                password
        );
    }
}
