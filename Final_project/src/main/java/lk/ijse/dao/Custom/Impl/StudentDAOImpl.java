package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.studentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
@Override
    public  List<studentDto> getAll() throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Student";
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        List<studentDto> studentDtos = new ArrayList<>();
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM Student");
        while (resultSet.next()) {
            studentDtos.add(new studentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return studentDtos;
    }
    @Override
    public boolean save(studentDto dto) throws SQLException {

       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Student VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setString(2, name);
        pstm.setString(3, address);
        pstm.setString(4, email);
        pstm.setInt(5, contactNo);
        pstm.setString(6, gender);
        pstm.setString(7, dateOfBirth);

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO Student VALUES(?,?,?,?,?,?,?)",dto.getId(),dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContactNo(),dto.getGender(),dto.getDateOfBirth());
    }
    @Override
    public  studentDto search(String id) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Student WHERE stuId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();*/
        studentDto dto = null;
        ResultSet resultSet=SQLUtil.execute( "SELECT * FROM Student WHERE stuId=?",id);
        if (resultSet.next()) {
            String stu_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String email = resultSet.getString(4);
            int contactNo = Integer.parseInt(resultSet.getString(5));
            String gender = resultSet.getString(6);
            String dateOfBirth = resultSet.getString(7);

            dto = new studentDto(stu_id, name, address, email, contactNo, gender, dateOfBirth);

        }
        return dto;
    }
    @Override
    public boolean update(studentDto dto) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE Student SET name=?,address=?,email=? ,contactNo=?,gender=?,dateOfBirth=?WHERE stuId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getEmail());
        pstm.setInt(4, dto.getContactNo());
        pstm.setString(5, dto.getGender());
        pstm.setString(6, dto.getDateOfBirth());
        pstm.setString(7, dto.getId());
        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE Student SET name=?,address=?,email=? ,contactNo=?,gender=?,dateOfBirth=?WHERE stuId=?",dto.getName(),dto.getAddress(),dto.getEmail(),dto.getContactNo(),dto.getGender(),dto.getDateOfBirth(),dto.getId());
    }
    @Override
    public boolean delete(String id) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM Student WHERE stuId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("DELETE FROM Student WHERE stuId=?",id);

    }
    @Override
    public String generateNextId() throws SQLException {

     /*   Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT stuId FROM Student  ORDER BY stuId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SQLUtil.execute("SELECT stuId FROM Student  ORDER BY stuId DESC LIMIT 1 ");
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);
    }
    @Override
  public String ChangeId(String stuId) {
        if (stuId != null) {
            String[] split = stuId.split("S0");
            int id = Integer.parseInt(split[1]);
            id++;
            if(id<10){
                return "S00" + id;
            }else{
                return "S0"+id;
            }

        } else {
            return "S001";
        }
    }

    @Override
    public  String searchTotalStudent() throws SQLException {
        String count="0";
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM Student;";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();*/
        try{
            ResultSet resultSet=SQLUtil.execute("SELECT COUNT(*) FROM Student;");
            if(resultSet.next()){
                count=resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}

