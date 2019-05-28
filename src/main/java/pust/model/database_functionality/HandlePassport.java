package pust.model.database_functionality;

import pust.model.administrative_functions.application_functions.Passport;
import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.utility.AppConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Adds a new passport to the database or fetch an existing from the database
 */

public class HandlePassport {
    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

    public HandlePassport() {

    }

    /**
     * @param passport To add a new passport the object needs to be created outside of
     *                 this class passed as a parameter to the addPassport method. The
     *                 Method will then add the passport to the database.
     */

    public static void addPassport(Passport passport) {
        PreparedStatement pstmt = null;

        try (Connection connection = AppConstant.dataSource.getConnection()) {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(sqlAddPassport());
            pstmt.setInt(1, passport.getPassportId());
            pstmt.setInt(2, passport.getHeight());
            pstmt.setInt(3, passport.getPoliceId());
            pstmt.setString(4, passport.getPersonalNumber());
            pstmt.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    /**
     * To retrieve a passport, the method parameters are used to select
     * a specific passport from the database depending on the personal number.
     *
     * @param employee The current logged in employee.
     * @param visitor  The customer (visitor) the wants a new passport.
     * @return The method will instantiate a new passport object and return this
     * to the caller. It is therefore possible to do
     * Passport passport = HandlePassport.getPassport();
     */

    public static Passport getPassport(Employee employee, Person visitor) {
        Passport passport = new Passport(visitor, employee);
        PreparedStatement pstmt = null;
        int ppId = -1;
        int height = -1;
        int policeId = -1;
        String personalNumber = "";

        try (Connection connection = AppConstant.dataSource.getConnection()) {
            pstmt = connection.prepareStatement(sqlGetPassport());
            pstmt.setString(1, visitor.getPersonalNumber().getPersonalNumber());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ppId = rs.getInt("passportID");
                height = rs.getInt("height");
                policeId = rs.getInt("Police_policeID");
                personalNumber = rs.getString("Person_SSN");
            }
            passport.setPassportId(ppId);
            passport.setHeight(height);
            passport.setPoliceId(policeId);
            passport.setPersonalNumber(personalNumber);

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return passport;
    }

    private static String sqlAddPassport() {
        return "INSERT INTO passport(" +
                "passportID, height, " +
                "Police_policeID, " +
                "Person_SSN)" +
                "VALUES" +
                "(?, ?, ?, ?)";
    }

    private static String sqlGetPassport() {
        return "SELECT passportID, " +
                "height, Police_PoliceID," +
                "Person_SSN " +
                "FROM passport " +
                "WHERE Person_SSN=?";
    }
}
