package pust.model.database_functionality;

import pust.model.utility.AppConstant;


import java.sql.*;
import java.util.ArrayList;

public class AdminDatabase {

    public boolean insertPoliceSQL(int policeID, String strSSN, String randGenUserName, String strMail, String policeRole) {
        try {
            Connection con = AppConstant.dataSource.getConnection();
            Statement statement = con.createStatement();
            String prepareSQL = "INSERT INTO `"+AppConstant.getDatabaseName()+"`.`police` (`policeID`, `Person_SSN`, `username`, `e-mail`, `jobtitle`) VALUES " +
                    "('"+policeID+"', '"+strSSN+"', '"+randGenUserName+"', '"+strMail+"', '"+policeRole+"');";
            statement.executeUpdate(prepareSQL);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            AppConstant.alertBoxWarning("Warning!","The person with this SSN "+strSSN+" does not exist in the database.");
            return false;
        }
    }

   public void createUsersSQL(String accName, String accPassText) {
        try {
            Connection con = AppConstant.dataSource.getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE USER '" + accName + "'@'" + AppConstant.getDatabaseHost() + "' IDENTIFIED BY '" + accPassText + "';");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public void grantOptionsSQL(String grant, String accName) {
        try {
            Connection con = AppConstant.dataSource.getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate("GRANT "+grant+" ON *.* TO '" + accName + "'@'" + AppConstant.getDatabaseHost() +"';");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public void removeUsersSQL(String userToDelete) {
        try {
            Connection con = AppConstant.dataSource.getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate("DROP USER '"+userToDelete+"'@'" + AppConstant.getDatabaseHost() + "';");
            statement.executeUpdate("DELETE FROM `"+AppConstant.getDatabaseName()+"`.`police` WHERE (`username` = '"+userToDelete+"');");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public ArrayList<String> getUsersAdmin() {
        ArrayList<String> userReturn = new ArrayList<>();
        try {
            Connection con = AppConstant.dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT user FROM mysql.user;");

            while(rs.next()){
                userReturn.add(rs.getString(1));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return userReturn;
    }
}
