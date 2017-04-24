package model;

import java.util.Objects;

/**
 * Created by uzh051 on 4/20/17.
 */
public class UserRoles {

    private int userRoleId;
    private String userRole;

    public UserRoles(){
        super();
    }

    public UserRoles(int userRoleId, String userRole) {
        super();
        this.userRoleId = userRoleId;
        this.userRole = userRole;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "userRoleId=" + userRoleId +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
