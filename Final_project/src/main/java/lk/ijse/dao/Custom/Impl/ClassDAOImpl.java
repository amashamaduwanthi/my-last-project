package lk.ijse.dao.Custom.Impl;

import lk.ijse.Entity.Classes;
import lk.ijse.dao.Custom.ClassDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.class2Dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAOImpl implements ClassDAO {
    @Override
    public  String generateNextId() throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT classId FROM Class ORDER BY classId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet = SQLUtil.execute("SELECT classId FROM Class ORDER BY classId DESC LIMIT 1 ");
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);
    }

  public String ChangeId(String classId) {
        if (classId!= null) {
            String[] split = classId.split("C0");
            int id = Integer.parseInt(split[1]);
            id++;
            if(id<10) {
                return "C00" + id;
            }else {
                return "C0" + id;
            }
        } else {
            return "C001";
        }
    }
    @Override

    public boolean save(Classes entity) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Class VALUES(?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getGrade());


        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("INSERT INTO Class VALUES(?,?)",entity.getId(),entity.getGrade());


    }
    @Override

    public boolean delete(String classId) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM Class WHERE classId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,classId);
        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("DELETE FROM Class WHERE classId=?",classId);
    }
    @Override

    public boolean update(Classes entity) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE Class SET grade=? WHERE classId=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getGrade());


            pstm.setString(2, dto.getId());
            return pstm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        return SQLUtil.execute("UPDATE Class SET grade=? WHERE classId=?",entity.getGrade(),entity.getId());
    }
    @Override

    public Classes search(String classId) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Class WHERE classId=?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,classId);

            class2Dto dto=null;
            ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Class WHERE classId=?",classId);
        Classes entity=null;
            if (resultSet.next()){
               entity= new Classes(
                        resultSet.getString(1),
                        resultSet.getString(2)

                );
            }

        return entity;
    }
    @Override

    public List<Classes> getAll() throws SQLException {

           /* Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Class");
            ResultSet resultSet = pstm.executeQuery();*/
            ResultSet resultSet=SQLUtil.execute("SELECT * FROM Class");
            List<Classes>classes = new ArrayList<>();
            while (resultSet.next()) {
                Classes classes1 = new Classes(
                        resultSet.getString(1),
                        resultSet.getString(2)
                );
               classes.add(classes1);
            }
            return classes;

    }
}
