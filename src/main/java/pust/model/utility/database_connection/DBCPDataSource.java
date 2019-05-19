package pust.model.utility.database_connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl("jdbc:mysql://localhost:4321/pustgis");
        ds.setUsername("root");
        ds.setPassword("6978f28c972457220d4e72398bb9e000");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("Executing SQL method!");
        return ds.getConnection();
    }

    private DBCPDataSource() {

    }
}
