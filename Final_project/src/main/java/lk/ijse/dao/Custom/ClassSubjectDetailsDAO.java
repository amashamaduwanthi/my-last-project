package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDAO;

import java.sql.SQLException;

public interface ClassSubjectDetailsDAO extends CrudDAO {
    boolean addClass(String classId, String id,  String subName, String lectName) throws SQLException;
}
