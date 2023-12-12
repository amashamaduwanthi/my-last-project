package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.classDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleModel {
    public static String generateId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT scheduleId FROM Schedule  ORDER BY scheduleId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return chngeId(resultSet.getString(1));
        }
       return chngeId(null);
    }

    private static String chngeId(String ScheduleId) {
        if(ScheduleId!=null){
            String[] split = ScheduleId.split("L0");
            int id= Integer.parseInt(split[1]);
            id++;
            if(id<10){
                return "L00"+id;
            }else{
                return "L0"+id;
            }
        }else{
            return "L001";
        }

    }

    public boolean saveSchedule(classDto dto, String hallId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Schedule VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getDescription());
        pstm.setString(3, dto.getDuration());
        pstm.setString(4, hallId);
        return pstm.executeUpdate()>0;
    }

    public boolean deleteSchedule(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM Schedule WHERE scheduleId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public classDto searchSchedule(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Schedule WHERE ScheduleId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();
        classDto dto=null;
        if(resultSet.next()){
            dto=new classDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return dto;
    }

    public boolean updateSchedule(classDto dto, String hallId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE Schedule SET description=?,duration=? ,hallId=? WHERE scheduleId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getDescription());
        pstm.setString(2, dto.getDuration());
        pstm.setString(3, hallId);
        pstm.setString(4, dto.getId());

        return pstm.executeUpdate() > 0;

    }

    public List<classDto> loadAllSchedule() throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();
            String sql="SELECT * FROM Schedule";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            List<classDto> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(new classDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return list;

    }
}

