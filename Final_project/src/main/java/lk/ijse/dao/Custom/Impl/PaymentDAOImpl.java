package lk.ijse.dao.Custom.Impl;

import lk.ijse.Entity.Payment;
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
    public boolean save(Payment entity) throws SQLException {
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
        return SQLUtil.execute("INSERT INTO Classfee VALUES(?,?,?,?,?,?)",entity.getId(),entity.getAmount(),entity.getDate(),entity.getStatus(),entity.getClassId(),entity.getStuId());
    }
    @Override
    public boolean update(Payment entity) throws SQLException {
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
        return SQLUtil.execute("UPDATE Classfee SET amount=?,date=?,status=?,classId=?,stuId=? WHERE classfeeId=?",entity.getAmount(),entity.getDate(),entity.getStatus(),entity.getClassId(),entity.getStuId(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment search(String id) throws SQLException {
     /*   Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Classfee WHERE classfeeId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();*/
        Payment entity = null;
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Classfee WHERE classfeeId=?",id);
        if (resultSet.next()) {

            String payId = resultSet.getString(1);
            double amount = resultSet.getDouble(2);
            String date = resultSet.getString(3);
            String status = resultSet.getString(4);
            String classId = resultSet.getString(5);
            String stuId = resultSet.getString(6);
             entity=new Payment(payId,amount,date,status,classId,stuId);


        }
        return entity;
    }
    @Override
    public List<Payment> getAll() throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Classfee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ArrayList<Payment> payments= new ArrayList<>();
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Classfee");
        while (resultSet.next()){
            String payId = resultSet.getString(1);
            double amount = resultSet.getDouble(2);
            String date = resultSet.getString(3);
            String status = resultSet.getString(4);
            String classId = resultSet.getString(5);
            String stuId = resultSet.getString(6);
            payments.add(new Payment(payId,amount,date,status,classId,stuId));
        }
        return payments;
    }
}
