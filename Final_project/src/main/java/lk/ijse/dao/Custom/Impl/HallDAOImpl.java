package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.HallDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.HallDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallDAOImpl implements HallDAO {
    public String searchTotalHall() throws SQLException {
        String count="0";
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM Hall;";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();*/
        try {
        ResultSet resultSet= SQLUtil.execute("SELECT COUNT(*) FROM Hall;");
            if(resultSet.next()){
                count=resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
@Override
    public List<HallDto> getAll() throws SQLException {

        List<HallDto> dto = new ArrayList<>();
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Hall");
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

    @Override
    public boolean save(HallDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(HallDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String ChangeId(String subId) {
        return null;
    }


    @Override
    public HallDto search(String id) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Hall WHERE hallId=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,id);
            HallDto dto=null;
            ResultSet resultSet = pstm.executeQuery();*/
        try {
        HallDto dto=null;
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Hall WHERE hallId=?",id);
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

