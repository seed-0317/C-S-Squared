package dao;

import model.UserRoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by uzh051 on 4/23/17.
 */
class UserRolesDAO {

    private Connection connection;


    public UserRolesDAO(Connection connection) {
        super();
        this.connection = connection;
    }


    /**
     * Returns a UserRoles object when passed the role ID.
     * @param roleId
     * @return
     * @throws SQLException
     */
    public UserRoles createRoleObj(int roleId) throws SQLException {
        System.out.println("Role id: " + roleId);
        String sql = "SELECT ur_role FROM csssquared.ers_user_roles " +
                                    "WHERE ur_id = ?";
        System.out.println("SQL STatement: " + sql);
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, roleId);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String role = rs.getString("ur_role");
        UserRoles roleObj = new UserRoles(roleId, role);
        System.out.println(roleObj);
        return roleObj;
    }
}
