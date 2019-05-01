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
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasicConnectionPoolTest {

    private IConnectionPool connectionPool;

    /**
     * The most basic connection example to connect to the database.
     * The test consists of two examples:
     * 1. Using the custom the connection pool
     * 2. Using the DBCP connection pool from Apache commons
     */

    @Test
    public void devBasicDatabaseConnection() {
        // In the real program the remote connection is already done
        LinuxRemoteConnection.remoteConnect();

        //Create the connection pool if using the custom pool
        try {
            createConnectionPool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Fetch a connection from the connection pool
        try (Connection connection = connectionPool.getConnection()) {
            //Some SQL statements


            //Return the connection back to the pool
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //If the DBCP connection pool is used it is simply called when
        //creating the connection
        try (Connection connection = DBCPDataSource.getConnection()) {
            //Some SQL statements

            //No need to return the connection back to the pool as it is
            //handled by the DBCP.
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /*
     * This method creates a custom connection pool
     * and gets a connection from the pool. The sql syntax is
     * then executed and the connection is returned back to
     * the pool.
     */

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
            connectionPool.releaseConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method also creates a pool but
     * it is an Apache common component and added via
     * maven dependencies.
     * https://commons.apache.org/proper/commons-dbcp/
     */

    @Test
    public void devDBCPDatabaseConnection() throws SQLException {
        LinuxRemoteConnection.remoteConnect();
        PreparedStatement database = null;
        PreparedStatement firstName = null;
        String useDataBase = "USE pustgis;";
        String name = "SELECT firstname FROM person WHERE lastname = 'Muhammed';";
        try (Connection connection = DBCPDataSource.getConnection()) {

            connection.setAutoCommit(false);
            database = connection.prepareStatement(useDataBase);
            firstName = connection.prepareStatement(name);
            database.execute();
            ResultSet rs = firstName.executeQuery(name);
            String temp = "";
            while (rs.next()) {
                temp = rs.getString("firstname");
            }
            Assert.assertEquals("Ali", temp);
            System.out.println("The first name is: " + temp);
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
        if (file.exists()) {
            System.out.println("Exists");
        }
    }

}