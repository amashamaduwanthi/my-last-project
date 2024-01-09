package lk.ijse.bao.custom.impl;

import lk.ijse.Entity.Exam;
import lk.ijse.Entity.Lecturer;
import lk.ijse.bao.custom.LecturerBO;
import lk.ijse.dao.Custom.Impl.LecturerDAOImpl;
import lk.ijse.dao.Custom.LecturerDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.ExamDto;
import lk.ijse.dto.lecturerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LecturerBOImpl implements LecturerBO {
    private LecturerDAO lecturerDAOImpl= (LecturerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LECTURER);
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
        return lecturerDAOImpl.save(new Lecturer(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getNic(),dto.getUniversity()));
    }

    @Override
    public lecturerDto searchLecturer(String id) throws SQLException, ClassNotFoundException {
        Lecturer search= lecturerDAOImpl.search(id);
        return new lecturerDto(search.getId(),search.getName(),search.getAddress(),search.getTel(),search.getNic(),search.getUniversity());
    }

    @Override
    public boolean delteLecturer(String id) throws SQLException, ClassNotFoundException {
        return lecturerDAOImpl.delete(id);
    }

    @Override
    public boolean updateLecturer(lecturerDto dto) throws SQLException, ClassNotFoundException {
        return lecturerDAOImpl.update(new Lecturer(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getNic(),dto.getUniversity()));
    }

    @Override
    public List<lecturerDto> loadAllLecturer() throws SQLException, ClassNotFoundException {
        List<Lecturer> all = lecturerDAOImpl.getAll();
        List<lecturerDto> lecturers = new ArrayList<>();

        for (Lecturer lect : all) {
           lecturers.add(new lecturerDto(lect.getId(),
                   lect.getName(),
                   lect.getAddress(),
                   lect.getTel(),
                   lect.getNic(),
                   lect.getUniversity()));


        }
        return lecturers;
    }

}

