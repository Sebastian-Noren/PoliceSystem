package pust.model.entity;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.report.CrimeReport;
import pust.model.administrative_functions.report_system.record.Record;


public class Witness extends Person {

    public Witness(
            String firstName,
            String surname,
            PersonalNumber personalNumber,
            Address address,
            Record crimeRecord,
            int height,
            Identification identification
    ) {
        super(
                firstName,
                surname,
                personalNumber,
                address,
                crimeRecord,
                height,
                identification
        );
    }
}
