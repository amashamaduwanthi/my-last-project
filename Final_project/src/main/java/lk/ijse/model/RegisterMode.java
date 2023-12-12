package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.RegistrationDto;
import lk.ijse.dto.studentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterMode {
    public boolean saveRegistration(String regId, String name, String email, String date, String parentId,String userName) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Registration VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, regId);
        pstm.setString(2, name);
        pstm.setString(3, email);
        pstm.setString(4, date);
        pstm.setString(5, parentId);
        pstm.setString(6,userName);
        return pstm.executeUpdate() > 0;
    }
}
