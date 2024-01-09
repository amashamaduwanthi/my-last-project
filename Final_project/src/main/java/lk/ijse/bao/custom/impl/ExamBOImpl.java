package lk.ijse.bao.custom.impl;

import lk.ijse.Entity.Admin;
import lk.ijse.Entity.Exam;
import lk.ijse.Entity.Student;
import lk.ijse.bao.custom.ExamBO;
import lk.ijse.dao.Custom.ExamDAO;
import lk.ijse.dao.Custom.Impl.ExamDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.ExamDto;
import lk.ijse.dto.ResultDto;
import lk.ijse.dto.studentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamBOImpl implements ExamBO {
    private ExamDAO examDAOImpl = (ExamDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EXAM);
    StudentDAO studentDAOImpl= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
    @Override
    public String generateNxtExamId() throws SQLException, ClassNotFoundException {
        return examDAOImpl.generateNextId();
    }

    @Override
    public String ChangeId(String examId) {
        return examDAOImpl.ChangeId(examId);
    }

    @Override
    public boolean SaveExam(ExamDto dto) throws SQLException, ClassNotFoundException {
        return examDAOImpl.save(new Exam(dto.getId(),dto.getName(),dto.getDate()));
    }

    @Override
    public boolean UpdateExam(ExamDto dto) throws SQLException, ClassNotFoundException {
        return examDAOImpl.update(new Exam(dto.getId(),dto.getName(),dto.getDate()));
    }

    @Override
    public boolean deleteExam(String id) throws SQLException, ClassNotFoundException {
        return examDAOImpl.delete(id);
    }

    @Override
    public ExamDto searchExam(String id) throws SQLException, ClassNotFoundException {
        Exam search= examDAOImpl.search(id);
        return new ExamDto(search.getId(),search.getName(),search.getDate());
    }

    @Override
    public List<ExamDto> getAllExam() throws SQLException, ClassNotFoundException {
        List<Exam> all = examDAOImpl.getAll();
        List<ExamDto> examDtos = new ArrayList<>();

        for (Exam exam : all) {
           examDtos.add(new ExamDto(
                    exam.getId(),
                    exam.getName(),
                    exam.getDate()));

        }
        return examDtos;
    }

    @Override
    public boolean SaveResult(ResultDto dto1) throws SQLException {
        return examDAOImpl.SaveResult(dto1);
    }

    @Override
    public List<ResultDto> loadAllResult() throws SQLException {
        return examDAOImpl.loadAllResult();
    }

    @Override
    public List<studentDto> loadAllStudent() throws SQLException, ClassNotFoundException {
        List<Student> all = studentDAOImpl.getAll();
        List<studentDto>studentDtos = new ArrayList<>();

        for (Student student : all) {
            studentDtos.add(new studentDto(
                    student.getId(),
                    student.getName(),
                    student.getAddress(),
                    student.getEmail(),
                    student.getContactNo(),
                    student.getGender(),
                    student.getDateOfBirth()));
        }
        return studentDtos;
    }


    @Override
    public studentDto searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student search= studentDAOImpl.search(id);
        return new studentDto(search.getId(),search.getName(),search.getAddress(),search.getEmail(),search.getContactNo(),search.getGender(),search.getDateOfBirth());
    }
}
