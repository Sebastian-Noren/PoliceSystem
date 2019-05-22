package pust.model.entity.entity_builder;

import pust.model.entity.Address;

public class AddressBuilder<T extends Address> {

    private String street;
    private int zipCode;
    private String city;
    private String country;

    public AddressBuilder withStreetName(String streetName) {
        this.street = streetName;
        return this;
    }

    public AddressBuilder withZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public Address build() {
        return new Address(
                street,
                zipCode,
                city,
                country
        );
    }

}
