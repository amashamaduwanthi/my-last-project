package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.ScheduleDAO;
import lk.ijse.dao.Custom.SubjectDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SubjectLecturerDto;
import lk.ijse.dto.subjectDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
    @Override
    public  boolean update(subjectDto dto) throws SQLException {
     /*   Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE Subject SET name=?,description=? WHERE subId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getDescription());
        pstm.setString(3, dto.getId());
        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("UPDATE Subject SET name=?,description=? WHERE subId=?",dto.getName(),dto.getDescription(),dto.getId());
    }
    @Override
    public List<subjectDto> getAll() throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Subject";
        PreparedStatement pstm = connection.prepareStatement(sql);
        List<subjectDto> subjectdto = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();*/
        List<subjectDto> subjectdto = new ArrayList<>();
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Subject");
        while (resultSet.next()){
            subjectdto.add(new subjectDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return subjectdto;
    }
    @Override
    public  String generateNextId() throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT subId FROM Subject  ORDER BY subId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SQLUtil.execute( "SELECT subId FROM Subject  ORDER BY subId DESC LIMIT 1 ");
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);
    }
    @Override
   public String ChangeId(String subId) {
        if (subId!= null) {
            String[] split = subId.split("S0");
            int id = Integer.parseInt(split[1]);
            id++;
            if (id < 10) {
                return "S00" + id;
            }
            return "S0" + id;
        } else {
            return "S001";
        }
    }

    @Override

    public boolean save(subjectDto dto) throws SQLException {
     /*   Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Subject VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getDescription());
        return pstm.executeUpdate()>0;*/
        return  SQLUtil.execute("INSERT INTO Subject VALUES(?,?,?)",dto.getId(),dto.getName(),dto.getDescription());

    }
    @Override
    public boolean delete(String id) throws SQLException {
     /*   Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM Subject WHERE subId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("DELETE FROM Subject WHERE subId=?",id);

    }
    @Override
    public subjectDto search(String id) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Subject WHERE subId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);*/
        subjectDto subjectDto=null;
       ResultSet resultSet=SQLUtil.execute("SELECT * FROM Subject WHERE subId=?",id);
        if(resultSet.next()){
            String sub_id=resultSet.getString(1);
            String name=resultSet.getString(2);
            String description=resultSet.getString(3);
           subjectDto= new subjectDto(sub_id,name,description);
        }
        return subjectDto;
    }


}
