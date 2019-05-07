package pust.model.utility;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.*;

public class LinuxRemoteConnection {

    private static Encrypt encrypt;

    public static void remoteConnect() {
        readEncryption();
        String user = encrypt.getUser();
        String password = encrypt.getPassword();
        String host = encrypt.getHost();
        int port = encrypt.getPort();

        try {
            JSch jSch = new JSch();
            Session session = jSch.getSession(user, host, port);
            int listeningPort = encrypt.getListeningPort();
            String remoteHost = encrypt.getRemoteHost();
            int remotePort = encrypt.getRemotePort();

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

    private static void readEncryption() {
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream("encrypt.bin"))) {
            encrypt = (Encrypt) oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
