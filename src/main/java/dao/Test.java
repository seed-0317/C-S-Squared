package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tky247 on 4/18/17.
 */
public class Test {
    public static void main(String[] args) {

//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        ConnectionFactory getData = new ConnectionFactory();
        Connection connection = getData.createConnection();

        try {

            PreparedStatement stmt1 = connection.prepareStatement("select * from csssquared.ers_users");

            ResultSet rs = stmt1.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getInt("u_id") + "\t" + rs.getString("u_username"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
