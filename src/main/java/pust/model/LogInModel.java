package pust.model;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.SQLException;

public class LogInModel {

    // this method takes the count of failed log in attempts and displays appropriate messsage
   private static BasicDataSource ds = new BasicDataSource();
    public String passwordCounter(int wrongPass) {
        if (wrongPass > 0 && wrongPass < 3) {
            return "Incorrect username or password";
        } else if (wrongPass >= 3) {
            return "warning";
        } else {
            return "";
        }
    }
    public boolean LogInAuth(String userName, String passWordText){
        try {
            ds.setUrl("jdbc:mysql://localhost:4321/pustgis");
            ds.setUsername(userName);
            ds.setPassword(passWordText);
            //"6978f28c972457220d4e72398bb9e000"
            ds.getConnection();
            System.out.println("Login Successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
