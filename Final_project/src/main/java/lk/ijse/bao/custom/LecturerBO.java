package lk.ijse.bao.custom;

import lk.ijse.dto.lecturerDto;

import java.sql.SQLException;
import java.util.List;

public interface LecturerBO {
    String generateNxtLecturerId() throws SQLException, ClassNotFoundException;

    String ChangeId(String lectId);

    boolean saveLecturer(lecturerDto dto) throws SQLException, ClassNotFoundException;
    lecturerDto searchLecturer(String id) throws SQLException, ClassNotFoundException;
    boolean delteLecturer(String id) throws SQLException, ClassNotFoundException;
    boolean updateLecturer(lecturerDto dto) throws SQLException, ClassNotFoundException;
    List<lecturerDto> loadAllLecturer() throws SQLException, ClassNotFoundException;
}
