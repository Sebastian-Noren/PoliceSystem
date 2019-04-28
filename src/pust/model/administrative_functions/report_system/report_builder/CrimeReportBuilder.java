package pust.model.administrative_functions.report_system.report_builder;

import pust.model.administrative_functions.report_system.report.CrimeReport;
import pust.model.administrative_functions.report_system.report.MissingItemReport;
import pust.model.entity.Witness;

public class CrimeReportBuilder extends BaseReportBuilder<CrimeReport> {

    protected String tracks;
    protected String informationAboutOffender;
    protected MissingItemReport itemDescription;
    protected Witness witness;

    public CrimeReportBuilder withTracks(String tracks) {
        this.tracks = tracks;
        return this;
    }

    public CrimeReportBuilder withInformationAboutOffender(String informationAboutOffender) {
        this.informationAboutOffender = informationAboutOffender;
        return this;
    }

    public CrimeReportBuilder withItemDescription(MissingItemReport itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public CrimeReportBuilder withWitness(Witness witness) {
        this.witness = witness;
        return this;
    }


    @Override
    public CrimeReport build() {
        return new CrimeReport(
                super.ref,
                super.currentDate,
                super.administrativeOfficer,
                super.timeAndDateOfEvent,
                super.placeOfEvent,
                super.notifier,
                super.descriptionOfEvent,
                tracks,
                informationAboutOffender,
                itemDescription,
                witness);
    }
}
