package DAO;


import model.Reimbursement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by uzh051 on 4/18/17.
 */
public class DAOUtilities {


    private static final String CONNECTION_USERNAME = System.getenv("CONNECTIONUSERNAME");
    private static final String CONNECTION_PASSWORD = System.getenv("CONNECTIONPASSWORD");
    private static final String URL = System.getenv("CONNECTIONURL");

    private static ReimbursmentsDAO reimbursmentsDAO;
    private static Connection connection;

    public static synchronized ReimbursmentsDAO getReimbursmentsDAO() {

        if (reimbursmentsDAO == null) {
            reimbursmentsDAO = new ReimbursmentsDAO();
        }
        return reimbursmentsDAO;
    }

    static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Could not register driver!");
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
        }

        //If connection was closed then retrieve a new connection
        if (connection.isClosed()){
            System.out.println("getting new connection...");
            connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
        }
        return connection;
    }
}
