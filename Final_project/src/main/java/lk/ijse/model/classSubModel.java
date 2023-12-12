package lk.ijse.model;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class classSubModel {
    public boolean addClass(String classId, String id,  String subName, String lectName) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO studentClassDetails VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, classId);
        pstm.setString(2, id);
        pstm.setString(3, subName);
        pstm.setString(4, lectName);
        return pstm.executeUpdate() > 0;
    }
}
