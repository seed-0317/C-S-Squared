package dao;

import model.Reimbursement;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by nof191 on 4/19/17.
 */
public class ReimbursmentsDAOImpl implements ReimbursmentsDAO{


    @Override
    public List<Reimbursement> getAllReimbursments() {

        List<Reimbursement> reimbursements = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ReimbursmentsDAOImpl dao = new ReimbursmentsDAOImpl();

        try{
            connection = DAOUtilities.getConnection();

            String sql = "SELECT * FROM ERS_REIMBURSEMENTS";

            stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reimbursement r = new Reimbursement();

                r.setId(rs.getInt("id"));
                r.setAmount(rs.getFloat("amount"));
                r.setDescription(rs.getString("description"));
                r.setReceipt(rs.getBlob("receipt"));
                r.setSubmitted(rs.getTimestamp("submitted"));
                r.setResolved(rs.getTimestamp("resolved"));
                r.setAuthor(rs.getInt("author"));
                r.setResolver(rs.getInt("resolver"));
                r.setStatusID(rs.getInt("statusid"));
                r.setTypeId(rs.getInt("typeid"));


                reimbursements.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reimbursements;

        }


    @Override
    public void createReimbursment(Reimbursement newReimbursment) throws Exception {

    }

    @Override
    public void saveReimbursment(Reimbursement saveReimbursment) throws Exception {

    }
}
