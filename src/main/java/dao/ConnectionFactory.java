package dao;

/**
 * Created by tky247 on 4/18/17.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * Useful links:
 *   https://www.mkyong.com/jdbc/how-do-connect-to-postgresql-with-jdbc-driver-java/
 */
public class ConnectionFactory {

        private static final String URL = System.getenv("CONNECTIONURL");
        private static final String USERNAME = System.getenv("CONNECTIONUSERNAME");
        private static final String PASSWORD = System.getenv("CONNECTIONPASSWORD");



    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}

