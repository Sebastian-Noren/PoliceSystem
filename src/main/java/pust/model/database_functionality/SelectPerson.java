package pust.model.database_functionality;

import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.PersonalNumber;
import pust.model.enumerations.PersonType;
import pust.model.utility.database_connection.DBCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectPerson {

    private Person person;

    public Person selectPerson(String ssn) {


        return person;
    }

    public void insertPerson(Person person) {

    }

    private void setPersonType(String ssn){
        if (isItAdministrator(ssn)){
            createItAdministrator(ssn);
        } else if (isPolice(ssn)){
            createPolice(ssn);
        } else if (isPoliceChief(ssn)){
            createPoliceChief(ssn);
        } else if (isSuspect(ssn)){
            createSuspect(ssn);
        } else {
            createPerson(ssn);
        }
    }

    private void createItAdministrator(String ssn) {

    }

    private void createPolice(String ssn){

    }

    private void createPoliceChief(String ssn){

    }

    private void createSuspect(String ssn){

    }

    private void createPerson(String ssn){

    }

    private boolean isSuspect(String ssn) {
        return foundPerson("suspect", "ssn", PersonType.SUSPECT.toString().toLowerCase());
    }

    private boolean isItAdministrator(String ssn) {
        return foundPerson("police", "jobtitle", PersonType.ITADMINISTRATOR.toString().toLowerCase());
    }

    private boolean isPolice(String ssn) {
        return foundPerson("police", "jobtitle", PersonType.POLICE.toString().toLowerCase());
    }

    private boolean isPoliceChief(String ssn) {
        return foundPerson("police", "jobtitle", PersonType.POLICECHIEF.toString().toLowerCase());
    }


    private boolean foundPerson(String table, String column, String personType) {
        ResultSet resultSet = null;
        boolean hasSsn = false;
        PreparedStatement getSsn = null;
        try (Connection connection = DBCPDataSource.getConnection()) {
            connection.setAutoCommit(false);
            getSsn = connection.prepareStatement(select(table, column));
            if (personType.equals(PersonType.SUSPECT.toString())) {
                getSsn.setString(6, personType.toString());
            } else {
                getSsn.setString(1, column);
            }
            resultSet = getSsn.executeQuery();
            connection.commit();
            hasSsn = resultSet.next();

        } catch (SQLException e) {
            //TODO Create log and add to file
        }
        return hasSsn;
    }

    private String select(String table, String column) {
        return "SELECT " + column + " FROM " + table + " WHERE " + column + " = ?;";
    }


}
