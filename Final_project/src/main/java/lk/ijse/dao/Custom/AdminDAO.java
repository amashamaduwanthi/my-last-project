package lk.ijse.dao.Custom;

import lk.ijse.Entity.Admin;
import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.AdminDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AdminDAO extends CrudDAO<Admin> {
    String searchTotalStaff() throws SQLException ;
    AdminDto checkLogin(String name, String password,String type) throws SQLException ;

   // boolean SaveAdmin(AdminDto dto) throws SQLException ;



  /*  boolean deleteAdmin(String userName) throws SQLException ;

    AdminDto searchAdmin(String userName) throws SQLException ;

    boolean updateAdmin(AdminDto dto) throws SQLException ;
    List<AdminDto> loadAllType() throws SQLException ;*/
}
