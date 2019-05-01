package pust.model.utility;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LinuxRemoteConnection {

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
            System.out.println("Localhost connected on: " + assignedPort + " to " + remoteHost + " on " + host + ":" + remotePort);

        } catch (Exception ex) {
            System.err.print(ex);
        }
    }
}
