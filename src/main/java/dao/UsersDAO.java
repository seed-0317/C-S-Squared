package dao;

import model.Users;
import model.UserRoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by uzh051 on 4/23/17.
 */
class UsersDAO {
    private Connection connection;

    public UsersDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    /**
     * Returns a Users object of the passed username.
     * @param givenUsername
     * @return
     * @throws SQLException
     */
    public Users getUserInfoByUsername(String givenUsername) throws SQLException{

        String sql = "SELECT a.u_id, a.u_username, a.u_firstname, a.u_lastname, a.u_email ";
        sql = sql + "  ,b.ur_id, b.ur_role from csssquared.ers_users a join csssquared.ers_user_roles b on b.ur_id = a.ur_id";
        sql = sql +  " where a.u_username = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        System.out.println("Starting In connection get User Info by namae");
        stmt.setString(1, givenUsername);
        ResultSet rs = stmt.executeQuery();
        Users obj = null;
        while(rs.next()){
            int id = rs.getInt("u_id");
            String username = rs.getString("u_username");
            String firstName = rs.getString("u_firstname");
            String lastName = rs.getString("u_lastname");
            String email = rs.getString("u_email");

            int roleId = rs.getInt("ur_id");
            UserRoles role = null;
            if(roleId != 0){
                UserRolesDAO rolesDAO = new UserRolesDAO(connection);
                role = rolesDAO.createRoleObj(roleId);
            }


            obj = new Users(id,username,firstName,lastName,email,role);


        }
        System.out.println("Users object: " +obj);
        return obj;
    }

    /**
     * Username was found. User exist!
     * @param username
     * @return
     * @throws SQLException
     */

    public boolean usernameFound(String username) throws SQLException{

        String sql = "SELECT u_username FROM csssquared.ers_users " +
                "WHERE u_username = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        if(stmt.executeUpdate() == 1) return true;
        return false;
    }


    /**
     * Returns 1 for employee or 2 for manager after determining if the user is an employee or manager.
     * @param username
     * @return
     * @throws SQLException
     */
    public int empOrManager(String username) throws SQLException{

        String sql = "SELECT ur_id FROM csssquared.ers_users " +
                "WHERE u_username = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        if(rs.getInt("ur_id") == 1) return 1;
        return 2;
    }

    /**
     * Creates and returns a Users object when passed the user ID.
     * @param userId
     * @return
     * @throws SQLException
     */
    public Users createUserObj(int userId) throws SQLException{

        String sql = "SELECT u_username, u_firstname, u_lastname, u_email ,ur_id FROM csssquared.ers_users " +
                "WHERE u_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String username = rs.getString("u_username");
        String firstName = rs.getString("u_firstname");
        String lastName = rs.getString("u_lastname");
        String email = rs.getString("u_email");

        int roleId = rs.getInt("ur_id");
        UserRolesDAO roleDAO = new UserRolesDAO(connection);
        UserRoles role = roleDAO.createRoleObj(roleId);

        Users user = new Users(userId, username,firstName,lastName,email,role);
        return user;
    }


    /**
     * Retrieves the int of the next ID available in the ERS_USERS table.
     * @return
     * @throws SQLException
     */
    public int getNextId() throws SQLException{

        String sqlForMaxId = "SELECT MAX(ur_id) FROM csssquared.ers_users";
        PreparedStatement maxId = connection.prepareStatement(sqlForMaxId);
        ResultSet rs = maxId.executeQuery();
        rs.next();
        int theId = rs.getInt("MAX(ur_id)") + 1;
        return theId;
    }

    /**
     * Adds a user to the DB.
     * @param username
     * @param fName
     * @param lName
     * @param email
     * @param roleId
     * @throws SQLException
     */
    public void addUserToDB(String username, String fName, String lName, String email, int roleId) throws SQLException{

        String sql = "INSERT INTO csssquared.ers_users (u_id, u_username, u_firstname, u_lastname, u_email, ur_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, getNextId());
        stmt.setString(2, username);
        stmt.setString(3, fName);
        stmt.setString(4, lName);
        stmt.setString(5, email);
        stmt.setInt(6, roleId);
        stmt.executeUpdate();
        connection.commit();
        System.out.println("commited");
    }



}


