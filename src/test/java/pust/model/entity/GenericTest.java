package pust.model.entity;

import org.junit.*;
import pust.model.database_functionality.InsertPerson;
import pust.model.entity.entity_builder.SuspectBuilder;
import pust.model.entity.entity_builder.VisitorBuilder;
import pust.model.enumerations.Color;
import pust.model.enumerations.Ethnicity;
import pust.model.enumerations.PersonType;
import pust.model.utility.Encrypt;
import pust.model.utility.LinuxRemoteConnection;
import pust.model.utility.random_person_generator.RandomPerson;

import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;

import static org.junit.Assert.*;

public class GenericTest {

    @Test
    public void testParseEmail(){
        String[] arrEmail = "christoffer.quick@pustgis.se".split("@");
        StringBuilder email = new StringBuilder();
        email.append(arrEmail[0]);
        email.append("'@'");
        email.append(arrEmail[1]);
        for (int i = 0; i < email.length(); i++) {
            System.out.println(email.charAt(i));
        }
    }

    @Test
    public void preparedStatement() {
        PreparedStatement preparedStatement = null;
        try (Connection connection = DriverManager.getConnection("Some connection")) {
            int accountId = 19845;
            String sql = "SELECT * FROM account WHERE accountID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void addRandomSuspectToDatabase() {
        LinuxRemoteConnection.remoteConnect();
        InsertPerson insertPerson = new InsertPerson(new RandomPerson(PersonType.SUSPECT).generateRandomPerson());
    }

    @Test
    public void addRandomePoliceToDatabase() {
        LinuxRemoteConnection.remoteConnect();
        InsertPerson insertPerson = new InsertPerson(new RandomPerson(PersonType.POLICE).generateRandomPerson());
    }

    @Test
    public void addRandomPersonToDatabase() {
        LinuxRemoteConnection.remoteConnect();
        InsertPerson insertPerson = new InsertPerson(new RandomPerson(PersonType.NOTIFIER).generateRandomPerson());
    }

    @Test
    public void testIfEnumOrdinalReturnsIndexPosition() {
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
    public void decrypt() {
        Encrypt encrypt = new Encrypt();
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream("encrypt.bin"))) {
            encrypt = (Encrypt) oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(encrypt.getPassword());
    }

    @Test
    public void splitAtTest() {
        String myMail = "christoffer.quick@hotmail.com";
        String[] arrEmail = myMail.split("@");
        String email = arrEmail[0] +
                "\\@" +
                arrEmail[1];
        System.out.println(email);
    }
}

