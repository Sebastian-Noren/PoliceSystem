package pust;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    Statement statement;

    public DatabaseConnection(){
    }

    public boolean Loginconnect(String userName, String passWord){
        try{

            String url = "jdbc:mysql://"+AppConstant.getDatabaseHost() +":3306/"+AppConstant.getDatabaseName()+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,userName,passWord);
            statement = con.createStatement();
            System.out.println("Login okay!");
            //Saves the current user to a global
            AppConstant.setCurrentUser(userName);
            AppConstant.setCurrentUserPass(passWord);
            return true;
        } catch(Exception e){
            System.err.println(e);
            System.err.println("Connection fail and login fail");
            return false;
        }
    }

    //Connect method use this to connect to database aft login
    public void connect(){
        try{
            String url = "jdbc:mysql://"+ AppConstant.getDatabaseHost()+":3306/"+AppConstant.getDatabaseName()+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,AppConstant.getCurrentUser(),AppConstant.getCurrentUserPass());
            statement = con.createStatement();
        } catch(Exception e){
            System.err.println(e);
        }
    }

}
