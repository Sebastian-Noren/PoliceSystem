package pust.model.administrative_functions.report_system.report_builder;

import pust.model.administrative_functions.report_system.MissingItem;

public class MissingItemBuilder extends BaseReportBuilder<MissingItem> {

    protected String manufacturer;
    protected String areaOfUse;
    protected String model;
    protected String productionNumber;
    protected String marking;
    protected String material;
    protected Enum color;
    protected int fair;
    protected String specificCharacteristics;

    public MissingItemBuilder withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public MissingItemBuilder withAreaOfUse(String areaOfUse) {
        this.areaOfUse = areaOfUse;
        return this;
    }

    public MissingItemBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public MissingItemBuilder withProductionNumber(String productionNumber) {
        this.productionNumber = productionNumber;
        return this;
    }

    public MissingItemBuilder withMarking(String marking) {
        this.marking = marking;
        return this;
    }

    public MissingItemBuilder withMaterial(String material) {
        this.material = material;
        return this;
    }

    public MissingItemBuilder withColor(Enum color) {
        this.color = color;
        return this;
    }

    public MissingItemBuilder asFair(int fair) {
        this.fair = fair;
        return this;
    }

    public MissingItemBuilder withSpecificCharacteristics(String specificCharacteristics) {
        this.specificCharacteristics = specificCharacteristics;
        return this;
    }


    @Override
    public MissingItem build() {
        return null;
    }
}
