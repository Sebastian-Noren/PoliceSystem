package pust.model.report_system;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;

import java.time.LocalDateTime;
import java.util.Date;

public class MissingItem extends BaseReport {

    private String manufacturer;
    private String areaOfUse;
    private String model;
    private String productionNumber;
    private String marking;
    private String material;
    private Enum color;
    private int fair;
    private String specificCharacteristics;

    public MissingItem(
            Date currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent,
            Person notifier,
            String descriptionOfEvent,
            String ref,
            String manufacturer,
            String areaOfUse,
            String model,
            String productionNumber,
            String marking,
            String material,
            Enum color,
            int fair,
            String specificCharacteristics
    ) {
        super(
                currentDate,
                administrativeOfficer,
                timeAndDateOfEvent,
                placeOfEvent,
                notifier,
                descriptionOfEvent,
                ref
        );
        this.manufacturer = manufacturer;
        this.areaOfUse = areaOfUse;
        this.model = model;
        this.productionNumber = productionNumber;
        this.marking = marking;
        this.material = material;
        this.color = color;
        this.fair = fair;
        this.specificCharacteristics = specificCharacteristics;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getAreaOfUse() {
        return areaOfUse;
    }

    public String getModel() {
        return model;
    }

    public String getProductionNumber() {
        return productionNumber;
    }

    public String getMarking() {
        return marking;
    }

    public String getMaterial() {
        return material;
    }

    public Enum getColor() {
        return color;
    }

    public int getFair() {
        return fair;
    }

    public String getSpecificCharacteristics() {
        return specificCharacteristics;
    }
}
