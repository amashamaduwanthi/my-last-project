package lk.ijse.bao.custom.impl;

import lk.ijse.Entity.Admin;
import lk.ijse.Entity.Lecturer;
import lk.ijse.Entity.Subject;
import lk.ijse.Entity.SubjectLecturer;
import lk.ijse.bao.custom.SubjectBO;

import lk.ijse.dao.Custom.LecturerDAO;
import lk.ijse.dao.Custom.LecturerSubjectDAO;
import lk.ijse.dao.Custom.SubjectDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.SubjectLecturerDto;
import lk.ijse.dto.lecturerDto;
import lk.ijse.dto.subjectDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectBOImpl implements SubjectBO {

    SubjectDAO subjectDAO= (SubjectDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUBJECT);
    LecturerDAO lecturerDAOImpl= (LecturerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LECTURER);
    LecturerSubjectDAO lecturerSubjectDAO= (LecturerSubjectDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LECTURERSUBJECT);


    @Override
    public boolean UpdateSubject(subjectDto dto) throws SQLException, ClassNotFoundException {
        return subjectDAO.update(new Subject(dto.getId(),dto.getName(),dto.getDescription()));
    }

    @Override
    public List<subjectDto> loadAllSubject() throws SQLException, ClassNotFoundException {
        List<Subject> all = subjectDAO.getAll();
        List<subjectDto> subjectDtos = new ArrayList<>();

        for (Subject subject : all) {
            subjectDtos.add(new subjectDto(
                    subject.getId(),
                   subject.getName(),
                    subject.getDescription()));
        }
        return subjectDtos;
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
        return subjectDAO.save(new Subject(dto.getId(),dto.getName(),dto.getDescription()));
    }

    @Override
    public boolean deleteSubject(String id) throws SQLException, ClassNotFoundException {
        return subjectDAO.delete(id);
    }

    @Override
    public subjectDto searchSubject(String id) throws SQLException, ClassNotFoundException {
       Subject search= subjectDAO.search(id);
       return new subjectDto(search.getId(),search.getName(),search.getDescription());
    }

    @Override
    public lecturerDto searchLecturer(String id) throws SQLException, ClassNotFoundException {
       Lecturer search= lecturerDAOImpl.search(id);
        return new lecturerDto(search.getId(),search.getName(),search.getAddress(),search.getTel(),search.getNic(),search.getUniversity());
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


    @Override
    public List<SubjectLecturerDto> loadAllSubjectLecturer() throws SQLException, ClassNotFoundException {
       List<SubjectLecturer>all=lecturerSubjectDAO.getAll();
       List<SubjectLecturerDto>subjectLecturerDtos=new ArrayList<>();
       for(SubjectLecturer subjectLecturer:all){
          subjectLecturerDtos.add(new SubjectLecturerDto(subjectLecturer.getLecId(),subjectLecturer.getLecName(),subjectLecturer.getSubId(),subjectLecturer.getSubName()));
       }
       return subjectLecturerDtos;
    }

    @Override
    public boolean saveSubjectLecturer(SubjectLecturerDto dto2) throws SQLException, ClassNotFoundException {
        return lecturerSubjectDAO.save(new SubjectLecturer(dto2.getLecId(),dto2.getLecName(),dto2.getSubId(),dto2.getSubName()));
    }
}
