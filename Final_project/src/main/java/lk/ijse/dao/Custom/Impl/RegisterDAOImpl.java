package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.RegisterDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.RegistrationDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RegisterDAOImpl implements RegisterDAO {
    @Override
    public List<RegistrationDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(RegistrationDto registrationDto) throws SQLException {

        return SQLUtil.execute("INSERT INTO Registration VALUES(?,?,?,?,?,?)",registrationDto.getRegId(),registrationDto.getName(),registrationDto.getEmail(),registrationDto.getDate(),registrationDto.getParentId(),registrationDto.getUserName());
    }

    @Override
    public boolean update(RegistrationDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String ChangeId(String subId) {
        return null;
    }

    @Override
    public RegistrationDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
