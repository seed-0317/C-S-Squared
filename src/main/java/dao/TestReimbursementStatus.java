package dao;

import model.Reimbursement;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by uzh051 on 4/24/17.
 */
public class TestReimbursementStatus {

    public int getNextId() throws SQLException {
        Connection connection = DAOUtilities.createConnection();
        String sqlForMaxId = "SELECT MAX(r_id) FROM csssquared.ers_reimbursements";
        PreparedStatement maxId = connection.prepareStatement(sqlForMaxId);
        ResultSet rs = maxId.executeQuery();
        rs.next();
        int theId = rs.getInt("MAX(r_id)") + 1;
        return theId;
    }

    /**
     * Selects all the reimbursements of a user.
     *
     * @param username
     * @throws SQLException
     */

    public List<Reimbursement> selectAllByUsername(String username) throws SQLException {
        Connection connection;
        List<Reimbursement> results = null;
        PreparedStatement  stmt;

        try {
            connection = DAOUtilities.createConnection();
            results = new ArrayList<>();

            String sql = "SELECT * FROM csssquared.ers_reimbursements " +
                    "WHERE u_id_author in " +
                    "(SELECT u_id FROM csssquared.ers_users WHERE u_username = ?) ";

            System.out.println("SQL: " + sql);
            System.out.println("username: " + username);

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            mapRows(rs, results);
            if (results.size() == 0) {
                System.out.println("Could not find the user " + username + ".");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;

    }
    /**
     * Assists us in returning all the rows in a query.
     * @param rs
     * @param results
     * @throws SQLException
     */

    private void mapRows(ResultSet rs, List<Reimbursement> results) throws SQLException {
        Connection connection = DAOUtilities.createConnection();
        while(rs.next()){
            System.out.println("Inside map rows while");
            int id = rs.getInt("r_id");
            float amount = rs.getFloat("r_amount");
            String description = rs.getString("r_description");
            String submitted = rs.getString("r_submitted");
            String resolved = rs.getString("r_resolved");
            int author = rs.getInt("u_id_author");
            int resolver = rs.getInt("u_id_resolver");
            int typeId = rs.getInt("rt_id");
            int statusID = rs.getInt("rs_id");

            Reimbursement obj = new Reimbursement(id,amount,description,submitted,resolved,author,resolver,typeId,statusID);
//            Reimbursement obj = new Reimbursement(id,amount,description,null,submitted,resolved,author,resolver,typeId,statusID);

            results.add(obj);
            System.out.println("printing obj" + obj);
        }
    }

}
