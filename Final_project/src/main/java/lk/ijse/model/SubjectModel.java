package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.SubjectLecturerDto;
import lk.ijse.dto.subjectDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectModel {
    public static boolean UpdateSubject(subjectDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE Subject SET name=?,description=? WHERE subId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getDescription());
        pstm.setString(3, dto.getId());
        return pstm.executeUpdate()>0;
    }

    public static List<subjectDto> loadAllSubject() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Subject";
        PreparedStatement pstm = connection.prepareStatement(sql);
        List<subjectDto> subjectdto = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            subjectdto.add(new subjectDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return subjectdto;
    }

    public static String generateNxtSubjectId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT subId FROM Subject  ORDER BY subId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);
    }

    private static String ChangeId(String subId) {
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



    public boolean saveSubject(subjectDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Subject VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getDescription());
        return pstm.executeUpdate()>0;

    }

    public boolean deleteSubject(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM Subject WHERE subId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;

    }

    public subjectDto searchSubject(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Subject WHERE subId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        subjectDto subjectDto=null;
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            String sub_id=resultSet.getString(1);
            String name=resultSet.getString(2);
            String description=resultSet.getString(3);
           subjectDto= new subjectDto(sub_id,name,description);
        }
        return subjectDto;
    }


    public boolean saveSubjectLecturer(SubjectLecturerDto dto2) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO lecSubDetails VALUES(?,?,?,?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto2.getLecId());
            pstm.setString(2, dto2.getLecName());
            pstm.setString(3, dto2.getSubId());
            pstm.setString(4, dto2.getSubName());
            return pstm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
