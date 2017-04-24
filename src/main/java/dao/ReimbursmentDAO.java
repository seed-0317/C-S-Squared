package dao;

import model.Reimbursement;
import model.ReimbursementStatus;
import model.ReimbursementType;
import model.Users;
import model.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by uzh051 on 4/23/17.
 */
public class ReimbursmentDAO {

    private Connection connection;

    public ReimbursmentDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    /**
     * Retrieves the ID of the reimbursement to be inserted.
     * @return the ID
     * @throws SQLException
     */

    public int getNextId() throws SQLException{

        String sqlForMaxId = "SELECT MAX(r_id) FROM csssquared.ers_reimbursements";
        PreparedStatement maxId = connection.prepareStatement(sqlForMaxId);
        ResultSet rs = maxId.executeQuery();
        rs.next();
        int theId = rs.getInt("MAX(r_id)") + 1;
        return theId;
    }

    /**
     * Inserts a new reimbursement into our database.
     * No resolved date, no resolver, and the status is pending.
     * Called by createReimbObject in DAOUtilities class.
     * @param reimb
     * @throws SQLException
     */

    public void insert(Reimbursement reimb) throws SQLException{

        String sql = "INSERT INTO csssquared.ers_reimbursements" +
                "(r_id , r_amount,r_submitted ,r_description,u_id_author ,rs_id ,rt_id ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, reimb.getId());
        stmt.setDouble(2, reimb.getAmount());
        stmt.setTimestamp(3, reimb.getSubmitted());
        stmt.setString(4, reimb.getDescription());
        stmt.setInt(5, reimb.getAuthor().getUsersId());
        stmt.setInt(6, reimb.getStatus().getStatusId());
        stmt.setInt(7, reimb.getType().getTypeId());
        connection.commit();
        stmt.executeUpdate();
        System.out.println("Successfully inserted row!");
        System.out.println(reimb.toString());
    }

    /**
     * Updates an reimbursement.
     * Used by employees.
     * @param status
     * @param username
     * @param reimbId
     * @param currentDate
     * @throws SQLException
     */

    public void updateReimbursement
    (String status, String username, int reimbId, Timestamp currentDate) throws SQLException{

        String sql =
                "UPDATE csssquared.ers_reimbursements " +
                        "SET r_resolved  = ?, " +
                        "rs_id  = " +
                        "(SELECT rs_id  FROM csssquared.ers_reimbursement_status WHERE rs_status = ?), " +
                        "u_id_resolver = " +
                        "(SELECT u_id FROM csssquared.ers_users WHERE u_username = ?) " +
                        "WHERE rs_id  = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setTimestamp(1, currentDate);
        stmt.setString(2, status);
        stmt.setString(3, username);
        stmt.setInt(4, reimbId);
        stmt.executeUpdate();

        System.out.println("Successfully updated reimbursement#" + reimbId + " as " + status +
                ". Resolved by " + username + " on " + currentDate.toString() + ".");
    }

    /**
     * Selects all the reimbursements of a user.
     * @param username
     * @throws SQLException
     */

    public List<Reimbursement> selectAllByUsername(String username) throws SQLException {
        List<Reimbursement> results = new ArrayList<Reimbursement>();

        String sql = "SELECT * FROM csssquared.ers_reimbursements" +
                "WHERE u_id_author  = " +
                "(SELECT u_id FROM csssquared.ers_users WHERE u_username = ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        mapRows(rs, results);
        if(results.size() == 0) {
            System.out.println("Could not find the user " + username + ".");
            return null;
        }


        Collections.sort(results, new Comparator<Reimbursement>() {
            @Override
            public int compare(Reimbursement r1, Reimbursement r2) {
                return r2.getSubmitted().compareTo(r1.getSubmitted());
            }
        });


        return results;
    }

    /**
     * Selects reimbursements by the status given.
     * Used for managers.
     * @param status
     * @throws SQLException
     */

    public List<Reimbursement> selectByStatus(String status) throws SQLException{
        List<Reimbursement> results = new ArrayList<Reimbursement>();

        String sql = "SELECT * FROM csssquared.ers_reimbursements " +
                "WHERE rs_id  = " +
                "(SELECT rs_id  FROM csssquared.ers_reimbursement_status WHERE rs_status = ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, status);
        ResultSet rs = stmt.executeQuery();
        mapRows(rs, results);
        //System.out.println("rows mapped");
        return results;
    }

    /**
     * Assists us in returning all the rows in a query.
     * @param rs
     * @param results
     * @throws SQLException
     */

    private void mapRows(ResultSet rs, List<Reimbursement> results) throws SQLException {
        Timestamp resolved = null;
        while(rs.next()){
            int id = rs.getInt("r_id ");
            double amount = rs.getDouble("r_amount");
            Timestamp submitted = rs.getTimestamp("r_submitted");
            resolved = rs.getTimestamp("r_resolved");
            String description = rs.getString("r_description");

            int authorId = rs.getInt("u_id_author");
            UsersDAO authorDAO = new UsersDAO(connection);
            Users author = authorDAO.createUserObj(authorId);

            int resolverId = rs.getInt("u_id_resolver");
            Users resolver = null;
            if(resolverId != 0){
                UsersDAO resolverDAO = new UsersDAO(connection);
                resolver = resolverDAO.createUserObj(resolverId);
            }

            int statusId = rs.getInt("rs_id");
            ReimbursementStatusDAO statusDAO = new ReimbursementStatusDAO(connection);
            ReimbursementStatus status = statusDAO.createStatusObj(statusId);

            int typeId = rs.getInt("rt_id");
            ReimbursementTypeDAO typeDAO = new ReimbursementTypeDAO(connection);
            ReimbursementType type = typeDAO.createTypeObj(typeId);

            Reimbursement obj = new Reimbursement(id,
                    amount,
                    submitted,
                    resolved,
                    description,
                    author,
                    resolver,
                    status,
                    type);
            results.add(obj);
        }
    }

    /**
     * Returns a list of the completed (not pending) reimbursements resolved by the given username.
     * @param resolverUsername
     * @return
     * @throws SQLException
     */
    public List<Reimbursement> reimbCompleted(String resolverUsername) throws SQLException{
        List<Reimbursement> results = new ArrayList<Reimbursement>();

        String sql = "SELECT * FROM csssquared.ers_reimbursements" +
                "WHERE u_id_resolver = " +
                "(SELECT u_id FROM csssquared.ers_users WHERE u_username = ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, resolverUsername);
        ResultSet rs = stmt.executeQuery();
        mapRows(rs, results);
        if(results.size() == 0) {
            System.out.println("Could not find the user " + resolverUsername + ".");
            return null;
        }

        Collections.sort(results, new Comparator<Reimbursement>() {
            @Override
            public int compare(Reimbursement r1, Reimbursement r2) {
                return r2.getResolved().compareTo(r1.getResolved());
            }
        });

        return results;
    }
}
