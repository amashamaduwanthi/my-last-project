package lk.ijse.bao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.StudentClassDto;

import java.sql.SQLException;
import java.util.List;

public interface StudentClassBO extends SuperDAO {

    List<StudentClassDto> loadAllDetails() throws SQLException, ClassNotFoundException;

    StudentClassDto searchStudentClass(String id) throws SQLException, ClassNotFoundException;
    boolean addClass(String classId, String id,  String subName, String lectName) throws SQLException;
}
