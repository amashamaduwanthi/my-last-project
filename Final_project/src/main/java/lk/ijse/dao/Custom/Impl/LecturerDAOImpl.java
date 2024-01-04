package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.LecturerDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.lecturerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LecturerDAOImpl implements LecturerDAO {
    @Override
    public String searchTotalLecturer() throws SQLException {
        String count="0";
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM Lecturer;";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();*/
        try {
            ResultSet resultSet= SQLUtil.execute("SELECT COUNT(*) FROM Lecturer;");
            if(resultSet.next()){
                count=resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    @Override
    public  String generateNextId() throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT lectId FROM Lecturer  ORDER BY lectId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SQLUtil.execute("SELECT lectId FROM Lecturer  ORDER BY lectId DESC LIMIT 1 ");
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);
    }
    @Override
    public String ChangeId(String lectId) {
        if (lectId!= null) {
            String[] split = lectId.split("L0");
            int id = Integer.parseInt(split[1]);
            id++;
            if(id<10){
                return "L00" + id;
            }else{
                return "L0"+id;
            }

        } else {
            return "L001";
        }
    }

    @Override

    public boolean save(lecturerDto dto) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Lecturer VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setInt(4,dto.getTel());
        pstm.setString(5, dto.getNic());
        pstm.setString(6, dto.getUniversity());
        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("INSERT INTO Lecturer VALUES(?,?,?,?,?,?)",dto.getId(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getNic(),dto.getUniversity());

    }
    @Override
    public  lecturerDto search(String id) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Lecturer WHERE lectId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Lecturer WHERE lectId=?",id);
        lecturerDto dto=null;
        if(resultSet.next()){
            dto=new lecturerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
                    );
        }
             return dto;
    }
    @Override
    public boolean delete(String id) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM Lecturer WHERE lectId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("DELETE FROM Lecturer WHERE lectId=?",id);

    }
    @Override
    public boolean update(lecturerDto dto) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE Lecturer SET name=?,address=?,tel=?,NIC=?,university=? WHERE lectId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
       pstm.setString(2, dto.getAddress());
       pstm.setInt(3,dto.getTel());
       pstm.setString(4, dto.getNic());
       pstm.setString(5, dto.getUniversity());
        pstm.setString(6, dto.getId());

        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("UPDATE Lecturer SET name=?,address=?,tel=?,NIC=?,university=? WHERE lectId=?",dto.getName(),dto.getAddress(),dto.getTel(),dto.getNic(),dto.getUniversity(),dto.getId());

    }
    @Override
    public List<lecturerDto> getAll() throws SQLException{

          /*  Connection connection = DbConnection.getInstance().getConnection();
            String sql="SELECT * FROM Lecturer";
            PreparedStatement pstm = connection.prepareStatement(sql);
            List<lecturerDto> lecturerList = new ArrayList<>();
            ResultSet resultSet = pstm.executeQuery();*/
        List<lecturerDto> lecturerList = new ArrayList<>();
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Lecturer");
            while (resultSet.next()){
                lecturerList.add(new lecturerDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                ));

            }
            return lecturerList;
        }

}