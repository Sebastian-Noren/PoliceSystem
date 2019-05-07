package pust.model.utility.random_person_generator;

import pust.model.administrative_functions.report_system.report.BaseReport;
import pust.model.administrative_functions.report_system.record.CriminalRecord;
import pust.model.entity.*;
import pust.model.entity.entity_builder.*;
import pust.model.enumerations.*;
import pust.model.utility.AppConstant;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
 * To generate a new person give the constructor a person type e.g.
 * POLICE.
 */

public class RandomPerson {

    private SecureRandom random = new SecureRandom();

    private PersonalNumber personalNumber;
    private String firstName;
    private String surname;
    private Address address;
    private CriminalRecord criminalRecord;
    private int height;
    private PersonType personType;

    public RandomPerson(PersonType personType) {
        this.personType = personType;
        personalNumber = createRandomPersonalNumber();
        firstName = new FirstNameSource().random(personalNumber.getSerialNumber());
        surname = new SurnameSource().random();
        address = createRandomAddress();
        criminalRecord = createCrimeReport();
        height = createRandomHeight(personalNumber.getSerialNumber());

        generateRandomPerson();
    }

    public Person generateRandomPerson() {


        switch (personType) {
            case ITADMINISTRATOR:
                return createRandomItAdministrator();
            case MISSINGPERSON:
                return createRandomMissingPerson();
            case NOTIFIER:
                return createRandomNotifier();
            case POLICE:
                return createRandomPolice();
            case POLICECHIEF:
                return createRandomPoliceChief();
            case SUSPECT:
                return createRandomSuspect();
            case VISITOR:
                return createRandomVisitor();
            case WITNESS:
                return createRandomWitness();
            default:
                return null;  //TODO Better return type if default case
        }
    }

    private Person createRandomItAdministrator() {
        return (ItAdministrator) new ItAdministratorBuilder()
                .withUserName(createUserName())
                .withPassword(createUserName())
                .withId(createRandomId())
                .withTitle(createRandomTitle())
                .withSalary(createRandomSalary())
                .withEmail(createRandomEmail())
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(createRandomHeight(personalNumber.getSerialNumber()))
                .withCrimeRecord(criminalRecord)
                .build();
    }

    private Person createRandomMissingPerson() {
        return (MissingPerson) new MissingPersonBuilder()
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(createRandomHeight(personalNumber.getSerialNumber()))
                .withCrimeRecord(criminalRecord)
                .build();
    }

    private Person createRandomNotifier() {
        return (Notifier) new NotifierBuilder()
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(createRandomHeight(personalNumber.getSerialNumber()))
                .withCrimeRecord(criminalRecord)
                .build();
    }

    private Person createRandomPolice() {
        return (Police) new PoliceBuilder()
                .withUserName(createUserName())
                .withPassword(createUserName())
                .withId(createRandomId())
                .withTitle(createRandomTitle())
                .withSalary(createRandomSalary())
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(createRandomHeight(personalNumber.getSerialNumber()))
                .withCrimeRecord(criminalRecord)
                .build();
    }

    private Person createRandomPoliceChief() {
        return (PoliceChief) new PoliceChiefBuilder()
                .withUserName(createUserName())
                .withPassword(createUserName())
                .withId(createRandomId())
                .withTitle(createRandomTitle())
                .withSalary(createRandomSalary())
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(createRandomHeight(personalNumber.getSerialNumber()))
                .withCrimeRecord(criminalRecord)
                .build();
    }

    private Person createRandomSuspect() {
        return (Suspect) new SuspectBuilder()
                .hasWeight(createRandomWeight(personalNumber.getSerialNumber()))
                .withGender(giveGender(personalNumber.getSerialNumber()))
                .hasEthnicity(createRandomEthnicity())
                .hasCharacteristic("John Doe")
                .withHairColor(createRandomHairColor())
                .withEyeColor(createRandomEyeColor())
                .hasBuild(createRandomBuild())
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(createRandomHeight(personalNumber.getSerialNumber()))
                .withCrimeRecord(criminalRecord)
                .build();
    }

    private Person createRandomVisitor() {
        return (Visitor) new VisitorBuilder()
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(createRandomHeight(personalNumber.getSerialNumber()))
                .withCrimeRecord(criminalRecord)
                .build();
    }

