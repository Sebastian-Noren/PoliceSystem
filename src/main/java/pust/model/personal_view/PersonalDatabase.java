package pust.model.personal_view;

import pust.model.utility.database_connection.DBCPDataSource;

import java.sql.*;
import java.util.ArrayList;

public class PersonalDatabase {

    public TempPerson getPerson(String ssn) {
        boolean wanted = false, missing = false;
        String personSSN = null;
        String firstname = null;
        String lastname = null;
        String gender = null;
        String streetAddres = null;
        String zipcode = null;
        String city = null;
        String country = null;
        String wantedtemp;
        try {
            Connection con = DBCPDataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select SSN,firstname,lastname,gender, \n" +
                    "street,zipCode,city,country,wanted, missing  from person\n" +
                    "join address_has_person on person.SSN = address_has_person.Person_SSN\n" +
                    "join address on address.zipCode = address_has_person.Address_zipCode\n" +
                    " where  person.SSN like '"+ssn+"';");
            while(rs.next()){
                personSSN = rs.getString(1);
                firstname = rs.getString(2);
                lastname = rs.getString(3);
                gender = rs.getString(4);
                streetAddres = rs.getString(5);
                zipcode = rs.getString(6);
                city = rs.getString(7);
                country = rs.getString(8);
                wantedtemp = rs.getString(9);
                int temp = Integer.parseInt(wantedtemp);
                wanted = temp != 0;
                String missingtemp = rs.getString(10);
                int temp2 = Integer.parseInt(missingtemp);
                missing = temp2 != 0;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return new TempPerson(personSSN, firstname, lastname, gender, new TempAddres(streetAddres, zipcode, city, country), wanted, missing);
    }

    public ArrayList<TempCriminalRecord> getCrimeRecord(String ssn) {
        ArrayList<TempCriminalRecord> crimRecords = new ArrayList<>();
        try {
            Connection con = DBCPDataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select convictiondate, criminalcase, crimeType, crimedate, Address_street, city, term  from crime\n" +
                    "join crimeregister on crime.crimeID = crimeregister.Crime_CrimeID\n" +
                    "join convictionterm on convictionterm.termID = crimeregister.convictionterm_termID\n" +
                    "join address on address.zipCode = crimeregister.Address_zipCode\n" +
                    " where  crimeregister.Person_SSN ='"+ssn+"' ;");
            while(rs.next()){
                String convictionDate = rs.getString(1);
                String criminalCase = rs.getString(2);
                String crimeType = rs.getString(3);
                String crimeDate = rs.getString(4);
                String streetAddress = rs.getString(5);
                String city = rs.getString(6);
                String term = rs.getString(7);
                crimRecords.add(new TempCriminalRecord(convictionDate,criminalCase,crimeType,crimeDate,streetAddress,city,term));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("leaving");
        return crimRecords;
    }

}
