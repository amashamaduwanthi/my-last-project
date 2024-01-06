package lk.ijse.bao.custom.impl;

import lk.ijse.bao.custom.ExamBO;
import lk.ijse.dao.Custom.ExamDAO;
import lk.ijse.dao.Custom.Impl.ExamDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.ExamDto;
import lk.ijse.dto.ResultDto;
import lk.ijse.dto.studentDto;

import java.sql.SQLException;
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
        return examDAOImpl.save(dto);
    }

    @Override
    public boolean UpdateExam(ExamDto dto) throws SQLException, ClassNotFoundException {
        return examDAOImpl.update(dto);
    }

    @Override
    public boolean deleteExam(String id) throws SQLException, ClassNotFoundException {
        return examDAOImpl.delete(id);
    }

    @Override
    public ExamDto searchExam(String id) throws SQLException, ClassNotFoundException {
        return examDAOImpl.search(id);
    }

    @Override
    public List<ExamDto> getAllExam() throws SQLException, ClassNotFoundException {
        return examDAOImpl.getAll();
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
        return studentDAOImpl.getAll();
    }

    @Override
    public studentDto searchStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAOImpl.search(id);
    }
}
