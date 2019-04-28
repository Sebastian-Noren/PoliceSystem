package pust.model.personal_view;

import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TempPerson {
    private String personSSN;
    private String firstname;
    private String lastname;
    private String gender;
    private TempAddres address;
    private boolean wanted;
    private boolean missing;

    public TempPerson(String personSSN, String firstname, String lastname, String gender, TempAddres address, boolean wanted, boolean missing) {
        this.personSSN = personSSN;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.wanted = wanted;
        this.missing = missing;
    }

    public String getPersonSSN() {
        return personSSN;
    }

    public void setPersonSSN(String personSSN) {
        this.personSSN = personSSN;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public TempAddres getAddress() {
        return address;
    }

    public void setAddress(TempAddres address) {
        this.address = address;
    }

    public boolean isWanted() {
        return wanted;
    }

    public void setWanted(boolean wanted) {
        this.wanted = wanted;
    }

    public boolean isMissing() {
        return missing;
    }

    public void setMissing(boolean missing) {
        this.missing = missing;
    }
}
