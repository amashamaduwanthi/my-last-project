package lk.ijse.dao.Custom;

import lk.ijse.Entity.StudentClass;
import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.StudentClassDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentClassDAO extends CrudDAO<StudentClass> {
  /*  List<StudentClassDto> loadAllDetails() throws SQLException ;

     StudentClassDto searchStudentClass(String id) throws SQLException */;
}
