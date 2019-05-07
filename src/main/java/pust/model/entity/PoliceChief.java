package pust.model.entity;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.report.CrimeReport;
import pust.model.administrative_functions.report_system.record.Record;


public class PoliceChief extends Employee {
    public PoliceChief(
            String firstName,
            String surname,
            PersonalNumber personalNumber,
            Address address,
            Record crimeRecord,
            int height,
            Identification identification,
            int salary,
            Enum title,
            int id,
            String userName,
            String password,
            String email
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
                password,
                email
        );
    }
}
