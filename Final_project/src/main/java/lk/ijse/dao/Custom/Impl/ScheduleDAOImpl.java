package lk.ijse.dao.Custom.Impl;

import lk.ijse.Entity.Schedule;
import lk.ijse.dao.Custom.ScheduleDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.classDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public  String generateNextId() throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT scheduleId FROM Schedule  ORDER BY scheduleId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet= SQLUtil.execute("SELECT scheduleId FROM Schedule  ORDER BY scheduleId DESC LIMIT 1");
        if(resultSet.next()){
            return ChangeId(resultSet.getString(1));
        }
       return ChangeId(null);
    }
    @Override

   public  String ChangeId(String ScheduleId) {
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
    @Override
    public boolean saveSchedule(classDto dto, String hallId) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Schedule VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getDescription());
        pstm.setString(3, dto.getDuration());
        pstm.setString(4, hallId);
        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("INSERT INTO Schedule VALUES(?,?,?,?)",dto.getId(),dto.getDescription(),dto.getDuration(),hallId);
    }

    public boolean delete(String id) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM Schedule WHERE scheduleId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("DELETE FROM Schedule WHERE scheduleId=?",id);
    }
    @Override
    public Schedule search(String id) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Schedule WHERE ScheduleId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();*/
       Schedule entity=null;
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Schedule WHERE ScheduleId=?",id);
        if(resultSet.next()){
            entity=new Schedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return entity;
    }
    @Override
    public boolean updateSchedule(classDto dto, String hallId) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE Schedule SET description=?,duration=? ,hallId=? WHERE scheduleId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getDescription());
        pstm.setString(2, dto.getDuration());
        pstm.setString(3, hallId);
        pstm.setString(4, dto.getId());

        return pstm.executeUpdate() > 0;*/
        return  SQLUtil.execute("UPDATE Schedule SET description=?,duration=? ,hallId=? WHERE scheduleId=?",dto.getDescription(),dto.getDuration(),hallId,dto.getId());

    }
    @Override
    public List<Schedule> getAll() throws SQLException {

          /*  Connection connection = DbConnection.getInstance().getConnection();
            String sql="SELECT * FROM Schedule";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();*/

            List<Schedule> list = new ArrayList<>();
            ResultSet resultSet=SQLUtil.execute("SELECT * FROM Schedule");
            while (resultSet.next()){
                list.add(new Schedule(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return list;

    }

    @Override
    public boolean save(Schedule entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Schedule entity) throws SQLException, ClassNotFoundException {
        return false;
    }
}

