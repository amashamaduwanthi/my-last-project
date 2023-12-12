package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.lecturerDto;
import lk.ijse.dto.subjectDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Lecturermodel {
    public static String searchTotalLecturer() throws SQLException {
        String count="0";
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM Lecturer;";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                count=resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public static String generateNxtLecturerId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT lectId FROM Lecturer  ORDER BY lectId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);
    }

    private static String ChangeId(String lectId) {
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



    public boolean saveLecturer(lecturerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Lecturer VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setInt(4,dto.getTel());
        pstm.setString(5, dto.getNic());
        pstm.setString(6, dto.getUniversity());
        return pstm.executeUpdate()>0;


    }
    public static lecturerDto searchLecturer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Lecturer WHERE lectId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();
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
    public boolean delteLecturer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM Lecturer WHERE lectId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;

    }
    public boolean updateLecturer(lecturerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE Lecturer SET name=?,address=?,tel=?,NIC=?,university=? WHERE lectId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
       pstm.setString(2, dto.getAddress());
       pstm.setInt(3,dto.getTel());
       pstm.setString(4, dto.getNic());
       pstm.setString(5, dto.getUniversity());
        pstm.setString(6, dto.getId());

        return pstm.executeUpdate()>0;

    }
    public static List<lecturerDto> loadAllLecturer() throws SQLException{

            Connection connection = DbConnection.getInstance().getConnection();
            String sql="SELECT * FROM Lecturer";
            PreparedStatement pstm = connection.prepareStatement(sql);
            List<lecturerDto> lecturerList = new ArrayList<>();
            ResultSet resultSet = pstm.executeQuery();
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
