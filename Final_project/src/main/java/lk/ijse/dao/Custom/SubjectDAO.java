package lk.ijse.dao.Custom;

import lk.ijse.Entity.Subject;
import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SubjectLecturerDto;
import lk.ijse.dto.subjectDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SubjectDAO extends CrudDAO<Subject> {
  /*  boolean UpdateSubject(subjectDto dto) throws SQLException ;
     List<subjectDto> loadAllSubject() throws SQLException;

    String generateNxtSubjectId() throws SQLException ;

    String ChangeId(String subId) ;



     boolean saveSubject(subjectDto dto) throws SQLException ;

    boolean deleteSubject(String id) throws SQLException ;

    subjectDto searchSubject(String id) throws SQLException ;


    */


}
