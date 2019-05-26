package pust.model.utility;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import pust.model.database_functionality.InsertPerson;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LinuxRemoteConnection {
    private static final Logger LOGGER = Logger.getLogger(InsertPerson.class.getName());

    private static Encrypt encrypt;
    private static Session session = null;

    public static void remoteConnect() {
        readEncryption();
        String user = encrypt.getUser();
        String password = encrypt.getPassword();
        String host = encrypt.getHost();
        int port = encrypt.getPort();

        int sleepTimer = 50;
        do {
            try {
                JSch jSch = new JSch();
                session = jSch.getSession(user, host, port);
                int listeningPort = encrypt.getListeningPort();
                String remoteHost = encrypt.getRemoteHost();
                int remotePort = encrypt.getRemotePort();

                session.setPassword(password);
                session.setConfig("StrictHostKeyChecking", "no");
                LOGGER.log(Level.INFO, "Establishing connection ...");

                session.connect();

                if (!session.isConnected()) {
                    LOGGER.log(Level.FINE, "Connection failed, waiting " + sleepTimer + " ms");
                    Thread.sleep(sleepTimer);
                    sleepTimer += 50;
                } else {
                    LOGGER.log(Level.INFO, "Connected!");

                    int assignedPort = session.setPortForwardingL(
                            listeningPort, remoteHost, remotePort
                    );
                    LOGGER.log(Level.INFO, "Localhost connected on: " + assignedPort + " to " + remoteHost + " on " + host + ":" + remotePort);
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Linux remote connection exception: " + ex.toString(), ex);
            }
        } while (session == null || !session.isConnected());
    }

    private static void readEncryption() {
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream("encrypt.bin"))) {
            encrypt = (Encrypt) oi.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);

        }
    }

    public static void closeConnection(){
        if (session != null){
            session.disconnect();
        }
    }
}
