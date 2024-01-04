package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ParentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ParentDAO extends CrudDAO<ParentDto> {
   //  boolean SaveStudent(ParentDto parentDto) throws SQLException ;

 //    ParentDto searchParent(String parentId) throws SQLException ;
}
