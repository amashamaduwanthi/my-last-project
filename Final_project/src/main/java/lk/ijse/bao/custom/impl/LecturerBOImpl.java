package lk.ijse.bao.custom.impl;

import lk.ijse.bao.custom.LecturerBO;
import lk.ijse.dao.Custom.Impl.LecturerDAOImpl;
import lk.ijse.dao.Custom.LecturerDAO;
import lk.ijse.dto.lecturerDto;

import java.sql.SQLException;
import java.util.List;

public class LecturerBOImpl implements LecturerBO {
    private LecturerDAO lecturerDAOImpl=new LecturerDAOImpl();
    @Override
    public String generateNxtLecturerId() throws SQLException, ClassNotFoundException {
        return lecturerDAOImpl.generateNextId();
    }

    @Override
    public String ChangeId(String lectId) {
        return lecturerDAOImpl.ChangeId(lectId);
    }

    @Override
    public boolean saveLecturer(lecturerDto dto) throws SQLException, ClassNotFoundException {
        return lecturerDAOImpl.save(dto);
    }

    @Override
    public lecturerDto searchLecturer(String id) throws SQLException, ClassNotFoundException {
        return lecturerDAOImpl.search(id);
    }

    @Override
    public boolean delteLecturer(String id) throws SQLException, ClassNotFoundException {
        return lecturerDAOImpl.delete(id);
    }

    @Override
    public boolean updateLecturer(lecturerDto dto) throws SQLException, ClassNotFoundException {
        return lecturerDAOImpl.update(dto);
    }

    @Override
    public List<lecturerDto> loadAllLecturer() throws SQLException, ClassNotFoundException {
        return lecturerDAOImpl.getAll();
    }
}
