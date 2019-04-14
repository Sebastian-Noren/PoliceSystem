package pust.adminCreate;

import pust.AppConstant;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AdminDatabase {

    Statement statement;


    //Connect method use this to connect to database aft login
    void connect(){
        try{
            String url = "jdbc:mysql://"+ AppConstant.DATABASE_HOST +":3306/"+AppConstant.DATABASE_NAME+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,AppConstant.getCurrentUser(),AppConstant.getCurrentUserPass());
            statement = con.createStatement();
            System.out.println("Connected to database!");
        } catch(Exception e){
            System.err.println(e);
            System.err.println("Connection fail");
        }
    }

    void createUsersSQL(String accName, String accPassText) {

        try {
            statement.executeUpdate("CREATE USER '" + accName + "'@'" + AppConstant.DATABASE_HOST + "' IDENTIFIED BY '" + accPassText + "';");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void grantOptionsSQL(String grant, String accName) {
        try {
            statement.executeUpdate("GRANT "+grant+" ON *.* TO '" + accName + "'@'" + AppConstant.DATABASE_HOST +"';");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void removeUsersSQL(String userToDelete) {
        try {
            statement.executeUpdate("DROP USER '"+userToDelete+"'@'" + AppConstant.DATABASE_HOST + "';");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    ArrayList<String> getUsersAdmin() {
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
