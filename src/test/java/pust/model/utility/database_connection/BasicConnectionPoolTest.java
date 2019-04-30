package pust.model.utility.database_connection;

import org.junit.Assert;
import org.junit.Test;
import pust.model.entity.Person;
import pust.model.enumerations.PersonType;
import pust.model.utility.LinuxRemoteConnection;
import pust.model.utility.random_person_generator.RandomPerson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BasicConnectionPoolTest {

    private IConnectionPool connectionPool;


    @Test
    public void devCreatePersonAndInjectToDatabase() {
        Person person = new RandomPerson(PersonType.VISITOR).generateRandomPerson();

        LinuxRemoteConnection.remoteConnect();
        PreparedStatement createPerson = null;
        PreparedStatement activateDatabase = null;
        String usedDatabase = "USE pustgis;";
        String insertPerson = "INSERT INTO person (" +
                "SSN, firstname, lastname, gender, wanted, missing) " +
                "VALUES (?, ?, ?, 'M', 0, 0);";
        try {
            createConnectionPool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(person.getPersonalNumber().getPersonalNumber());

        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            activateDatabase = connection.prepareStatement(usedDatabase);
            createPerson = connection.prepareStatement(insertPerson);
            createPerson.setString(1, person.getPersonalNumber().getPersonalNumber());
            createPerson.setString(2, person.getFirstName());
            createPerson.setString(3, person.getSurname());
            activateDatabase.executeUpdate();
            createPerson.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void devDatabaseConnection() throws SQLException {
        LinuxRemoteConnection.remoteConnect();
        System.out.println("Connecting to database ...");
        IConnectionPool connectionPool = BasicConnectionPool
                .create("jdbc:mysql://localhost:4321",
                        "root",
                        "6978f28c972457220d4e72398bb9e000");
        try {
            Assert.assertTrue(connectionPool.getConnection().isValid(1));
        } catch (AssertionError e) {
            System.out.println("Connection to database failed");
            throw e;
        }
        System.out.println("Connection to database complete");
    }

    private void createConnectionPool() throws SQLException {
        connectionPool = BasicConnectionPool
                .create("jdbc:mysql://localhost:4321",
                        "root",
                        "6978f28c972457220d4e72398bb9e000");
    }


    @Test
    public void testPath() throws IOException {
       File file = new File("src/main/resources/files/cities.txt");
       if (file.exists()){
           System.out.println("Exists");
       }
    }

}