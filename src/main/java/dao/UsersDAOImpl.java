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

        try (Connection connection = DAOUtilities.createConnection()){
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
            preparedstmt.setString(6,user.getRole());

            success = preparedstmt.executeUpdate();
        }catch (SQLException e){

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
                String sql =  "SELECT a.u_id, a.u_username, a.u_firstname, a.u_lastname, a.u_email, b.ur_role ";
                sql = sql + "   from csssquared.ers_users a, csssquared.ers_user_roles b";
                sql = sql +  " where a.u_username = ? AND a.ur_id = b.ur_id";
                preparedstmt = connection.prepareStatement(sql);

                preparedstmt.setString(1, username);
                ResultSet resultSet = preparedstmt.executeQuery();

                if (resultSet.next()) {
                    user = new User();
                    UserRoles roles = new UserRoles();

                    user.setId(resultSet.getInt(1));
                    user.setUserName(resultSet.getString(2));
                    user.setFirstName(resultSet.getString(3));
                    user.setLastName(resultSet.getString(4));
                    user.seteMail(resultSet.getString(5));
                    user.setRole(resultSet.getString(6));

                    //System.out.println(roles.getUrRole());
                }

            } catch (SQLException e) {

                e.printStackTrace();
        }


        return user;
    }


    public static void updateUser(String userName, String firstName, String lastName, String eMail) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedstmt = null;
        User user = null;
        connection = DAOUtilities.createConnection();

        String sql = "UPDATE csssquared.ers_users Set u_firstname = ?, u_lastname = ?, u_email = ? where u_username = ?";
        preparedstmt = connection.prepareStatement(sql);
        preparedstmt.setString(1, firstName);
        preparedstmt.setString(2, lastName);
        preparedstmt.setString(3, eMail);
        preparedstmt.setString(4,userName);
        ResultSet resultSet = preparedstmt.executeQuery();
    }

}
