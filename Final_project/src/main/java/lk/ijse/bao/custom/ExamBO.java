package lk.ijse.bao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.ExamDto;
import lk.ijse.dto.ResultDto;
import lk.ijse.dto.studentDto;

import java.sql.SQLException;
import java.util.List;

public interface ExamBO  extends SuperDAO {
    String generateNxtExamId() throws SQLException, ClassNotFoundException;
    String ChangeId(String examId) ;
    boolean SaveExam(ExamDto dto) throws SQLException, ClassNotFoundException;
    boolean UpdateExam(ExamDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteExam(String id) throws SQLException, ClassNotFoundException;
    ExamDto searchExam(String id) throws SQLException, ClassNotFoundException;
    List<ExamDto> getAllExam() throws SQLException, ClassNotFoundException;
    boolean SaveResult(ResultDto dto1) throws SQLException;
    List<ResultDto> loadAllResult() throws SQLException ;
    List<studentDto> loadAllStudent() throws SQLException, ClassNotFoundException;
    studentDto searchStudent(String id) throws SQLException, ClassNotFoundException;
}
