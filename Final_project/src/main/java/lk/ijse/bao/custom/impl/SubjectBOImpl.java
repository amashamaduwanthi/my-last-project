package lk.ijse.bao.custom.impl;

import lk.ijse.bao.custom.SubjectBO;

import lk.ijse.dao.Custom.LecturerDAO;
import lk.ijse.dao.Custom.LecturerSubjectDAO;
import lk.ijse.dao.Custom.SubjectDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.SubjectLecturerDto;
import lk.ijse.dto.lecturerDto;
import lk.ijse.dto.subjectDto;

import java.sql.SQLException;
import java.util.List;

public class SubjectBOImpl implements SubjectBO {

    SubjectDAO subjectDAO= (SubjectDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUBJECT);
    LecturerDAO lecturerDAOImpl= (LecturerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LECTURER);
    LecturerSubjectDAO lecturerSubjectDAO= (LecturerSubjectDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LECTURERSUBJECT);


    @Override
    public boolean UpdateSubject(subjectDto dto) throws SQLException, ClassNotFoundException {
        return subjectDAO.update(dto);
    }

    @Override
    public List<subjectDto> loadAllSubject() throws SQLException, ClassNotFoundException {
        return subjectDAO.getAll();
    }

    @Override
    public String generateNxtSubjectId() throws SQLException, ClassNotFoundException {
        return subjectDAO.generateNextId();
    }

    @Override
    public String ChangeId(String subId) {
        return subjectDAO.ChangeId(subId);
    }

    @Override
    public boolean saveSubject(subjectDto dto) throws SQLException, ClassNotFoundException {
        return subjectDAO.save(dto);
    }

    @Override
    public boolean deleteSubject(String id) throws SQLException, ClassNotFoundException {
        return subjectDAO.delete(id);
    }

    @Override
    public subjectDto searchSubject(String id) throws SQLException, ClassNotFoundException {
        return subjectDAO.search(id);
    }

    @Override
    public lecturerDto searchLecturer(String id) throws SQLException, ClassNotFoundException {
        return lecturerDAOImpl.search(id);
    }

    @Override
    public List<lecturerDto> loadAllLecturer() throws SQLException, ClassNotFoundException {
        return lecturerDAOImpl.getAll();
    }

    @Override
    public List<SubjectLecturerDto> loadAllSubjectLecturer() throws SQLException, ClassNotFoundException {
        return lecturerSubjectDAO.getAll();
    }

    @Override
    public boolean saveSubjectLecturer(SubjectLecturerDto dto2) throws SQLException, ClassNotFoundException {
        return lecturerSubjectDAO.save(dto2);
    }
}
