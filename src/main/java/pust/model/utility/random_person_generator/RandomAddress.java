package pust.model.utility.random_person_generator;

import java.util.concurrent.ThreadLocalRandom;

public class RandomAddress {

    private String street;
    private int zipCode;
    private String city;
    private String country;

    RandomAddress() {
        this.street = new StreetSource().random() + ThreadLocalRandom.current().nextInt(1, 200);
        this.zipCode = ThreadLocalRandom.current().nextInt(10000, 97999);
        this.city = new CitySource().random();
        this.country = "Sverige";
    }

    public String getStreet() {
        return street;
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
