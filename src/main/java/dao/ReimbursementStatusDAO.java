package dao;

import model.ReimbursementStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by uzh051 on 4/23/17.
 */
public class ReimbursementStatusDAO {


    public ReimbursementStatusDAO() {
        super();

    }

    /**
     * Returns the status as a String given the status ID.
     * @param statusId
     * @return
     * @throws SQLException
     */

    public String statusNameById(int statusId) throws SQLException{
        Connection connection = DAOUtilities.createConnection();

        String result = null;
        String sql = "SELECT rs_status FROM csssquared.ers_reimbursement_status " +
                "WHERE rs_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, statusId);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        if(rs.getString("rs_status").equals("Pending")){
            result = "Pending";
            return result;
        } else if(rs.getString("rs_status").equals("Approved")){
            result = "Approved";
            return result;
        }
        result = "Rejected";
        return result;
    }

    /**
     * Returns a status Object given a status ID.
     * Used in order to put a status Object into Reimbursement bean.
     * @param statusId
     * @return
     * @throws SQLException
     */

    public ReimbursementStatus createStatusObj(int statusId) throws SQLException{

        Connection connection = DAOUtilities.createConnection();

        String sql = "SELECT rs_status FROM csssquared.ers_reimbursement_status" +
                "WHERE rs_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, statusId);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String status = rs.getString("rs_status");
        ReimbursementStatus statusObj = new ReimbursementStatus(statusId, status);
        return statusObj;
    }
}