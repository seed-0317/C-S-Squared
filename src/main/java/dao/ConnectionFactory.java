package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by uzh051 on 4/24/17.
 */
public class ConnectionFactory {

    private static final String CONNECTION_USERNAME = System.getenv("CONNECTIONUSERNAME");
    private static final String CONNECTION_PASSWORD = System.getenv("CONNECTIONPASSWORD");
    private static final String URL = System.getenv("CONNECTIONURL");

    public static Connection createConnection() {


        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
