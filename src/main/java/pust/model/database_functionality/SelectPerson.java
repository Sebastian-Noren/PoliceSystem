package pust.model.database_functionality;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.record.Record;
import pust.model.entity.*;
import pust.model.entity.entity_builder.*;
import pust.model.enumerations.*;
import pust.model.utility.AppConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * This class loads a person object from the database based in the constructor parameter
 * when calling loadPerson().
 *
 */

public class SelectPerson {
    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());
    private boolean foundPerson;
    private boolean foundEmployee;
    private boolean foundSuspect;

    //Person variables
    private String ssn;
    private String firstName;
    private String surname;
    private Gender gender;
    private PersonalNumber personalNumber;
    private Address address;
    private Record crimeRecord;
    private int height;
    private Identification identification;
    private String phoneNumber;
    private boolean isWanted;
    private boolean isMissing;
    private boolean inCustody;
    private boolean isSuspect;

    //Employee variables
    private int salary;
    private Title title;
    private int id;
    private String userName;
    private String password;
    private String email;

    //Suspect variables
    private Ethnicity ethnicity;
    private Build build;
    private Color.hairColor hairColor;
    private Color.eyeColor eyeColor;
    private int weight;
    private String characteristic;

    //Address variables
    private String street;
    private int zipCode;
    private String city;
    private String country;

    /**
     * The constructor is used to set the variable ssn
     *
     * @param ssn needs a 12 digit String in the format XXXXXXXXXXXX
     */

    //FIXME Add crimeRecord functionality

    public SelectPerson(String ssn) {
        this.ssn = ssn;
        foundPerson = false;
        foundEmployee = false;
        foundSuspect = false;
        loadData();
    }

    /**
     * loadPerson()
     *
     * @return a new object of either a) person, b) employee or c) suspect.
     * This needs to be checked where the method is called e.g:
     * Person person = new SelectPerson(ssn).loadPerson();
     * Suspect suspect;
     * Employee employee;
     * if (person instanceof Suspect) {
     * suspect = (Suspect) person;
     * } else if (person instance of Employee) {
     * employee = (Employee) person;
     * }
     * The variables can then be used to extract the information needed.
     */

    public Person loadPerson() {
        Person person = null;
        if (foundPerson) {
            if (foundEmployee) {
                switch (title) {
                    case ITADMINISTRATOR:
                    case ITCOORDINATOR:
                        return createItAdministrator();
                    case ASSISTANT:
                    case INSPECTOR:
                    case SUPERINTENDENT:
                        return createPolice();
                    case CHIEFSUPERINTENDENT:
                    case DISTRICTPOLICECOMMISSIONER:
                        return createPoliceChief();
                }
            } else if (foundSuspect) {
                return createSuspect();
            } else if (isMissing) {
                person = createMissingPerson();
            } else {
                person = createVisitor();
                //TODO createNotifier is not used
            }
        }
        return person;
    }

    private void loadData() {
        loadPersonData();
        loadEmployeeData();
        loadSuspectData();
        loadAddressData();
    }

    private void loadPersonData() {
        try (Connection connection = AppConstant.dataSource.getConnection()) {
            connection.setAutoCommit(false);

            //Get the data for a person object
            PreparedStatement personData = connection.prepareStatement(sqlPerson());
            personData.setString(1, ssn);
            ResultSet personResult = personData.executeQuery();
            connection.commit();

            while (personResult.next()) {
                foundPerson = true;
                personalNumber = AppConstant.parsePersonalNumber(personResult.getString("SSN"));
                firstName = personResult.getString("firstname");
                surname = personResult.getString("lastname");
                gender = AppConstant.parseGenderToEnum(personResult.getString("gender"));
                isWanted = AppConstant.parseToBoolean(personResult.getInt("wanted"));
                isMissing = AppConstant.parseToBoolean(personResult.getInt("missing"));
                inCustody = AppConstant.parseToBoolean(personResult.getInt("custody"));
                isSuspect = AppConstant.parseToBoolean(personResult.getInt("suspect"));
                phoneNumber = personResult.getString("phonenumber");
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private void loadEmployeeData() {
        try (Connection connection = AppConstant.dataSource.getConnection()) {
            connection.setAutoCommit(false);
            //Get the data for an employee object
            PreparedStatement employeeData = connection.prepareStatement(sqlEmployee());
            employeeData.setString(1, ssn);
            ResultSet employeeResult = employeeData.executeQuery();
            connection.commit();

            while (employeeResult.next()) {
                foundEmployee = true;
                id = employeeResult.getInt("policeID");
                salary = employeeResult.getInt("salary");
                userName = employeeResult.getString("username");
                email = employeeResult.getString("e-mail");
                String jobtitle = employeeResult.getString("jobtitle");
                title = AppConstant.parseJobTitleToEnum(jobtitle);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private void loadSuspectData() {
        try (Connection connection = AppConstant.dataSource.getConnection()) {
            connection.setAutoCommit(false);

            //Get the data for a suspect object
            PreparedStatement suspectData = connection.prepareStatement(sqlSuspect());
            PreparedStatement ethnicityData = connection.prepareStatement(sqlEthnicity());
            PreparedStatement eyeColourData = connection.prepareStatement(sqlEyeColour());
            PreparedStatement hairColourData = connection.prepareStatement(sqlHairColour());
            suspectData.setString(1, ssn);
            ethnicityData.setString(1, ssn);
            eyeColourData.setString(1, ssn);
            hairColourData.setString(1, ssn);
            ResultSet suspectResult = suspectData.executeQuery();
            ResultSet ethnicityResult = ethnicityData.executeQuery();
            ResultSet eyeColourResult = eyeColourData.executeQuery();
            ResultSet hairColourResult = hairColourData.executeQuery();
            connection.commit();

            while (suspectResult.next()) {
                foundSuspect = true;
                weight = suspectResult.getInt("weight");
                height = suspectResult.getInt("height");
                characteristic = suspectResult.getString("characteristics");
                build = AppConstant.parseBuildToEnum(suspectResult.getString("build"));
                while (ethnicityResult.next()) {
                    ethnicity = AppConstant.parseEthnicityToEnum(ethnicityResult.getString("ethnicity"));
                }
                while (eyeColourResult.next()) {
                    eyeColor = AppConstant.parseEyeColour(eyeColourResult.getString("eyeColourcol"));
                }
                while (hairColourResult.next()) {
                    hairColor = AppConstant.parseHairColour(hairColourResult.getString("hairColour"));
                }
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private void loadAddressData() {
        try (Connection connection = AppConstant.dataSource.getConnection()) {
            if (foundPerson) {
                connection.setAutoCommit(false);

                //Get the data for an Address
                PreparedStatement addressData = connection.prepareStatement(sqlAddress());
                addressData.setString(1, ssn);
                ResultSet addressResult = addressData.executeQuery();
                connection.commit();

                while (addressResult.next()) {
                    street = addressResult.getString("street");
                    city = addressResult.getString("city");
                    zipCode = addressResult.getInt("zipCode");
                    country = addressResult.getString("country");
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }

        address = new Address(street, zipCode, city, country);
    }

    private String sqlPerson() {
        return "SELECT SSN, firstname, lastname, " +
                "gender, wanted, missing, custody, " +
                "suspect, phonenumber " +
                "FROM person " +
                "WHERE SSN = ?";
    }

    private String sqlEmployee() {
        return "SELECT policeID, salary, Person_SSN, " +
                "username, `e-mail`, jobtitle " +
                "FROM police " +
                "WHERE Person_SSN = ?";
    }

    private String sqlSuspect() {
        return "SELECT Person_SSN, build, weight, " +
                "height, characteristics, " +
                "Ethnicity_EthnicityID, " +
                "eyeColour_eyeColourID," +
                "HairColour_hairColourID " +
                "FROM suspect " +
                "WHERE Person_SSN = ?";
    }

    private String sqlEthnicity() {
        return "SELECT ethnicity " +
                "FROM ethnicity " +
                "WHERE EthnicityID IN " +
                "(SELECT Ethnicity_EthnicityID " +
                "FROM suspect " +
                "WHERE Person_SSN = ?)";
    }

    private String sqlEyeColour() {
        return "SELECT eyeColourcol " +
                "FROM eyecolour " +
                "WHERE eyeColourID IN " +
                "(SELECT eyeColour_eyeColourID " +
                "FROM suspect " +
                "WHERE Person_SSN = ?)";
    }

    private String sqlHairColour() {
        return "SELECT hairColour " +
                "FROM haircolour " +
                "WHERE hairColourID IN " +
                "(SELECT HairColour_hairColourID " +
                "FROM suspect " +
                "WHERE Person_SSN = ?)";
    }

    private String sqlAddress() {
        return "SELECT zipCode, " +
                "city, street, country " +
                "FROM address " +
                "WHERE zipCode AND street IN " +
                "(SELECT Address_street " +
                "FROM address_has_person " +
                "WHERE Person_SSN = ?)";
    }

    //TODO Tweak these methods to no build every attribute e.g. Notifier's height is not needed

    private ItAdministrator createItAdministrator() {
        return (ItAdministrator) new ItAdministratorBuilder()
                .withUserName(userName)
                .withPassword(password)
                .withId(id)
                .withTitle(title)
                .withSalary(salary)
                .withEmail(email)
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(height)
                .withCrimeRecord(crimeRecord)
                .withPhoneNumber(phoneNumber)
                .withGender(gender)
                .isWanted(isWanted)
                .isMissing(isMissing)
                .inCustody(inCustody)
                .isSuspect(isSuspect)
                .build();
    }

    private Person createMissingPerson() {
        return (MissingPerson) new MissingPersonBuilder()
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(height)
                .withCrimeRecord(crimeRecord)
                .withPhoneNumber(phoneNumber)
                .withGender(gender)
                .isWanted(isWanted)
                .isMissing(isMissing)
                .inCustody(inCustody)
                .isSuspect(isSuspect)
                .build();
    }

    private Person createNotifier() {
        return (Notifier) new NotifierBuilder()
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(height)
                .withCrimeRecord(crimeRecord)
                .withPhoneNumber(phoneNumber)
                .withGender(gender)
                .isWanted(isWanted)
                .isMissing(isMissing)
                .inCustody(inCustody)
                .isSuspect(isSuspect)
                .build();
    }

    private Person createPolice() {
        return (Police) new PoliceBuilder()
                .withUserName(userName)
                .withPassword(password)
                .withId(id)
                .withTitle(title)
                .withSalary(salary)
                .withEmail(email)
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(height)
                .withCrimeRecord(crimeRecord)
                .withPhoneNumber(phoneNumber)
                .withGender(gender)
                .isWanted(isWanted)
                .isMissing(isMissing)
                .inCustody(inCustody)
                .isSuspect(isSuspect)
                .build();
    }

    private Person createPoliceChief() {
        return (PoliceChief) new PoliceChiefBuilder()
                .withUserName(userName)
                .withPassword(password)
                .withId(id)
                .withTitle(title)
                .withSalary(salary)
                .withEmail(email)
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(height)
                .withCrimeRecord(crimeRecord)
                .withPhoneNumber(phoneNumber)
                .withGender(gender)
                .isWanted(isWanted)
                .isMissing(isMissing)
                .inCustody(inCustody)
                .isSuspect(isSuspect)
                .build();
    }

    private Person createSuspect() {
        return (Suspect) new SuspectBuilder()
                .hasEthnicity(ethnicity)
                .hasWeight(weight)
                .hasCharacteristic(characteristic)
                .withHairColor(hairColor)
                .withEyeColor(eyeColor)
                .hasBuild(build)
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(height)
                .withCrimeRecord(crimeRecord)
                .withPhoneNumber(phoneNumber)
                .withGender(gender)
                .isWanted(isWanted)
                .isMissing(isMissing)
                .inCustody(inCustody)
                .isSuspect(isSuspect)
                .build();
    }

    private Person createVisitor() {
        return (Visitor) new VisitorBuilder()
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(height)
                .withCrimeRecord(crimeRecord)
                .withPhoneNumber(phoneNumber)
                .withGender(gender)
                .isWanted(isWanted)
                .isMissing(isMissing)
                .inCustody(inCustody)
                .isSuspect(isSuspect)
                .build();
    }

    private Person createWitness() {
        return (Witness) new WitnessBuilder()
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(height)
                .withCrimeRecord(crimeRecord)
                .withPhoneNumber(phoneNumber)
                .withGender(gender)
                .isWanted(isWanted)
                .isMissing(isMissing)
                .inCustody(inCustody)
                .isSuspect(isSuspect)
                .build();
    }
}