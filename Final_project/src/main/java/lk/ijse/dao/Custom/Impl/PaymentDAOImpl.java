package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.PaymentDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.PayementDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public  String generateNextId() throws SQLException {
     /*   Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT classfeeId FROM Classfee  ORDER BY classfeeId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet= SQLUtil.execute("SELECT classfeeId FROM Classfee  ORDER BY classfeeId DESC LIMIT 1 ");
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);
    }
    @Override
    public   String ChangeId(String classfeeId) {
        if (classfeeId!= null) {
            String[] split = classfeeId.split("P0");
            int id = Integer.parseInt(split[1]);
            id++;
            if(id<10){
                return "P00" + id;
            }else{
                return "P0"+id;
            }
        } else {
            return "P001";
        }
    }
    @Override
    public boolean save(PayementDto dto) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Classfee VALUES(?,?,?,?,?,?)";
        java.sql.PreparedStatement stm = connection.prepareStatement(sql);
        stm.setObject(1, dto.getId());
        stm.setObject(2, dto.getAmount());
        stm.setObject(3, dto.getDate());
        stm.setObject(4, dto.getStatus());
        stm.setObject(5, dto.getClassId());
        stm.setObject(6, dto.getStuId());

        return stm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO Classfee VALUES(?,?,?,?,?,?)",dto.getId(),dto.getAmount(),dto.getDate(),dto.getStatus(),dto.getClassId(),dto.getStuId());
    }
    @Override
    public boolean update(PayementDto dto) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE Classfee SET amount=?,date=?,status=?,classId=?,stuId=? WHERE classfeeId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, String.valueOf(dto.getAmount()));
        pstm.setString(2, dto.getDate());
        pstm.setString(3, dto.getStatus());
        pstm.setString(4, dto.getClassId());
        pstm.setString(5, dto.getStuId());
        pstm.setString(6, dto.getId());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE Classfee SET amount=?,date=?,status=?,classId=?,stuId=? WHERE classfeeId=?",dto.getAmount(),dto.getDate(),dto.getStatus(),dto.getClassId(),dto.getStuId(),dto.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PayementDto search(String id) throws SQLException {
     /*   Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Classfee WHERE classfeeId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();*/
        PayementDto dto = null;
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Classfee WHERE classfeeId=?",id);
        if (resultSet.next()) {

            String payId = resultSet.getString(1);
            double amount = resultSet.getDouble(2);
            String date = resultSet.getString(3);
            String status = resultSet.getString(4);
            String classId = resultSet.getString(5);
            String stuId = resultSet.getString(6);
             dto=new PayementDto(payId,amount,date,status,classId,stuId);


        }
        return dto;
    }
    @Override
    public List<PayementDto> getAll() throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Classfee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ArrayList<PayementDto> dtos = new ArrayList<>();
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Classfee");
        while (resultSet.next()){
            String payId = resultSet.getString(1);
            double amount = resultSet.getDouble(2);
            String date = resultSet.getString(3);
            String status = resultSet.getString(4);
            String classId = resultSet.getString(5);
            String stuId = resultSet.getString(6);
            dtos.add(new PayementDto(payId,amount,date,status,classId,stuId));
        }
        return dtos;
    }
}
