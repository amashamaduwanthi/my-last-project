package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.class2Dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassModel {
    public static String generateNxtClassId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT classId FROM Class ORDER BY classId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);
    }

    private static String ChangeId(String classId) {
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



    public boolean saveClass(class2Dto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Class VALUES(?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getGrade());


        return pstm.executeUpdate()>0;


    }

    public boolean deleteClass(String classId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM Class WHERE classId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,classId);
        return pstm.executeUpdate()>0;
    }

    public boolean updateClass(class2Dto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE Class SET grade=? WHERE classId=?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getGrade());


            pstm.setString(2, dto.getId());
            return pstm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public class2Dto searchSubject(String classId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Class WHERE classId=?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,classId);
            class2Dto dto=null;
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                dto= new class2Dto(
                        resultSet.getString(1),
                        resultSet.getString(2)

                );
            }

        return dto;
    }

    public List<class2Dto> loadAllclassIds() throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Class");
            ResultSet resultSet = pstm.executeQuery();
            List<class2Dto> class2Dtos = new ArrayList<>();
            while (resultSet.next()) {
                class2Dto dto = new class2Dto(
                        resultSet.getString(1),
                        resultSet.getString(2)
                );
                class2Dtos.add(dto);
            }
            return class2Dtos;

    }
}
