package pust.model.entity;

public class Address {
    private String streetName;
    private int streetNumber;
    private int zipCode;
    private String city;
    private String country;

    public Address(String streetName, int streetNumber, int zipCode, String city, String country) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
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
