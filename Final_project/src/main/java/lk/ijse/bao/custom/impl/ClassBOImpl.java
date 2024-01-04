package lk.ijse.bao.custom.impl;

import lk.ijse.bao.custom.ClassBO;
import lk.ijse.dao.Custom.ClassDAO;
import lk.ijse.dao.Custom.Impl.ClassDAOImpl;
import lk.ijse.dao.Custom.Impl.HallDAOImpl;
import lk.ijse.dto.HallDto;
import lk.ijse.dto.class2Dto;

import java.sql.SQLException;
import java.util.List;

public class ClassBOImpl implements ClassBO {
    private ClassDAOImpl classDAOImpl = new ClassDAOImpl();
    private HallDAOImpl hallDAOImpl=new HallDAOImpl();

    @Override
    public String generateNxtClassId() throws SQLException {
        return classDAOImpl.generateNextId();
    }

    @Override
    public boolean saveClass(class2Dto dto) throws SQLException {
        return classDAOImpl.save(new class2Dto(dto.getId(),dto.getGrade()));

    }

    @Override
    public boolean deleteClass(String classId) throws SQLException {
        return classDAOImpl.delete(classId);
    }

    @Override
    public boolean updateClass(class2Dto dto) throws SQLException {
        return classDAOImpl.update(new class2Dto(dto.getId(),dto.getGrade()));
    }

    @Override
    public class2Dto searchClass(String classId) throws SQLException {
        return classDAOImpl.search(classId);
    }

    @Override
    public List<class2Dto> loadAllclassIds() throws SQLException {
        return classDAOImpl.getAll();
    }

    @Override
    public HallDto searchHall(String id) throws SQLException {
        return hallDAOImpl.search(id);
    }
}