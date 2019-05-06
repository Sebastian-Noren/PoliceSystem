package pust.model.utility;

import java.io.Serializable;

public class Encrypt implements Serializable {

    private String user;
    private String password;
    private String host;
    private int port;
    private int listeningPort;
    private String remoteHost;
    private int remotePort;

    public Encrypt() {
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
        this.listeningPort = listeningPort;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getListeningPort() {
        return listeningPort;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public int getRemotePort() {
        return remotePort;
    }
}
