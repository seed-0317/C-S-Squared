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

    private static final String URL = "jdbc:postgresql://seed-development.crv1otzbekk9.us-east-1.rds.amazonaws.com:5432/SEED2017";
    private static final String USERNAME = "seed_superuser";
    private static final String PASSWORD = "Seed2017";



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

