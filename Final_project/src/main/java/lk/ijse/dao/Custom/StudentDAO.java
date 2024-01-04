package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.studentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentDAO extends CrudDAO<studentDto> {
    /* List<studentDto> loadAllStudent() throws SQLException ;

    boolean SaveStudent(String id, String name, String address, String email, int contactNo, String gender, String dateOfBirth) throws SQLException ;

    studentDto searchStudent(String id) throws SQLException ;

    boolean updateStudent(studentDto dto) throws SQLException ;
     boolean deleteStudent(String id) throws SQLException ;

    String generateNextRegId() throws SQLException ;

     String ChangeId(String stuId) ;*/



    String searchTotalStudent() throws SQLException;


}
