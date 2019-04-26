package pust.model.administrative_functions.report_system;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;
import pust.model.entity.Suspect;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissingPerson extends BaseReport {

    private Person missingPerson;
    private String clothes;
    private String specificCharacteristics;
    private String reasonsForDisappearing;
    private Suspect descriptionOfSuspect;

    public MissingPerson(
            String ref,
            LocalDate currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent,
            Person notifier,
            String descriptionOfEvent,
            Person missingPerson,
            String clothes,
            String specificCharacteristics,
            String reasonsForDisappearing,
            Suspect descriptionOfSuspect
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
        this.missingPerson = missingPerson;
        this.clothes = clothes;
        this.specificCharacteristics = specificCharacteristics;
        this.reasonsForDisappearing = reasonsForDisappearing;
        this.descriptionOfSuspect = descriptionOfSuspect;
    }

    public Person getMissingPerson() {
        return missingPerson;
    }

    public String getClothes() {
        return clothes;
    }

    public String getSpecificCharacteristics() {
        return specificCharacteristics;
    }

    public String getReasonsForDisappearing() {
        return reasonsForDisappearing;
    }

    public Suspect getDescriptionOfSuspect() {
        return descriptionOfSuspect;
    }
}