    private Person createRandomWitness() {
        return (Witness) new WitnessBuilder()
                .withPersonalNumber(personalNumber)
                .withFirstName(firstName)
                .withSurname(surname)
                .withAddress(address)
                .withHeight(createRandomHeight(personalNumber.getSerialNumber()))
                .withCrimeRecord(criminalRecord)
                .build();
    }

    private int createRandomHeight(int serialNumber) {
        if (AppConstant.isFemale(serialNumber)) {
            return ThreadLocalRandom.current().nextInt(147, 188);
        } else {
            return ThreadLocalRandom.current().nextInt(161, 212);
        }
    }

    private PersonalNumber createRandomPersonalNumber() {
        RandomPersonalNumber ssn = new RandomPersonalNumber();
        return new PersonalNumber(
                ssn.getYear(),
                ssn.getMonth(),
                ssn.getDay(),
                ssn.getSerialNumber(),
                ssn.getControlNumber());
    }

    private Address createRandomAddress() {
        RandomAddress address = new RandomAddress();
        return new Address(
                address.getStreetName(),
                address.getStreetNumber(),
                address.getZipCode(),
                address.getCity(),
                address.getCountry()
        );
    }

    private CriminalRecord createCrimeReport() {
        return new CriminalRecord(new ArrayList<BaseReport>());
    }

    private int createRandomSalary() {
        switch (personType) {
            case ITADMINISTRATOR:
                return ThreadLocalRandom.current().nextInt(28000, 35000);
            case POLICE:
                return ThreadLocalRandom.current().nextInt(24500, 32000);
            case POLICECHIEF:
                return ThreadLocalRandom.current().nextInt(38000, 59000);
            default:
                return -1;
        }
    }

    private Title createRandomTitle() {
        switch (personType) {
            case ITADMINISTRATOR:
                if (random.nextBoolean() && random.nextBoolean()) {
                    return Title.ITCOORDINATOR;
                } else {
                    return Title.ITADMINISTRATOR;
                }
            case POLICE:
                if (random.nextBoolean() && random.nextBoolean()) {
                    return Title.SUPERINTENDENT;
                } else if (random.nextBoolean()) {
                    return Title.INSPECTOR;
                } else {
                    return Title.ASSISTANT;
                }
            case POLICECHIEF:
                if (random.nextBoolean() && random.nextBoolean()) {
                    return Title.DISTRICTPOLICECOMMISSIONER;
                } else {
                    return Title.CHIEFSUPERINTENDENT;
                }
            default:
                return null;
        }
    }

    private int createRandomId() {
        return ThreadLocalRandom.current().nextInt(1000, 10000);
    }

    private String createUserName() {
        StringBuilder sb = new StringBuilder();
        if (firstName.length() < 3) {
            sb.append("0").append(firstName.charAt(0)).append(firstName.charAt(1));
        } else {
            sb.append(firstName.charAt(0)).append(firstName.charAt(1)).append(firstName.charAt(2));
        }
        sb.append(String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000)));
        return sb.toString();
    }

    private int createRandomWeight(int serialNumber) {
        if (AppConstant.isFemale(serialNumber)) {
            return ThreadLocalRandom.current().nextInt(50, 100);
        } else {
            return ThreadLocalRandom.current().nextInt(60, 120);
        }
    }

    private Gender giveGender(int serialNumber) {
        if (AppConstant.isFemale(serialNumber)) {
            return Gender.FEMALE;
        } else {
            return Gender.MALE;
        }
    }

    private Ethnicity createRandomEthnicity() {
        return Ethnicity.values()[ThreadLocalRandom.current().nextInt(Ethnicity.values().length)];
    }

    private Enum createRandomEyeColor() {
        return Color.eyeColor.values()[ThreadLocalRandom.current().nextInt(Color.eyeColor.values().length)];
    }
    private Enum createRandomHairColor() {
        return Color.hairColor.values()[ThreadLocalRandom.current().nextInt(Color.hairColor.values().length)];
    }

    private Enum createRandomBuild() {
        return Build.values()[ThreadLocalRandom.current().nextInt(Build.values().length)];
    }

    private String createRandomEmail(){
        return firstName.concat(".").concat(surname).concat("@pustgis.se");
    }

}
