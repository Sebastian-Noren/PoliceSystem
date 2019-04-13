package pust;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    Statement statement;

    public DatabaseConnection(){
    }

    boolean Loginconnect(String userName, String passWord){
        try{

            String url = "jdbc:mysql://"+AppConstant.DATABASE_HOST +":3306/"+AppConstant.DATABASE_NAME+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,userName,passWord);
            statement = con.createStatement();
            System.out.println("Login okay!");
            //Saves the current user to a global
            AppConstant.CURRENT_USER = userName;
            AppConstant.CURRENT_USER_PASS = passWord;
            return true;
        } catch(Exception e){
            System.err.println(e);
            System.err.println("Connection fail and login fail");
            return false;
        }
    }


    //Connect method use this to connect to database aft login
    void connect(){
        try{
            String url = "jdbc:mysql://"+AppConstant.DATABASE_HOST +"/"+AppConstant.DATABASE_NAME+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,AppConstant.CURRENT_USER,AppConstant.CURRENT_USER_PASS);
            statement = con.createStatement();
            System.out.println("Connected to database!");
        } catch(Exception e){
            System.err.println(e);
            System.err.println("Connection fail");
        }
    }

}
