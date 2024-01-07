package lk.ijse.bao.custom.impl;

import lk.ijse.Entity.Admin;
import lk.ijse.bao.custom.StaffBo;
import lk.ijse.dao.Custom.AdminDAO;
import lk.ijse.dao.Custom.Impl.AdminDAOImpl;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.AdminDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StaffBOImpl implements StaffBo {
    AdminDAO adminDAOImpl= (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ADMIN);
    @Override
    public boolean deleteAdmin(String userName) throws SQLException, ClassNotFoundException {
        return adminDAOImpl.delete(userName);
    }

    @Override
    public AdminDto searchAdmin(String userName) throws SQLException, ClassNotFoundException {
       Admin search= adminDAOImpl.search(userName);
       return new AdminDto(search.getUsername(),search.getPassword(),search.getEmail(),search.getType());
    }

    @Override
    public boolean updateAdmin(AdminDto dto) throws SQLException, ClassNotFoundException {
        return adminDAOImpl.update(new Admin(dto.getUsername(),dto.getPassword(),dto.getEmail(),dto.getType()));
    }

    @Override
    public List<AdminDto> loadAllType() throws SQLException, ClassNotFoundException {
        List<Admin> all = adminDAOImpl.getAll();
        List<AdminDto> adminDtos = new ArrayList<>();

        for (Admin admin : all) {
            adminDtos.add(new AdminDto(
                    admin.getUsername(),
                    admin.getPassword(),
                    admin.getEmail(),
                    admin.getType()));
        }
        return adminDtos;
    }

    @Override
    public boolean SaveAdmin(AdminDto dto) throws SQLException, ClassNotFoundException {
        return adminDAOImpl.save(new Admin(dto.getUsername(),dto.getPassword(),dto.getEmail(),dto.getType()));
    }

    @Override
    public AdminDto checkLogin(String name, String password, String type) throws SQLException {
        return adminDAOImpl.checkLogin(name, password, type);
    }
}
