package lk.ijse.dao.Custom;

import lk.ijse.Entity.Lecturer;
import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.lecturerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LecturerDAO extends CrudDAO<Lecturer> {
   String searchTotalLecturer() throws SQLException ;
    /*String generateNxtLecturerId() throws SQLException ;

    String ChangeId(String lectId);

     boolean saveLecturer(lecturerDto dto) throws SQLException ;
    lecturerDto searchLecturer(String id) throws SQLException ;
     boolean delteLecturer(String id) throws SQLException ;
     boolean updateLecturer(lecturerDto dto) throws SQLException ;
    List<lecturerDto> loadAllLecturer() throws SQLException;*/
}
