package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    /* public static void main(String[] args) {
        Connection connection = ConnectionFactory.createConnection();
        try {
            PreparedStatement stmt1 = connection.prepareStatement("Insert INTO csssquared.ers_reimbursements " +
                    "(r_id,r_amount,u_id_author,rs_id, rt_id) VALUES (2, 400, 1, 1, 1)");
            ResultSet rs = stmt1.executeQuery();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int r_id = 3;
        String amount = "5000";
        String u_id = "1";
        int rs_id = 1;
        String rt_id = "1"; */

    public static void addReimburse(int Id, float amount, int author, int statusID, int TypeId) {
        Connection connection = ConnectionFactory.createConnection();
        try (PreparedStatement stmt1 = connection.prepareStatement("Insert INTO csssquared.ers_reimbursements " +
                "(r_id,r_amount,u_id_author,rs_id, rt_id) VALUES (?, ?, ?, ?, ?)")){
        stmt1.setInt(1,Id);
        stmt1.setFloat(2,amount);
        stmt1.setInt(3,author);
        stmt1.setInt(4,statusID);
        stmt1.setInt(5,TypeId);
        ResultSet rs = stmt1.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}

