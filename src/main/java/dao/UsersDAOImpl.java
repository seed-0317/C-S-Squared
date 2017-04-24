package dao;

import model.User;
import model.UserRoles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uzh051 on 4/19/17.
 */
public class UsersDAOImpl implements UsersDAO {

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DAOUtilities.createConnection();){
            PreparedStatement stmt1 = connection.prepareStatement("select * from csssquared.ers_users");
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                User newUser = new User();
                newUser.setId(rs.getInt("u_id"));
                newUser.setUserName(rs.getString("u_username"));
                newUser.setFirstName(rs.getString("u_firstname"));
                newUser.setLastName(rs.getString("u_lastname"));
                newUser.seteMail(rs.getString("u_email"));
                newUser.setId(rs.getInt("ur_id"));
                users.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public void saveUser(User user) throws Exception {

        Connection connection = null;
        PreparedStatement preparedstmt = null;
        int success = 0;

        try {

            connection = DAOUtilities.createConnection();
            String sql =  "UPDATE erawesome.ers_users set u_username = ?, u_firstname = ?, u_lastname = ?, ";
                   sql = sql + "u_email = ?, ur_id = ? WHERE u_id = ?";

            //Setup prepared statements
            preparedstmt = connection.prepareStatement(sql);

            // Add parameters from user into PreparedStatement
            preparedstmt.setInt(1, user.getId());
            preparedstmt.setString(2,user.getUserName());
            preparedstmt.setString(3,user.getFirstName());
            preparedstmt.setString(4,user.getLastName());
            preparedstmt.setString(5,user.geteMail());
            preparedstmt.setInt(6,user.getRole());

            success = preparedstmt.executeUpdate();
        } catch (SQLException e){

            e.printStackTrace();

        } finally {

            try {

                if (preparedstmt !=null)
                    preparedstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException  e){
                e.printStackTrace();

            }


        }

        if (success == 0) {

            throw new Exception("Insert User Failed" + user);

        }


    }

    @Override
    public User getUser(String username) {

        Connection connection = null;
        PreparedStatement preparedstmt = null;
        User user = null;

        try{
                connection = DAOUtilities.createConnection();
                String sql =  "SELECT u_id, u_username, u_firstname, u_lastname, u_email ";
                sql = sql + "   from csssquared.ers_users";
                sql = sql +  " where u_username = ?";
                preparedstmt = connection.prepareStatement(sql);

                preparedstmt.setString(1, username);
                ResultSet resultSet = preparedstmt.executeQuery();

                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("u_id"));
                    user.setUserName(resultSet.getString("u_username"));
                    user.setFirstName(resultSet.getString("u_firstname"));
                    user.setLastName(resultSet.getString("u_lastname"));
                    user.seteMail(resultSet.getString("u_email"));

                    UserRoles roles = new UserRoles();
                    roles.setUrId(resultSet.getInt("ur_id"));
                    roles.setUrRole(resultSet.getString("ur_role"));
 //                   user.setRole(roles);
                }

            } catch (SQLException e) {

                e.printStackTrace();
        }


        return user;
    }
}
