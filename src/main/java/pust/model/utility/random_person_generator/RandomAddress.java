package pust.model.utility.random_person_generator;

import java.util.concurrent.ThreadLocalRandom;

public class RandomAddress {

    private String streetName;
    private int streetNumber;
    private int zipCode;
    private String city;
    private String country;

    RandomAddress() {
        this.streetName = new StreetSource().random();
        this.streetNumber = ThreadLocalRandom.current().nextInt(1, 200);
        this.zipCode = ThreadLocalRandom.current().nextInt(10000, 97999);
        this.city = new CitySource().random();
        this.country = "Sverige";
    }

    public String getStreetName() {
        return streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
