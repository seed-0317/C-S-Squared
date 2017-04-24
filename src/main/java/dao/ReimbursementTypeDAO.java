package dao;

import model.ReimbursementType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by uzh051 on 4/23/17.
 */
public class ReimbursementTypeDAO {
    private Connection connection;

    public ReimbursementTypeDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    /**
     * Helper method used to return the rows in a list.
     * @param rs
     * @throws SQLException
     */

    private void mapRows(ResultSet rs) throws SQLException {
        List<ReimbursementType> results = new ArrayList<ReimbursementType>();
        while(rs.next()){
            int typeId = rs.getInt("rt_id");
            String type = rs.getString("rt_type");
            ReimbursementType obj = new ReimbursementType(typeId,
                    type);
            results.add(obj);
        }
        for(int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }
    }

    /**
     * Selects all the reimbursements depending on the type.
     * We will define the typeId in the DAOUtilities in order to select
     * lodging, travel, food, other reimbursements.
     * @param typeId
     * @throws SQLException
     */

    public void selectByType(int typeId) throws SQLException{

        String sql = "SELECT * FROM csssquared.ers_reimbursements" +
                "WHERE rt_id = ?";
       PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, typeId);
        ResultSet rs = stmt.executeQuery();
        mapRows(rs);
    }

    /**
     * Returns a type Object when given the type ID.
     * Used in order to put into Reimbursement bean.
     * @param typeId
     * @return
     * @throws SQLException
     */

    public ReimbursementType createTypeObj(int typeId) throws SQLException{

        String sql = "SELECT REIMB_TYPE FROM csssquared.ers_reimbusrement_type " +
                "WHERE rt_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, typeId);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String type = rs.getString("rt_type");
        ReimbursementType typeObject = new ReimbursementType(typeId, type);
        return typeObject;
    }
}
