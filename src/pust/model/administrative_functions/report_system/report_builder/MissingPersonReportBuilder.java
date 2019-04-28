package pust.model.administrative_functions.report_system.report_builder;

import pust.model.administrative_functions.report_system.report.MissingPersonReport;
import pust.model.entity.MissingPerson;
import pust.model.entity.Suspect;

public class MissingPersonReportBuilder extends BaseReportBuilder<MissingPersonReport> {

    protected MissingPerson missingPerson;
    protected String clothes;
    protected String specificCharacteristics;
    protected String reasonsForDisappearing;
    protected Suspect descriptionOfSuspect;

    @Override
    public MissingPersonReport build() {
        return new MissingPersonReport(
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
