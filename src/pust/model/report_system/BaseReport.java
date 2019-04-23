package pust.model.report_system;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public abstract class BaseReport {

    private LocalDate currentDate;
    private Police administrativeOfficer;
    private LocalDateTime timeAndDateOfEvent;
    private Address placeOfEvent;
    private Person notifier;
    private String descriptionOfEvent;
    private String ref;

    /*
     * @param ref means diarienummer in swedish
     */

    public BaseReport(
            Date currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent,
            Person notifier,
            String descriptionOfEvent,
            String ref
    ) {
        this.currentDate = LocalDate.now();
        this.administrativeOfficer = administrativeOfficer;
        this.timeAndDateOfEvent = timeAndDateOfEvent;
        this.placeOfEvent = placeOfEvent;
        this.notifier = notifier;
        this.descriptionOfEvent = descriptionOfEvent;
        this.ref = ref;
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

    public String getRef() {
        return ref;
    }
}
