package pust.model.report_system;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

public abstract class BaseReport {

    private Date currentDate;
    private Police administrativeOfficer;
    private LocalDateTime timeAndDateOfEvent;
    private Address placeOfEvent;
    private Person notifier;
    private String descriptionOfEvent;
    private String ref; //Diarienummer = ref //TODO Remove this comment before 29/4

    public BaseReport(Date currentDate,
                      Police administrativeOfficer,
                      LocalDateTime timeAndDateOfEvent,
                      Address placeOfEvent,
                      Person notifier,
                      String descriptionOfEvent,
                      String ref) {
        this.currentDate = currentDate;
        this.administrativeOfficer = administrativeOfficer;
        this.timeAndDateOfEvent = timeAndDateOfEvent;
        this.placeOfEvent = placeOfEvent;
        this.notifier = notifier;
        this.descriptionOfEvent = descriptionOfEvent;
        this.ref = ref;
    }
}
