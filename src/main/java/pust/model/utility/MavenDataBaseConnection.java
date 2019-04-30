package pust.model.utility;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MavenDataBaseConnection {

    private static int listeningPort;
    private static String remoteHost;
    private static int remotePort;

    public static void remoteConnect() {
        String user = "root";
        String password = "bd59487c64d470f9e1bf719988a54950";
        String host = "206.189.27.211";
        int port = 22;

        try {

            JSch jSch = new JSch();
            Session session = jSch.getSession(user, host, port);
            listeningPort = 4321;
            remoteHost = "localhost";
            remotePort = 3306;

            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            System.out.println("Connected!");
            int assignedPort = session.setPortForwardingL(
                    listeningPort, remoteHost, remotePort
            );
            System.out.println("Localhost connected on: " + assignedPort + " to " + remoteHost  + " on " + host +":" + remotePort);

        } catch (Exception ex) {
            System.err.print(ex);
        }

    }


    public static void dataBaseConnection() {
        try {
            remoteConnect();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println("\nSelecting all firstnames from the table person\n");
        Connection connection = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://" + remoteHost +":" + listeningPort + "/";
        String db = "pustgis";
        String dbUser = "root";
        String dbPasswd = "6978f28c972457220d4e72398bb9e000";
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url+db, dbUser, dbPasswd);
            try{
                Statement st = connection.createStatement();
                String sql = "SELECT * FROM person";

                ResultSet mySql = st.executeQuery(sql);
                while (mySql.next()){
                    String person = mySql.getString("firstname");
                    System.out.println(person + "\n");
                }

            }
            catch (SQLException s){
                System.out.println("SQL statement is not executed!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("SQL statement complete");
    }

}
