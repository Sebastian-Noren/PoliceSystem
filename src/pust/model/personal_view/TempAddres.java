package pust.model.personal_view;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TempAddres {
    private String streetName;
    private String zipCode;
    private String city;
    private String country;

    public TempAddres(String streetName, String zipCode, String city, String country) {
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
