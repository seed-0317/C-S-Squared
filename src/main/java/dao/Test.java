package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.createConnection();
        try {
            PreparedStatement stmt1 = connection.prepareStatement("Insert INTO csssquared.ers_reimbursements " +
                    "(r_id,r_amount,u_id_author,rs_id, rt_id) VALUES (2, 400, 1, 1, 1)");
            ResultSet rs = stmt1.executeQuery();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

