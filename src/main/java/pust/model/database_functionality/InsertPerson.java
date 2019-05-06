package pust.model.database_functionality;

import pust.model.entity.Employee;
import pust.model.entity.Notifier;
import pust.model.entity.Person;
import pust.model.entity.Suspect;
import pust.model.enumerations.Build;
import pust.model.utility.AppConstant;
import pust.model.utility.database_connection.DBCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pust.model.enumerations.Build.THIN;
import static pust.model.enumerations.Build.NORMAL;
import static pust.model.enumerations.Build.MUSCULAR;
import static pust.model.enumerations.Build.EXTENSIVE;

public class InsertPerson {
    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

    private Person person;
    private Suspect suspect;
    private Employee employee;

    public InsertPerson(Person person) {
        if (person instanceof Suspect) {
            this.person = person;
            this.suspect = (Suspect) person;
        } else if (person instanceof Employee) {
            this.person = person;
            this.employee = (Employee) person;
        } else {
            this.person = person;
            System.out.println(person.getFirstName());
        }
        updateDatabase();
    }


    private void updateDatabase() {
        if (person instanceof Suspect) {
            insertPerson();
            insertSuspect();
        } else if (person instanceof Employee) {
            insertPerson();
            insertEmployee();
        } else {
            insertPerson();
        }
    }

    private void insertSuspect() {

        PreparedStatement pstmt = null;

        try (Connection connection = DBCPDataSource.getConnection()) {

            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(sqlSuspect());
            pstmt.setString(1, suspect.getPersonalNumber().getPersonalNumber());
            System.out.println(suspect.getPersonalNumber().getPersonalNumber());
            pstmt.setString(2, parseBuild((Build) suspect.getBuild()));
            pstmt.setInt(3, suspect.getWeight());
            pstmt.setDouble(4, person.getHeight());
            pstmt.setString(5, suspect.getCharacteristic());
            pstmt.setInt(6, suspect.getEthnicity().ordinal() + 1);
            pstmt.setInt(7, suspect.getEyeColor().ordinal() + 1);
            pstmt.setInt(8, suspect.getHairColor().ordinal() + 1);
            pstmt.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, ex.toString(), ex);
                }
            }
        }
    }

    private void insertEmployee() {

    }

    private void insertPerson() {
        PreparedStatement pstmt = null;
        String sql = sqlPerson();

        try (Connection connection = DBCPDataSource.getConnection()) {

            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(sqlPerson());
            pstmt.setString(1, person.getPersonalNumber().getPersonalNumber());
            pstmt.setString(2, person.getFirstName());
            pstmt.setString(3, person.getSurname());
            pstmt.setString(4, calculateGender(person)); //FIXME Add gender to person class
            pstmt.setInt(5, 0);
            pstmt.setInt(6, 0);
            pstmt.setInt(7, 0);
            pstmt.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, ex.toString(), ex);
                }
            }
        }
    }

    private String sqlSuspect() {
        return "INSERT INTO suspect(" +
                "Person_SSN, build, weight, " +
                "height, characteristics, " +
                "Ethnicity_EthnicityID, " +
                "eyeColour_eyeColourID, " +
                "HairColour_hairColourID)" +
                "VALUES" +
                "(?, ?, ?, ?, ?, ?, ?, ?)";
    }

    private String sqlEmployee() {
        return null; //TODO Finish the method
    }

    private String sqlPerson() {
        return "INSERT INTO person(" +
                "SSN, firstname, lastname, " +
                "gender, wanted, missing, custody)" +
                "VALUES" +
                "(?, ?, ?, ?, ?, ?, ?)";
    }

    private String calculateGender(Person person) {
        if (AppConstant.isFemale(person.getPersonalNumber().getSerialNumber())) {
            return "F";
        } else {
            return "M";
        }
    }

    private String parseBuild(Build build) {
        switch (build) {
            case THIN:
                return "T";
            case NORMAL:
                return "N";
            case MUSCULAR:
                return "M";
            case EXTENSIVE:
                return "F";
            default:
                LOGGER.log(Level.FINER, "No build type found matching the cases");
                return "";

        }
    }
}
