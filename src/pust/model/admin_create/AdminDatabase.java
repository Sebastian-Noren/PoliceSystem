package pust.model.admin_create;

import javafx.scene.control.Alert;
import pust.model.utility.AppConstant;


import java.sql.*;
import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AdminDatabase {

    Statement statement;

   public void connect(){
        try{
            String url = "jdbc:mysql://"+ AppConstant.getDatabaseHost()+":3306/"+AppConstant.getDatabaseName()+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,AppConstant.getCurrentUser(),AppConstant.getCurrentUserPass());
            statement = con.createStatement();
        } catch(Exception e){
            System.err.println(e);
        }
    }

   public void createUsersSQL(String accName, String accPassText) {
        try {
            statement.executeUpdate("CREATE USER '" + accName + "'@'" + AppConstant.getDatabaseHost() + "' IDENTIFIED BY '" + accPassText + "';");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean insertPoliceSQL(int policeID, String strSSN, String randGenUserName, String strMail, String policeRole) {
        try {
            String prepareSQL = "INSERT INTO `"+AppConstant.getDatabaseName()+"`.`police` (`policeID`, `Person_SSN`, `username`, `E-mail`, `Job title`) VALUES " +
                    "('"+policeID+"', '"+strSSN+"', '"+randGenUserName+"', '"+strMail+"', '"+policeRole+"');";
            statement.executeUpdate(prepareSQL);
            return true;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("");
            alert.setContentText("The person with this SSN "+strSSN+" does not exist in the database.");
            alert.showAndWait();
            return false;
        }
    }

   public void grantOptionsSQL(String grant, String accName) {
        try {
            statement.executeUpdate("GRANT "+grant+" ON *.* TO '" + accName + "'@'" + AppConstant.getDatabaseHost() +"';");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public void removeUsersSQL(String userToDelete) {
        try {
            statement.executeUpdate("DROP USER '"+userToDelete+"'@'" + AppConstant.getDatabaseHost() + "';");
            statement.executeUpdate("DELETE FROM `"+AppConstant.getDatabaseName()+"`.`police` WHERE (`username` = '"+userToDelete+"');");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public ArrayList<String> getUsersAdmin() {
        ArrayList<String> userReturn = new ArrayList<>();
        try {
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
