package pust.model.entity;

import pust.model.application_functions.Identification;
import pust.model.report_system.Crime;
import pust.model.report_system.Record;

public class Notifier extends Person {

    public Notifier(
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