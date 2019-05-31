package pust.model.administrative_functions.report_system.report_builder;

import pust.model.administrative_functions.report_system.report.CrimeReport;
import pust.model.administrative_functions.report_system.report.MissingItemReport;

public class MissingItemReportBuilder extends BaseReportBuilder<MissingItemReport> {

    protected String manufacturer;
    protected String areaOfUse;
    protected String model;
    protected String productionNumber;
    protected String marking;
    protected String material;
    protected Enum color;
    protected int fair;
    protected String specificCharacteristics;

    public MissingItemReportBuilder withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public MissingItemReportBuilder withAreaOfUse(String areaOfUse) {
        this.areaOfUse = areaOfUse;
        return this;
    }

    public MissingItemReportBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public MissingItemReportBuilder withProductionNumber(String productionNumber) {
        this.productionNumber = productionNumber;
        return this;
    }

    public MissingItemReportBuilder withMarking(String marking) {
        this.marking = marking;
        return this;
    }

    public MissingItemReportBuilder withMaterial(String material) {
        this.material = material;
        return this;
    }

    public MissingItemReportBuilder withColor(Enum color) {
        this.color = color;
        return this;
    }

    public MissingItemReportBuilder asFair(int fair) {
        this.fair = fair;
        return this;
    }

    public MissingItemReportBuilder withSpecificCharacteristics(String specificCharacteristics) {
        this.specificCharacteristics = specificCharacteristics;
        return this;
    }


    @Override
    public MissingItemReport build() {
        return new MissingItemReport(
                super.ref,
                super.currentDate,
                super.administrativeOfficer,
                super.dateOfEvent,
                super.placeOfEvent,
                super.notifier,
                super.descriptionOfEvent,
                manufacturer,
                areaOfUse,
                model,
                productionNumber,
                marking,
                material,
                color,
                fair,
                specificCharacteristics
        );
    }
}
