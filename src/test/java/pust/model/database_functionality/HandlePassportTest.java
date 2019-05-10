package pust.model.database_functionality;

import org.junit.Test;
import pust.model.administrative_functions.application_functions.Passport;
import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.PersonalNumber;
import pust.model.entity.Police;
import pust.model.enumerations.PersonType;
import pust.model.utility.LinuxRemoteConnection;
import pust.model.utility.random_person_generator.RandomPerson;

import static org.junit.Assert.*;

public class HandlePassportTest {

    @Test
    public void addPassport() {
        LinuxRemoteConnection.remoteConnect();
        Person visitor = new RandomPerson(PersonType.VISITOR).generateRandomPerson();
        Employee police = (Employee) new RandomPerson(PersonType.POLICE).generateRandomPerson();
        Passport passport = new Passport(visitor, police);
        HandlePassport.addPassport(passport);

        System.out.println("Passport ID:        " + passport.getPassportId());
        System.out.println("Height:             " + passport.getHeight());
        System.out.println("Police ID:          " + passport.getPoliceId());
        System.out.println("Personal number:    " + passport.getPersonalNumber());
    }

    @Test
    public void getPassport() {
        LinuxRemoteConnection.remoteConnect();
        /*
         * When generating a test person we get a valid random personal number.
         * This number is not in our database which means we need to set the number
         * to something we know exist in the database, hence the setter of the personal
         * number.
         * If we instead retrieve a personal number from the database the method would
         * depend on another untested method.
         */
        Person visitor = new RandomPerson(PersonType.VISITOR).generateRandomPerson();
        PersonalNumber directedPersonalNumber = new PersonalNumber(1989, 11, 10, 555, 5);
        visitor.setPersonalNumber(directedPersonalNumber);

        Employee police = (Employee) new RandomPerson(PersonType.POLICE).generateRandomPerson();
        Passport passport = HandlePassport.getPassport(police, visitor);

        System.out.println("Passport ID:        " + passport.getPassportId());
        System.out.println("Height:             " + passport.getHeight());
        System.out.println("Police ID:          " + passport.getPoliceId());
        System.out.println("Personal number:    " + passport.getPersonalNumber());
    }
}