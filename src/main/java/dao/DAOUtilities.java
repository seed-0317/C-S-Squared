package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import model.Reimbursement;
import model.ReimbursementType;
import model.ReimbursementStatus;
import model.Users;



/**
 * Created by uzh051 on 4/23/17.
 */
public class DAOUtilities {


    public Connection getConnection() {


        Connection connection = null;
        connection = ConnectionFactory.createConnection();

        return connection;
    }


    /**
     * Returns a Users object when passed the user's ID.
     * @param userId
     * @return
     */
    public Users createUserObject(int userId){
        Connection connection = getConnection();
        UsersDAO userDAO = new UsersDAO(connection);
        Users user = null;
        try {
            user = userDAO.createUserObj(userId);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Returns a Users object when passed the user's username.
     * @param username
     * @return
     */
    public Users getUserByName(String username){
        Connection connection = getConnection();;
        UsersDAO dao = new UsersDAO(connection);
        Users result = null;
        try {
            result = dao.getUserInfoByUsername(username);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Returns a ReimbursementType object when passed the type ID.
     * @param typeId
     * @return
     */
    public ReimbursementType createTypeObject(int typeId){
        Connection connection = getConnection();
        ReimbursementTypeDAO typeDAO = new ReimbursementTypeDAO(connection);
        ReimbursementType type = null;
        try {
            type = typeDAO.createTypeObj(typeId);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    /**
     * Returns a ReimbursementStatus object when passed the status ID.
     * 1 is pending.
     * 2 is approved.
     * 3 is denied.
     * @param statusId
     * @return
     */
    public ReimbursementStatus createStatusObject(int statusId){
        Connection connection = getConnection();
        ReimbursementStatusDAO statusDAO = new ReimbursementStatusDAO(connection);
        ReimbursementStatus status = null;
        try {
            status = statusDAO.createStatusObj(statusId);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * Returns a Reimbursement object after having added the reimbursement in the DB when passed
     * the amount, description, user's ID, and type ID.
     * Only used by employees.
     * @param amount
     * @param description
     * @param authorId
     * @param typeId
     * @return
     */
    public Reimbursement addNewReimbursement(double amount, String description, int authorId, int typeId){
        Connection connection = getConnection();
        ReimbursmentDAO reimbDAO = new ReimbursmentDAO(connection);
        ReimbursementStatusDAO statusDAO = new ReimbursementStatusDAO(getConnection());
        Users author = null;
        ReimbursementType type = createTypeObject(typeId);
        ReimbursementStatus status = null;
        Reimbursement reimb = null;
        try {
            author = createUserObject(authorId);
            status = statusDAO.createStatusObj(1);
            reimb = new Reimbursement(reimbDAO.getNextId(), amount, getCurrentTimeStamp(),
                    (Timestamp)null, description, author, null, status, type);
            reimbDAO.insert(reimb);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimb;
    }

    /**
     * Updated an existing reimbursement when passed the new status, the username of the resolver, and
     * the reimbursement's ID.
     * @param status
     * @param username
     * @param reimbId
     */
    public void updateReimbursement(String status, String username, int reimbId){
        Connection connection = getConnection();
        Timestamp currentDate = getCurrentTimeStamp();
        ReimbursmentDAO dao = new ReimbursmentDAO(connection);
        try {
            dao.updateReimbursement(status, username, reimbId, currentDate);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Could not update reimbursement ID# " + reimbId + ".");
        }
    }

    /**
     * Returns a list of reimbursements when given the user's username.
     * Used only by employees.
     * @param username
     * @return
     */
    public List<Reimbursement> showAllReimbByUsername(String username){
        Connection connection = getConnection();
        ReimbursmentDAO dao = new ReimbursmentDAO(connection);
        List<Reimbursement> result = null;
        try {
            result = dao.selectAllByUsername(username);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Returns a list of reimbursements of the given status.
     * Used by managers.
     * @param status
     * @return
     */
    public List<Reimbursement> showAllByStatus(String status){
        Connection connection = getConnection();
        ReimbursmentDAO dao = new ReimbursmentDAO(connection);
        List<Reimbursement> result = null;
        try {
            result = dao.selectByStatus(status);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Could not show pending reimbursements.");
        }
        System.out.println(result);
        return result;
    }

    /**
     * Returns if the account was found given the username the user entered.
     * @param username
     * @return
     */
    public boolean accountFound(String username){
        Connection connection = getConnection();
        UsersDAO dao = new UsersDAO(connection);
        try {
            if(dao.usernameFound(username)){
                System.out.println(username + " found!");
                connection.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(username + " not found!");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Returns 'Employee' or 'Manager' given the username of the user.
     * @param username
     * @return
     */
    public String empOrManager(String username){
        Connection connection = getConnection();
        String result = null;
        UsersDAO dao = new UsersDAO(connection);
        try {
            if(dao.empOrManager(username) == 1){
                result = "Employee";
                connection.close();
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result = "Manager";
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Adds a new user to the DB. Made it for personal use but can be implemented with a sign up html page.
     * @param username
     * @param fName
     * @param lName
     * @param email
     * @param roleId
     */
    public void addUserToDB(String username, String fName, String lName, String email, int roleId){
        Connection connection = getConnection();
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.addUserToDB(username, fName, lName, email, roleId);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns a list of completed (approved or denied) reimbursements resolved by the given username.
     * For managers only.
     * @param resolverUsername
     * @return
     */
    public List<Reimbursement> showCompleted(String resolverUsername){
        Connection connection = getConnection();
        ReimbursmentDAO dao = new ReimbursmentDAO(connection);
        List<Reimbursement> results = null;
        try {
            results = dao.reimbCompleted(resolverUsername);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Returns the time stamp.
     * @return
     */

    public static java.sql.Timestamp getCurrentTimeStamp(){
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

}
