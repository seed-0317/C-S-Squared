package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.createConnection();
        try {
            PreparedStatement stmt1 = connection.prepareStatement("UPDATE csssquared.ers_reimbursements SET r_id = 3, r_amount=400, u_id_author = 1,rs_id = 1, rt_id = 1 ");
            ResultSet rs = stmt1.executeQuery();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

