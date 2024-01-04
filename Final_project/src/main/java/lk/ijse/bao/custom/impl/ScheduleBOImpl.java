package lk.ijse.bao.custom.impl;

import lk.ijse.bao.custom.ScheduleBO;
import lk.ijse.dao.Custom.HallDAO;
import lk.ijse.dao.Custom.Impl.HallDAOImpl;
import lk.ijse.dao.Custom.Impl.ScheduleDAOImpl;
import lk.ijse.dto.HallDto;
import lk.ijse.dto.classDto;

import java.sql.SQLException;
import java.util.List;

public class ScheduleBOImpl implements ScheduleBO {
    ScheduleDAOImpl scheduleDAOImpl=new ScheduleDAOImpl();
    HallDAO hallDAOImpl=new HallDAOImpl();
    @Override
    public String generateId() throws SQLException {
        return scheduleDAOImpl.generateNextId();
    }

    @Override
    public String chngeId(String ScheduleId) {
        return scheduleDAOImpl.ChangeId(ScheduleId);
    }

    @Override
    public boolean saveSchedule(classDto dto, String hallId) throws SQLException {
        return scheduleDAOImpl.saveSchedule(dto,hallId);
    }

    @Override
    public boolean deleteSchedule(String id) throws SQLException {
        return scheduleDAOImpl.delete(id);
    }

    @Override
    public classDto searchSchedule(String id) throws SQLException {
        return scheduleDAOImpl.search(id);
    }

    @Override
    public boolean updateSchedule(classDto dto, String hallId) throws SQLException {
        return scheduleDAOImpl.updateSchedule(dto,hallId);
    }

    @Override
    public List<classDto> loadAllSchedule() throws SQLException {
        return scheduleDAOImpl.getAll();
    }

    @Override
    public List<HallDto> loadAllHallIds() throws SQLException, ClassNotFoundException {
        return hallDAOImpl.getAll();
    }

    @Override
    public HallDto searchHall(String id) throws SQLException, ClassNotFoundException {
        return hallDAOImpl.search(id);
    }
}
