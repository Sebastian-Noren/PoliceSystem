package pust.model.database_functionality;

import org.junit.Test;
import pust.model.enumerations.PersonType;
import pust.model.utility.LinuxRemoteConnection;
import pust.model.utility.random_person_generator.RandomPerson;
import static org.junit.Assert.*;
/*
 * This class tests the functionality of the InsertPerson class. The purpose
 * is to pass a valid Person object to the constructor of InsertPerson which in turn
 * adds the object to the database. Three different types are used to cover all the
 * possible cases in InsertPerson, Person, Employee and Suspect. In this case the
 * Person is represented by a Notifier, Employee by Police and Suspect by Suspect.
 */

public class InsertPersonTest {

    @Test
    public void addRandomSuspectToDatabase() {
        LinuxRemoteConnection.remoteConnect();
       // LinuxRemoteConnection.remoteConnect(userName.getText(), passWord.getText());
        InsertPerson insertPerson = new InsertPerson(new RandomPerson(PersonType.SUSPECT).generateRandomPerson());
    }

    @Test
    public void addRandomPoliceToDatabase() {
        LinuxRemoteConnection.remoteConnect();
      //  LinuxRemoteConnection.remoteConnect(userName.getText(), passWord.getText());
        InsertPerson insertPerson = new InsertPerson(new RandomPerson(PersonType.POLICE).generateRandomPerson());
    }

    @Test
    public void addRandomPersonToDatabase() {
        LinuxRemoteConnection.remoteConnect();
        InsertPerson insertPerson = new InsertPerson(new RandomPerson(PersonType.NOTIFIER).generateRandomPerson());
    }

}