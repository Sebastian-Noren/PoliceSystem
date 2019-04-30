package pust.model.personal_view;



import pust.model.utility.AppConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class PersonalDatabase {

    Statement statement;

    public void connect(){
        try{
            String url = "jdbc:mysql://"+ AppConstant.getDatabaseHost()+":3306/"+AppConstant.getDatabaseName()+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,AppConstant.getCurrentUser(),AppConstant.getCurrentUserPass());
            statement = con.createStatement();
            System.out.println("Connected");
        } catch(Exception e){
            System.err.println(e);
            System.out.println("connect fail");
        }
    }

    public String getPerson(String ssn) {
        StringJoiner sj = new StringJoiner(":");
        try {
            ResultSet rs = statement.executeQuery("select SSN,firstname,lastname,gender, \n" +
                    "street,zipCode,city,country,wanted, missing  from person\n" +
                    "join address_has_person on person.SSN = address_has_person.Person_SSN\n" +
                    "join address on address.zipCode = address_has_person.Address_zipCode\n" +
                    " where  person.SSN like '"+ssn+"';");

            while(rs.next()){
                sj.add(rs.getString(1));
                sj.add(rs.getString(2));
                sj.add(rs.getString(3));
                sj.add(rs.getString(4));
                sj.add(rs.getString(5));
                sj.add(rs.getString(6));
                sj.add(rs.getString(7));
                sj.add(rs.getString(8));
                sj.add(rs.getString(9));
                sj.add(rs.getString(10));

            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return  sj.toString();
    }

    public ArrayList<String> getCrimeRecord(String ssn) {
        ArrayList<String> strRecords = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("select convictiondate, criminalcase, crimeType, crimedate, Address_street, city, term  from crime\n" +
                    "join crimeregister on crime.crimeID = crimeregister.Crime_CrimeID\n" +
                    "join convictionterm on convictionterm.termID = crimeregister.convictionterm_termID\n" +
                    "join address on address.zipCode = crimeregister.Address_zipCode\n" +
                    " where  crimeregister.Person_SSN ='"+ssn+"' ;");

            while(rs.next()){
                StringJoiner sj = new StringJoiner(":");
                sj.add(rs.getString(1));
                sj.add(rs.getString(2));
                sj.add(rs.getString(3));
                sj.add(rs.getString(4));
                sj.add(rs.getString(5));
                sj.add(rs.getString(6));
                sj.add(rs.getString(7));
                strRecords.add(sj.toString());
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return strRecords;
    }

}
