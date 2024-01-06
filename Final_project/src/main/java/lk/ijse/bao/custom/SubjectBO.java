package lk.ijse.bao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.SubjectLecturerDto;
import lk.ijse.dto.lecturerDto;
import lk.ijse.dto.subjectDto;

import java.sql.SQLException;
import java.util.List;

public interface SubjectBO extends SuperDAO {
    boolean UpdateSubject(subjectDto dto) throws SQLException, ClassNotFoundException;
    List<subjectDto> loadAllSubject() throws SQLException, ClassNotFoundException;

    String generateNxtSubjectId() throws SQLException, ClassNotFoundException;

    String ChangeId(String subId) ;



    boolean saveSubject(subjectDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteSubject(String id) throws SQLException, ClassNotFoundException;

    subjectDto searchSubject(String id) throws SQLException, ClassNotFoundException;
    lecturerDto searchLecturer(String id) throws SQLException, ClassNotFoundException;
    List<lecturerDto> loadAllLecturer() throws SQLException, ClassNotFoundException;
    List<SubjectLecturerDto> loadAllSubjectLecturer() throws SQLException, ClassNotFoundException;
     boolean saveSubjectLecturer(SubjectLecturerDto dto2) throws SQLException, ClassNotFoundException;

}
