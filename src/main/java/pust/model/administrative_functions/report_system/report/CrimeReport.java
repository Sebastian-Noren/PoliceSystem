package pust.model.administrative_functions.report_system.report;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;
import pust.model.entity.Witness;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CrimeReport extends BaseReport {

    private String tracks;
    private String informationAboutOffender;
    private MissingItemReport itemDescription;
    private Witness witness;

    public CrimeReport(
            String ref,
            LocalDate currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent,
            Person notifier,
            String descriptionOfEvent,
            String tracks,
            String informationAboutOffender,
            MissingItemReport itemDescription,
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

    public MissingItemReport getItemDescription() {
        return itemDescription;
    }

    public Witness getWitness() {
        return witness;
    }
}
