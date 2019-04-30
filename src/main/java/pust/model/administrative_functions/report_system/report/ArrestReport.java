package pust.model.administrative_functions.report_system.report;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;
import pust.model.entity.Suspect;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ArrestReport extends BaseReport {

    private Suspect suspect;

    public ArrestReport(
            String ref,
            LocalDate currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent,
            Person notifier,
            String descriptionOfEvent,
            Suspect suspect
    ) {
        super(
                ref,
                currentDate,
                administrativeOfficer,
                timeAndDateOfEvent,
                placeOfEvent,
                notifier,
                descriptionOfEvent
        );
        this.suspect = suspect;
    }

    public Suspect getSuspect() {
        return suspect;
    }
}
