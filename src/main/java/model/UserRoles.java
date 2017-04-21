package model;

import java.util.Objects;

/**
 * Created by uzh051 on 4/20/17.
 */
public class UserRoles {

    private int urId;
    private String urRole;

    public UserRoles(){

    }

    public UserRoles(int urId, String urRole) {
        this.urId = urId;
        this.urRole = urRole;
    }

    public int getUrId() {
        return urId;
    }

    public String getUrRole() {
        return urRole;
    }

    public void setUrId(int urId) {
        this.urId = urId;
    }

    public void setUrRole(String urRole) {
        this.urRole = urRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoles)) return false;

        UserRoles userRoles = (UserRoles) o;

        if (getUrId() != userRoles.getUrId()) return false;
        return getUrRole() != null ? getUrRole().equals(userRoles.getUrRole()) : userRoles.getUrRole() == null;
    }

    @Override
    public int hashCode() {
        int result = getUrId();
        result = 31 * result + (getUrRole() != null ? getUrRole().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "urId=" + urId +
                ", urRole='" + urRole + '\'' +
                '}';
    }
}
