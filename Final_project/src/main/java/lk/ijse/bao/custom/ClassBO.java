package lk.ijse.bao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.HallDto;
import lk.ijse.dto.class2Dto;

import java.sql.SQLException;
import java.util.List;

public interface ClassBO extends SuperDAO {
    String generateNxtClassId() throws SQLException ;

    boolean saveClass(class2Dto dto) throws SQLException;

    boolean deleteClass(String classId) throws SQLException ;

    boolean updateClass(class2Dto dto) throws SQLException ;

    class2Dto searchClass(String classId) throws SQLException ;
    List<class2Dto> loadAllclassIds() throws SQLException;
    HallDto searchHall(String id) throws SQLException;
}
