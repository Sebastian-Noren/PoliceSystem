package pust.model.entity;
import org.junit.*;
import pust.model.database_functionality.InsertPerson;
import pust.model.entity.entity_builder.SuspectBuilder;
import pust.model.entity.entity_builder.VisitorBuilder;
import pust.model.enumerations.Color;
import pust.model.enumerations.Ethnicity;
import pust.model.enumerations.PersonType;
import pust.model.utility.LinuxRemoteConnection;
import pust.model.utility.random_person_generator.RandomPerson;

import static org.junit.Assert.*;

public class GenericTest {

    @Test
    public void addRandomPersonToDatabase(){
        LinuxRemoteConnection.remoteConnect();
        InsertPerson insertPerson = new InsertPerson(new RandomPerson(PersonType.SUSPECT).generateRandomPerson());
    }

    @Test
    public void testIfEnumOrdinalReturnsIndexPosition(){
        int expected = 1;

        int actual = Ethnicity.AFRICAN.ordinal();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void buildPatternExample() {
        Suspect suspect = (Suspect) new SuspectBuilder()
                .withFirstName("Ali")
                .withSurname("Quick")
                .build();

        System.out.println(suspect.getFirstName());
        System.out.println(suspect.getEthnicity());

    }

    @Test
    public void createVisitorWithFirstName() {
        // Arrange
        final String expected = "Christoffer";

        // Act
        final Visitor actual = (Visitor) new VisitorBuilder().withFirstName("Christoffer").build();

        // Assert
        Assert.assertEquals(expected, actual.getFirstName());
    }

    @Test
    public void createSuspectWithYellowHair() {
        final Color.hairColor expected = Color.hairColor.BLONDE;

        final Suspect actual = (Suspect) new SuspectBuilder().withHairColor(Color.hairColor.BLONDE).build();

        Assert.assertEquals(expected, actual.getHairColor());
    }

    @Test
    public void emptyTest() {

    }

    @Test
    public void deleteTest() {

    }


}
