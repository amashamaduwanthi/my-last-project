package lk.ijse.bao.custom.impl;

import lk.ijse.bao.custom.StaffBo;
import lk.ijse.dao.Custom.AdminDAO;
import lk.ijse.dao.Custom.Impl.AdminDAOImpl;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.AdminDto;

import java.sql.SQLException;
import java.util.List;


public class StaffBOImpl implements StaffBo {
    AdminDAO adminDAOImpl= (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ADMIN);
    @Override
    public boolean deleteAdmin(String userName) throws SQLException, ClassNotFoundException {
        return adminDAOImpl.delete(userName);
    }

    @Override
    public AdminDto searchAdmin(String userName) throws SQLException, ClassNotFoundException {
        return adminDAOImpl.search(userName);
    }

    @Override
    public boolean updateAdmin(AdminDto dto) throws SQLException, ClassNotFoundException {
        return adminDAOImpl.update(dto);
    }

    @Override
    public List<AdminDto> loadAllType() throws SQLException, ClassNotFoundException {
        return adminDAOImpl.getAll();
    }

    @Override
    public boolean SaveAdmin(AdminDto dto) throws SQLException, ClassNotFoundException {
        return adminDAOImpl.save(dto);
    }

    @Override
    public AdminDto checkLogin(String name, String password, String type) throws SQLException {
        return adminDAOImpl.checkLogin(name, password, type);
    }
}
