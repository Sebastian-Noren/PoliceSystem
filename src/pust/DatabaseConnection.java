package pust;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseConnection {

    Statement statement;

    public DatabaseConnection(){
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/my_game?useTimezone=true&serverTimezone=UTC";
            String username = "root";
            String password = "root";
            //Class.forName(driver);

            Connection con = DriverManager.getConnection(url,username,password);
            statement = con.createStatement();
            System.out.println("Connected to database!");
        } catch(Exception e){
            System.out.println(e);
            System.out.println("Connection fail");
        }
    }

    public void  CreateUser() {
        Scanner input = new Scanner(System.in);
        String name, password;

        System.out.println("Enter USer name:");
        System.out.print(">> ");
        name = input.nextLine();
        System.out.println("Enter password:");
        System.out.print(">> ");
        password = input.nextLine();


        try {
            statement.executeUpdate("CREATE USER '"+ name+"'@'localhost' IDENTIFIED BY '"+password+"';");
            System.out.println("user complete");
        } catch (SQLException ex) {
            System.out.println("Can´t create user");
        }finally {
            //System.out.println("Insert Completed");
        }
    }

}
