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
            String url = "jdbc:mysql://"+ AppConstant.DATABASE_HOST +"/"+AppConstant.DATABASE_NAME+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,AppConstant.CURRENT_USER,AppConstant.CURRENT_USER_PASS);
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
            System.out.println("user complete");
        } catch (SQLException ex) {
            System.err.println("Can´t create user");
        }
    }


    void removeUsersSQL(String userToDelete) {
        try {
            statement.executeUpdate("DROP USER '"+userToDelete+"'@'" + AppConstant.DATABASE_HOST + "';");
            System.out.println("user Deleted");
        } catch (SQLException ex) {
            System.err.println("Can´t delete user");
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
            System.out.println("executing fail");
        }
        return userReturn;
    }
}
