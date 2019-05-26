package pust.model.login;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import pust.model.utility.LinuxRemoteConnection;
import pust.model.utility.database_connection.DBCPDataSource;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class LogInModelTest {

    private static BasicDataSource ds = new BasicDataSource();

    @Test
    public void logInAuth() {
        LinuxRemoteConnection.remoteConnect();

        String userName = "chris";
        String passWordText = "test1";

            try {
                ds.setUrl("jdbc:mysql://localhost:4321/pustgis?&useSSL=FALSE");
                ds.setUsername(userName);
                ds.setPassword(passWordText);
                //"6978f28c972457220d4e72398bb9e000"
                ds.getConnection();
                System.out.println("Login Successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}