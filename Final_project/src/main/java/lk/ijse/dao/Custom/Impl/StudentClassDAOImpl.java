package lk.ijse.dao.Custom.Impl;

import lk.ijse.Entity.StudentClass;
import lk.ijse.dao.Custom.StudentClassDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.StudentClassDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentClassDAOImpl implements StudentClassDAO {
    @Override
    public List<StudentClass> getAll() throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM studentClassDetails");
        ResultSet resultSet = pstm.executeQuery();*/
        List<StudentClass> studentClassDtos= new ArrayList<>();
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM studentClassDetails");
        while (resultSet.next()) {
           StudentClass dto = new StudentClass(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            studentClassDtos.add(dto);
        }
        return studentClassDtos;

    }

    @Override
    public boolean save(StudentClass dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(StudentClass dto) throws SQLException, ClassNotFoundException {
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
    public StudentClass search(String id) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM studentClassDetails WHERE  stuId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();*/
        StudentClass dto=null;
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM studentClassDetails WHERE  stuId=?",id);
        while (resultSet.next()){
            String class_id = resultSet.getString(1);
            String stu_id = resultSet.getString(2);
            String sub_name = resultSet.getString(3);
            String lec_name = resultSet.getString(4);
           dto= new StudentClass(class_id,stu_id,sub_name,lec_name);

        }
        return dto;
    }


}

