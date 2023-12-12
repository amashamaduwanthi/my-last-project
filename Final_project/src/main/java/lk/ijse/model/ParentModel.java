package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ParentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParentModel {
    public boolean SaveStudent(String id, String name, int contactNo, String designation) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Parent VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        pstm.setString(2,name);
        pstm.setInt(3, contactNo);
        pstm.setString(4, designation);

        return pstm.executeUpdate()>0;

    }

    public ParentDto searchParent(String parentId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Parent WHERE parentId=? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,parentId);
        ParentDto dto=null;
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            dto=new ParentDto(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4));
        }
         return dto;
    }
}
