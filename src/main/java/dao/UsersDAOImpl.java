package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uzh051 on 4/19/17.
 */
public class UsersDAOImpl implements UsersDAO {

    @Override
    public List<User> getAllUser() {
        List<User> users= new ArrayList<>();
        Connection connection =null;
        Statement stmt = null;

        try {
            connection = DAOUtilities.getConnection();

            stmt = connection.createStatement();

            String sql = "SELECT * FROM csssquared.ers_users";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                User a = new User();

                a.setId(rs.getInt("id"));
                a.setUserName(rs.getString("userName"));
                a.setFirstName(rs.getString("firstName"));
                a.setLastName(rs.getString("lastName"));
                a.seteMail(rs.getString("eMail"));
                a.setrID(rs.getInt("rId"));

                users.add(a);

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

        return users;
    }

    @Override
    public void saveUser(User user) throws Exception {

        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {

            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO csssquared.ers_users VALUES(?,?,?,?,?,?)";

            //Setup prepared statements
            stmt = connection.prepareStatement(sql);

            // Add parameters from user into PreparedStatement
            stmt.setInt(1, user.getId());
            stmt.setString(2,user.getUserName());
            stmt.setString(3,user.getFirstName());
            stmt.setString(4,user.getLastName());
            stmt.setString(5,user.geteMail());
            stmt.setInt(6,user.getrID());

            success = stmt.executeUpdate();
        } catch (SQLException e){

            e.printStackTrace();

        } finally {

            try {

                if (stmt !=null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException  e){
                e.printStackTrace();

            }


        }

        if (success == 0) {

            throw new Exception("Insert Use Failed" + user);

        }


    }
}
