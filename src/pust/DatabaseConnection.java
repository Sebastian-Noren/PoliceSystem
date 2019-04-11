package pust;

import java.sql.*;

public class DatabaseConnection {

    Statement statement;

    public DatabaseConnection(String userName, String passWord){
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://"+AppConstant.DATABASE_HOST +"/"+AppConstant.DATABASE_NAME+"?useTimezone=true&serverTimezone=UTC";
           // String username = "root";
          //  String password = "root";
           // Class.forName(driver);

            Connection con = DriverManager.getConnection(url,userName,passWord);
            statement = con.createStatement();
            System.out.println("Connected to database!");
        } catch(Exception e){
            System.err.println(e);
            System.err.println("Connection fail");
        }
    }

}
