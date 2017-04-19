package dao;

import model.Reimbursement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    }

    @Override
    public void saveReimbursment(Reimbursement saveReimbursment) throws Exception {

    }
}
