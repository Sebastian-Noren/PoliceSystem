package pust.model.report_system;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;
import pust.model.entity.Witness;

import java.time.LocalDateTime;
import java.util.Date;

public class Crime extends BaseReport {

    private String tracks;
    private String informationAboutOffender;
    private MissingItem itemDescription;
    private Witness witness;

    public Crime(
            Date currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent,
            Person notifier,
            String descriptionOfEvent,
            String ref,
            String tracks,
            String informationAboutOffender,
            MissingItem itemDescription,
            Witness witness
    ) {
        super(
                currentDate,
                administrativeOfficer,
                timeAndDateOfEvent,
                placeOfEvent,
                notifier,
                descriptionOfEvent,
                ref
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
