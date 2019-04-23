package pust.model.report_system;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;
import pust.model.entity.Suspect;

import java.time.LocalDateTime;
import java.util.Date;

public class Arrest extends BaseReport {

    private Suspect suspect;

    public Arrest(
            Date currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent, Person notifier,
            String descriptionOfEvent,
            String ref,
            Suspect suspect
    ) {
        super(
                currentDate,
                administrativeOfficer,
                timeAndDateOfEvent,
                placeOfEvent,
                notifier, descriptionOfEvent,
                ref
        );
        this.suspect = suspect;
    }

    public Suspect getSuspect() {
        return suspect;
    }
}
