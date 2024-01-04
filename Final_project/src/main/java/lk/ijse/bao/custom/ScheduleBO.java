package lk.ijse.bao.custom;

import lk.ijse.dto.HallDto;
import lk.ijse.dto.classDto;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleBO {
    String generateId() throws SQLException ;
    String chngeId(String ScheduleId);

    boolean saveSchedule(classDto dto, String hallId) throws SQLException;
    boolean deleteSchedule(String id) throws SQLException ;
    classDto searchSchedule(String id) throws SQLException;

    boolean updateSchedule(classDto dto, String hallId) throws SQLException ;
     List<classDto> loadAllSchedule() throws SQLException ;
    List<HallDto> loadAllHallIds() throws SQLException, ClassNotFoundException;

     HallDto searchHall(String id) throws SQLException, ClassNotFoundException;
}

