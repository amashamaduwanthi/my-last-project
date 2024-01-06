package lk.ijse.bao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.AdminDto;

import java.sql.SQLException;
import java.util.List;

public interface StaffBo extends SuperDAO {
    boolean deleteAdmin(String userName) throws SQLException, ClassNotFoundException;

    AdminDto searchAdmin(String userName) throws SQLException, ClassNotFoundException;

    boolean updateAdmin(AdminDto dto) throws SQLException, ClassNotFoundException;
    List<AdminDto> loadAllType() throws SQLException, ClassNotFoundException;
    boolean SaveAdmin(AdminDto dto) throws SQLException, ClassNotFoundException;
    AdminDto checkLogin(String name, String password,String type) throws SQLException ;
}
