package pust.model.administrative_functions.report_system;

import pust.model.entity.Address;
import pust.model.entity.Person;
import pust.model.entity.Police;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
            String ref,
            LocalDate currentDate,
            Police administrativeOfficer,
            LocalDateTime timeAndDateOfEvent,
            Address placeOfEvent,
            Person notifier,
            String descriptionOfEvent,
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
                ref,
                currentDate,
                administrativeOfficer,
                timeAndDateOfEvent,
                placeOfEvent,
                notifier,
                descriptionOfEvent
        );
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
