package lk.ijse.model;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentModel {
    public static String searchTotalStudent() throws SQLException {
        String count="0";
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM Student;";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                count=resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
