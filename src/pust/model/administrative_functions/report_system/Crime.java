package pust.model.administrative_functions.report_system;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;
import pust.model.entity.Witness;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Crime extends BaseReport {

    private String tracks;
    private String informationAboutOffender;
    private MissingItem itemDescription;
    private Witness witness;

    public Crime(
            String ref,
            LocalDate currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent,
            Person notifier,
            String descriptionOfEvent,
            String tracks,
            String informationAboutOffender,
            MissingItem itemDescription,
            Witness witness
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
        this.tracks = tracks;
        this.informationAboutOffender = informationAboutOffender;
        this.itemDescription = itemDescription;
        this.witness = witness;
    }

    public String getTracks() {
        return tracks;
    }

    public String getInformationAboutOffender() {
        return informationAboutOffender;
    }

    public MissingItem getItemDescription() {
        return itemDescription;
    }

    public Witness getWitness() {
        return witness;
    }
}
