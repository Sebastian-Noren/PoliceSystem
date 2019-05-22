package pust.model.database_functionality;

import pust.model.utility.database_connection.DBCPDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLDatabase {
    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

    public ArrayList<String> getSuspects() {
        ArrayList<String> userReturn = new ArrayList<>();
        try {
            Connection con = DBCPDataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement suspectListData = con.prepareStatement(sqlSuspect());
            ResultSet rs = suspectListData.executeQuery();
            con.commit();

            while (rs.next()) {
                StringJoiner sj = new StringJoiner(" ");
                String str1 = rs.getString(1);
                String str2 = rs.getString(2);
                String name = String.format("%s %s:", str1, str2);
                sj.add(name);
                sj.add(rs.getString(3));
                userReturn.add(sj.toString());
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return userReturn;
    }

    private String sqlSuspect() {
        return "select firstname,lastname, SSN from person where person.wanted = true;";
    }

    public String getWantedCrime(String splitSSN) {
        StringJoiner sj = new StringJoiner(", ");
        try {
            Connection con = DBCPDataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement suspectCrimeData = con.prepareStatement(sqlCrime());
            suspectCrimeData.setString(1, splitSSN);
            ResultSet rs = suspectCrimeData.executeQuery();
            con.commit();
            while (rs.next()) {
                sj.add(rs.getString(1));
            }
            String sentence = sj.toString();
            String replacement = " and";
            String toReplace = ",";
            int start = sentence.lastIndexOf(toReplace);
            if (start >= 0) {
                StringBuilder builder = new StringBuilder();
                builder.append(sentence, 0, start);
                builder.append(replacement);
                builder.append(sentence.substring(start + toReplace.length()));
                return builder.toString();
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return sj.toString();
    }

    private String sqlCrime() {
        return "select crimeType from person\n" +
                "join suspect on person.SSN = suspect.Person_SSN\n" +
                "join suspect_of_crime on suspect_of_crime.Suspect_Person_SSN = suspect.Person_SSN\n" +
                "join crime on crime.crimeID = suspect_of_crime.Crime_crimeID\n" +
                "where  person.wanted = true AND person.SSN = ?";
    }


    public String getPolice(String splitSSN) {
        String getSSN = "";
        try {
            Connection con = DBCPDataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement suspectCrimeData = con.prepareStatement(sqlOfficer());
            suspectCrimeData.setString(1, splitSSN);
            ResultSet rs = suspectCrimeData.executeQuery();
            con.commit();
            while (rs.next()) {
                getSSN = rs.getString(1);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return getSSN;
    }

    private String sqlOfficer() {
        return "select Person_SSN from police where police.username = ?";
    }


}
