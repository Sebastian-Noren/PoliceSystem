package pust.model.administrative_functions.report_system;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class BaseReport {
    /*
    @param ref = diarienummer in swedish
     */

    private String ref;
    private LocalDate currentDate;
    private Police administrativeOfficer;
    private LocalDateTime timeAndDateOfEvent;
    private Address placeOfEvent;
    private Person notifier;
    private String descriptionOfEvent;

    public BaseReport(
            String ref,
            LocalDate currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent,
            Person notifier,
            String descriptionOfEvent
    ) {
        this.ref = ref;
        this.currentDate = currentDate;
        this.administrativeOfficer = administrativeOfficer;
        this.timeAndDateOfEvent = timeAndDateOfEvent;
        this.placeOfEvent = placeOfEvent;
        this.notifier = notifier;
        this.descriptionOfEvent = descriptionOfEvent;
    }

    public String getRef() {
        return ref;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public Police getAdministrativeOfficer() {
        return administrativeOfficer;
    }

    public LocalDateTime getTimeAndDateOfEvent() {
        return timeAndDateOfEvent;
    }

    public Address getPlaceOfEvent() {
        return placeOfEvent;
    }

    public Person getNotifier() {
        return notifier;
    }

    public String getDescriptionOfEvent() {
        return descriptionOfEvent;
    }
}
