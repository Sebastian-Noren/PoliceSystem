package pust.model.admin_create;

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
