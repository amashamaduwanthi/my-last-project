package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.StudentClassDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentClassModel {
    public List<StudentClassDto> loadAllDetails() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM studentClassDetails");
        ResultSet resultSet = pstm.executeQuery();
        List<StudentClassDto> studentClassDtos= new ArrayList<>();
        while (resultSet.next()) {
           StudentClassDto dto = new StudentClassDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            studentClassDtos.add(dto);
        }
        return studentClassDtos;

    }

    public StudentClassDto searchStudentClass(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM studentClassDetails WHERE  stuId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();
        StudentClassDto dto=null;
        while (resultSet.next()){
            String class_id = resultSet.getString(1);
            String stu_id = resultSet.getString(2);
            String sub_name = resultSet.getString(3);
            String lec_name = resultSet.getString(4);
           dto= new StudentClassDto(class_id,stu_id,sub_name,lec_name);

        }
        return dto;
    }
}

