package pust.model.administrative_functions.report_system.report_builder;

import pust.model.administrative_functions.report_system.Crime;
import pust.model.administrative_functions.report_system.MissingItem;
import pust.model.entity.Witness;

public class CrimeBuilder extends BaseReportBuilder<Crime> {

    protected String tracks;
    protected String informationAboutOffender;
    protected MissingItem itemDescription;
    protected Witness witness;

    public CrimeBuilder withTracks(String tracks) {
        this.tracks = tracks;
        return this;
    }

    public CrimeBuilder withInformationAboutOffender(String informationAboutOffender) {
        this.informationAboutOffender = informationAboutOffender;
        return this;
    }

    public CrimeBuilder withItemDescription(MissingItem itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public CrimeBuilder withWitness(Witness witness) {
        this.witness = witness;
        return this;
    }


    @Override
    public Crime build() {
        return new Crime(
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
