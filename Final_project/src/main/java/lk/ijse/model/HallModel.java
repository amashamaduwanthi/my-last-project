package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.HallDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallModel {
    public static String searchTotalHall() throws SQLException {
        String count="0";
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM Hall;";
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

    public List<HallDto> loadAllHallIds() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Hall";
        PreparedStatement pstm = connection.prepareStatement(sql);
        List<HallDto> dto = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            dto.add(new HallDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return dto;
    }

   /* public boolean sabeHalls(HallDto dto2) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Hall VALUES(?,?,?,?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto2.getId());
            pstm.setString(2, dto2.getName());
            pstm.setString(3, dto2.getAvailability());
            pstm.setString(4, dto2.getCapacity());
            return pstm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public HallDto searchHall(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Hall WHERE hallId=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,id);
            HallDto dto=null;
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                dto= new HallDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
            return dto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

