package pust.model.entity;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.report_system.Crime;
import pust.model.report_system.Record;

public class Witness extends Person {

    public Witness(
            String firstName,
            String surname,
            PersonalNumber personalNumber,
            Address address,
            Record<Crime> crimeRecord,
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
