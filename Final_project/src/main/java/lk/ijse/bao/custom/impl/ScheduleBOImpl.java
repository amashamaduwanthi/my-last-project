package lk.ijse.bao.custom.impl;

import lk.ijse.Entity.Admin;
import lk.ijse.Entity.Hall;
import lk.ijse.Entity.Schedule;
import lk.ijse.bao.custom.ScheduleBO;
import lk.ijse.dao.Custom.HallDAO;
import lk.ijse.dao.Custom.Impl.HallDAOImpl;
import lk.ijse.dao.Custom.Impl.ScheduleDAOImpl;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.HallDto;
import lk.ijse.dto.classDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleBOImpl implements ScheduleBO {
    ScheduleDAOImpl scheduleDAOImpl= (ScheduleDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SCHEDULE);
    HallDAO hallDAOImpl= (HallDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.HALL);
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
        Schedule search= scheduleDAOImpl.search(id);
        return new classDto(search.getId(),search.getDescription(),search.getDuration());
    }

    @Override
    public boolean updateSchedule(classDto dto, String hallId) throws SQLException {
        return scheduleDAOImpl.updateSchedule(dto,hallId);
    }

    @Override
    public List<classDto> loadAllSchedule() throws SQLException {
        List<Schedule> all = scheduleDAOImpl.getAll();
        List<classDto> classDtos = new ArrayList<>();

        for (Schedule schedule : all) {
           classDtos.add(new classDto(
                   schedule.getId(),
                    schedule.getDescription(),
                   schedule.getDuration()));
        }
        return classDtos;
    }

    @Override
    public List<HallDto> loadAllHallIds() throws SQLException, ClassNotFoundException {
        List<Hall> all = hallDAOImpl.getAll();
        List<HallDto> hallDtos = new ArrayList<>();

        for (Hall hall : all) {
            hallDtos.add(new HallDto(
                    hall.getId(),
                    hall.getName(),
                    hall.getAvailability(),
                    hall.getCapacity()));
        }
        return hallDtos;

    }

    @Override
    public HallDto searchHall(String id) throws SQLException, ClassNotFoundException {
        Hall search= hallDAOImpl.search(id);
        return new HallDto(search.getId(),search.getName(),search.getAvailability(),search.getCapacity());
    }
}
