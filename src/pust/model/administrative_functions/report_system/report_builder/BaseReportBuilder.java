package pust.model.administrative_functions.report_system.report_builder;

import pust.model.administrative_functions.report_system.BaseReport;
import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class BaseReportBuilder<T extends BaseReport> {
    protected String ref;
    protected LocalDate currentDate;
    protected Police administrativeOfficer;
    protected LocalDateTime timeAndDateOfEvent;
    protected Address placeOfEvent;
    protected Person notifier;
    protected String descriptionOfEvent;

    public BaseReportBuilder hasRef(String ref) {
        this.ref = ref;
        return this;
    }

    public BaseReportBuilder isCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
        return this;
    }

    public BaseReportBuilder hasAdministrativeOfficer(Police administrativeOfficer) {
        this.administrativeOfficer = administrativeOfficer;
        return this;
    }

    public BaseReportBuilder wasTimeAndDateOfEvent(LocalDateTime timeAndDateOfEvent) {
        this.timeAndDateOfEvent = timeAndDateOfEvent;
        return this;
    }

    public BaseReportBuilder wasPlaceOfEvent(Address placeOfEvent) {
        this.placeOfEvent = placeOfEvent;
        return this;
    }

    public BaseReportBuilder theNotifier(Person notifier) {
        this.notifier = notifier;
        return this;
    }

    public BaseReportBuilder withDescriptionOfEvent(String descriptionOfEvent) {
        this.descriptionOfEvent = descriptionOfEvent;
        return this;
    }

    public abstract T build();

}
