package pust.model.administrative_functions.report_system.report_builder;

import pust.model.administrative_functions.report_system.MissingPerson;
import pust.model.entity.Person;
import pust.model.entity.Suspect;

public class MissingPersonBuilder extends BaseReportBuilder<MissingPerson> {

    protected Person missingPerson;
    protected String clothes;
    protected String specificCharacteristics;
    protected String reasonsForDisappearing;
    protected Suspect descriptionOfSuspect;

    @Override
    public MissingPerson build() {
        return new MissingPerson(
                super.ref,
                super.currentDate,
                super.administrativeOfficer,
                super.timeAndDateOfEvent,
                super.placeOfEvent,
                super.notifier,
                super.descriptionOfEvent,
                missingPerson,
                clothes,
                specificCharacteristics,
                reasonsForDisappearing,
                descriptionOfSuspect
        );
    }
}
