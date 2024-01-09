package lk.ijse.bao.custom.impl;

import lk.ijse.Entity.Admin;
import lk.ijse.Entity.Classes;
import lk.ijse.Entity.Hall;
import lk.ijse.bao.custom.ClassBO;
import lk.ijse.dao.Custom.ClassDAO;
import lk.ijse.dao.Custom.Impl.ClassDAOImpl;
import lk.ijse.dao.Custom.Impl.HallDAOImpl;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.HallDto;
import lk.ijse.dto.class2Dto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassBOImpl implements ClassBO {
    private ClassDAOImpl classDAOImpl = (ClassDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CLASS);
    private HallDAOImpl hallDAOImpl= (HallDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.HALL);

    @Override
    public String generateNxtClassId() throws SQLException {
        return classDAOImpl.generateNextId();
    }

    @Override
    public boolean saveClass(class2Dto dto) throws SQLException {
        return classDAOImpl.save(new Classes(dto.getId(),dto.getGrade()));

    }

    @Override
    public boolean deleteClass(String classId) throws SQLException {
        return classDAOImpl.delete(classId);
    }

    @Override
    public boolean updateClass(class2Dto dto) throws SQLException {
        return classDAOImpl.update(new Classes(dto.getId(),dto.getGrade()));
    }

    @Override
    public class2Dto searchClass(String classId) throws SQLException {
        Classes search= classDAOImpl.search(classId);
        return  new class2Dto(search.getId(),search.getGrade());
    }

    @Override
    public List<class2Dto> loadAllclassIds() throws SQLException {
        List<Classes> all = classDAOImpl.getAll();
        List<class2Dto> class2Dtos = new ArrayList<>();

        for (Classes classes : all) {
            class2Dtos.add(new class2Dto(
                    classes.getId(),
                    classes.getGrade()));
        }
        return class2Dtos;
    }

    @Override
    public HallDto searchHall(String id) throws SQLException {
        Hall search= hallDAOImpl.search(id);
        return new HallDto(search.getId(),search.getName(),search.getAvailability(),search.getCapacity());
    }

    @Override
    public List<HallDto> loadAllHallIds() throws SQLException {
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
}