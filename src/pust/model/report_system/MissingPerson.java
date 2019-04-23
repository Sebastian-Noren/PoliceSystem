package pust.model.report_system;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;
import pust.model.entity.Suspect;

import java.time.LocalDateTime;
import java.util.Date;

/* FIXME This class, MissingItem and Suspect all have a String variable called characteristics or specificCharacteristics.
 * It might be a better idea to create a class representing a characteristic object.
 *
 */

public class MissingPerson extends BaseReport {

    private Person missingPerson;
    private String clothes;
    private String specificCharacteristics;
    private String reasonsForDisappearing;
    private Suspect description;

    public MissingPerson(
            Date currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent,
            Person notifier,
            String descriptionOfEvent,
            String ref,
            Person missingPerson,
            String clothes,
            String specificCharacteristics,
            String reasonsForDisappearing,
            Suspect description
    ) {
        super(
                currentDate,
                administrativeOfficer,
                timeAndDateOfEvent,
                placeOfEvent, notifier,
                descriptionOfEvent,
                ref
        );
        this.missingPerson = missingPerson;
        this.clothes = clothes;
        this.specificCharacteristics = specificCharacteristics;
        this.reasonsForDisappearing = reasonsForDisappearing;
        this.description = description;
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

    public Suspect getDescription() {
        return description;
    }
}
