package pust.model.entity;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.report.CrimeReport;
import pust.model.administrative_functions.report_system.record.Record;

public class Notifier extends Person {

    public Notifier(
            String firstName,
            String surname,
            PersonalNumber personalNumber,
            Address address,
            Record crimeRecord,
            int height,
            Identification identification,
            String phoneNumber,
            Enum gender,
            boolean isWanted,
            boolean isMissing,
            boolean inCustody,
            boolean isSuspect
    ) {
        super(
                firstName,
                surname,
                personalNumber,
                address,
                crimeRecord,
                height,
                identification,
                phoneNumber,
                gender,
                isWanted,
                isMissing,
                inCustody,
                isSuspect
        );
    }
}