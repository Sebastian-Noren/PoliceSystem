package pust.model.administrative_functions.report_system.report_builder;

import pust.model.administrative_functions.report_system.report.ArrestReport;
import pust.model.entity.Suspect;

public class ArrestReportBuilder extends BaseReportBuilder<ArrestReport> {

    protected Suspect suspect;

    @Override
    public ArrestReport build() {
        return new ArrestReport(
                super.ref,
                super.currentDate,
                super.administrativeOfficer,
                super.dateOfEvent,
                super.placeOfEvent,
                super.notifier,
                super.descriptionOfEvent,
                suspect
        );
    }
}
