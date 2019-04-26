package pust.model.administrative_functions.report_system.report_builder;

import pust.model.administrative_functions.report_system.Arrest;
import pust.model.entity.Suspect;

public class ArrestBuilder extends BaseReportBuilder<Arrest> {

    protected Suspect suspect;

    @Override
    public Arrest build() {
        return new Arrest(
                super.ref,
                super.currentDate,
                super.administrativeOfficer,
                super.timeAndDateOfEvent,
                super.placeOfEvent,
                super.notifier,
                super.descriptionOfEvent,
                suspect
        );
    }
}
