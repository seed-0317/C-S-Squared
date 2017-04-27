package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddReimbursementsDAO {

    public static void addReimburse(float amount, int author, int statusID, int TypeId, String descr,
                                    String formattedDate) {
        Connection connection = DAOUtilities.createConnection();
        try (PreparedStatement stmt1 = connection.prepareStatement("Insert INTO csssquared.ers_reimbursements " +
                "(r_amount,u_id_author,rs_id, rt_id, r_description, r_submitted) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt1.setFloat(1, amount);
            stmt1.setInt(2, author);
            stmt1.setInt(3, statusID);
            stmt1.setInt(4, TypeId);
            stmt1.setString(5, descr);
            stmt1.setString(6, formattedDate);
            ResultSet rs = stmt1.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static String addTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static void StatusReimburse(int statusId, int resolver, String timeStamp, int id) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedstmt = null;
        connection = DAOUtilities.createConnection();

        String sql = "UPDATE csssquared.ers_reimbursements Set rs_id = ?, u_id_resolver = ?, r_resolved = ? where r_id = ?";
        preparedstmt = connection.prepareStatement(sql);
        preparedstmt.setInt(1, statusId);
        preparedstmt.setInt(2, resolver);
        preparedstmt.setString(3, timeStamp);
        preparedstmt.setInt(4, id);
        ResultSet resultSet = preparedstmt.executeQuery();

        System.out.println("StatusReimburse()");
    }
}


