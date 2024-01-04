package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.class2Dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClassDAO extends CrudDAO<class2Dto> {
  /*  String generateNxtClassId() throws SQLException ;

    boolean saveClass(class2Dto dto) throws SQLException;

    boolean deleteClass(String classId) throws SQLException ;

    boolean updateClass(class2Dto dto) throws SQLException ;

    class2Dto searchClass(String classId) throws SQLException ;
    List<class2Dto> loadAllclassIds() throws SQLException ;*/
}
