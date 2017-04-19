package dao;

import model.Reimbursement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nof191 on 4/19/17.
 */
public class ReimbursmentsDAOImpl implements ReimbursmentsDAO {

    @Override
    public List<Reimbursement> getAllReimbursments() {
        List<Reimbursement> reimbursements= new ArrayList<>();
        Connection connection =null;
        Statement stmt = null;

        try {
            connection = DAOUtilities.getConnection();

            stmt = connection.createStatement();

            String sql = "SELECT * FROM csssquared.ers_reimbursements";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Reimbursement a = new Reimbursement();

                a.setId(rs.getInt("id"));
                a.setAmount(rs.getFloat("amount"));
                a.setDescription(rs.getString("description"));
                a.setReceipt(rs.getBlob("receipt"));
                a.setSubmitted(rs.getString("submitted"));
                a.setResolved(rs.getTimestamp("resolved"));
                a.setAuthor((rs.getInt("author")));
                a.setResolver(rs.getInt("resolver"));
                a.setStatusID(rs.getInt("statusId"));
                a.setStatusID(rs.getInt("tyepId"));


                reimbursements.add(a);

            }

        }
        catch (SQLException e){

            e.printStackTrace();
        } finally {

            try {
                if (stmt!=null){
                    stmt.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

        }

        return reimbursements;
    }

    @Override
    public void createReimbursment(Reimbursement newReimbursment) throws Exception {
        Connection connection =null;
        PreparedStatement stmt = null;
        int success = 0;
        try {
            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO ERS_REIMBURSEMENTS VALUES (?,?,?,?,?,?,?,?,?,?)";

            // Setup PreparedStatement
            stmt = connection.prepareStatement(sql);

            // Add parameters from reimbursment into PreparedStatement
            stmt.setInt(1, newReimbursment.getId();
            stmt.setString(2, newReimbursment.getAmount();
            stmt.setString(3, newReimbursment.getDescription();
            stmt.setBlob(4, newReimbursment.getReceipt();
            stmt.setString(5, newReimbursment.getSubmitted();
            stmt.setString(6, newReimbursment.getResolved();
            stmt.setInt(7, newReimbursment.getAuthor());
            stmt.setInt(8, newReimbursment.getResolver());
            stmt.setInt(9, newReimbursment.getStatusID());
            stmt.setInt(10, newReimbursment.getTypeId());

            success = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            // then update didn't occur, throw an exception
            throw new Exception("Insert feeding schedule failed: " + newReimbursment);
        }

    }

    @Override
    public void saveReimbursment(Reimbursement saveReimbursment) throws Exception {

    }
}
