package lk.ijse.dao.Custom;

import lk.ijse.Entity.Schedule;
import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.classDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ScheduleDAO extends CrudDAO<Schedule> {
  //  String generateId() throws SQLException ;
  //   String chngeId(String ScheduleId);

   boolean saveSchedule(classDto dto, String hallId) throws SQLException ;
 //  boolean deleteSchedule(String id) throws SQLException ;
 //  classDto searchSchedule(String id) throws SQLException;

   boolean updateSchedule(classDto dto, String hallId) throws SQLException ;
 //   List<classDto> loadAllSchedule() throws SQLException ;
}
