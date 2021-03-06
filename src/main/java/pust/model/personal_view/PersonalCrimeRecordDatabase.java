package pust.model.personal_view;

import pust.model.utility.AppConstant;

import java.sql.*;
import java.util.ArrayList;

public class PersonalCrimeRecordDatabase {

    public ArrayList<CriminalRecord> getCrimeRecord(String ssn) {
        ArrayList<CriminalRecord> crimRecords = new ArrayList<>();
        try {
            Connection con = AppConstant.dataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement suspectCrimeData = con.prepareStatement(sqlCrimeRecord());
            suspectCrimeData.setString(1, ssn);
            ResultSet rs = suspectCrimeData.executeQuery();
            con.commit();
            while (rs.next()) {
                String convictionDate = rs.getString(1);
                String criminalCase = rs.getString(2);
                String crimeType = rs.getString(3);
                String crimeDate = rs.getString(4);
                String streetAddress = rs.getString(5);
                String city = rs.getString(6);
                String term = rs.getString(7);
                crimRecords.add(new CriminalRecord(convictionDate, criminalCase, crimeType, crimeDate, streetAddress, city, term));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return crimRecords;
    }

    private String sqlCrimeRecord() {
        return "select convictiondate, criminalcase, crimeType, crimedate, Address_street, city, term  from crime\n" +
                "join crimeregister on crime.crimeID = crimeregister.Crime_CrimeID\n" +
                "join convictionterm on convictionterm.termID = crimeregister.convictionterm_termID\n" +
                "join address on address.zipCode = crimeregister.Address_zipCode\n" +
                " where  crimeregister.Person_SSN = ?";
    }

}
