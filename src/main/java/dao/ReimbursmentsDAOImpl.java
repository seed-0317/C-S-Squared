package dao;

import model.Reimbursement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nof191 on 4/26/17.
 */
public class ReimbursmentsDAOImpl implements ReimbursmentsDAO{
    @Override
    public List<Reimbursement> getAllReimbursments() {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection connection = DAOUtilities.createConnection()){
            PreparedStatement stmt1 = connection.prepareStatement("select r_id, r_amount, r_description, r_submitted, r_resolved, u_id_author, u_id_resolver, rs_id, rt_id from csssquared.ers_reimbursements");
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                Reimbursement newReimbursement = new Reimbursement();
                newReimbursement.setId(rs.getInt("r_id"));
                newReimbursement.setAmount(rs.getFloat("r_amount"));
                newReimbursement.setDescription(rs.getString("r_description"));
                newReimbursement.setSubmitted(rs.getString("r_sumbitted"));
                newReimbursement.setResolved(rs.getString("r_resolved"));
                newReimbursement.setAuthor(rs.getInt("u_id_author"));
                newReimbursement.setResolver(rs.getInt("u_id_resolver"));
                newReimbursement.setStatusID(rs.getInt("rs_id"));
                newReimbursement.setTypeId(rs.getInt("rt_id"));
                reimbursements.add(newReimbursement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
