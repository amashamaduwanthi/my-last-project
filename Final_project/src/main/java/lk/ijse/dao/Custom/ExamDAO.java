package lk.ijse.dao.Custom;

import lk.ijse.Entity.Exam;
import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ExamDto;
import lk.ijse.dto.ResultDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ExamDAO extends CrudDAO<Exam> {
   /* String generateNxtExamId() throws SQLException ;

     String ChangeId(String examId) ;

    boolean SaveExam(ExamDto dto) throws SQLException ;

    boolean UpdateExam(ExamDto dto) throws SQLException ;

   boolean deleteExam(String id) throws SQLException ;

   ExamDto searchExam(String id) throws SQLException ;



    List<ExamDto> getAllExam() throws SQLException ;

   */

    boolean SaveResult(ResultDto dto1) throws SQLException ;
    List<ResultDto> loadAllResult() throws SQLException ;
}
