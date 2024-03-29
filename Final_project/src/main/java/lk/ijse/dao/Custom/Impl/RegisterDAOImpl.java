package lk.ijse.dao.Custom.Impl;

import lk.ijse.Entity.Registration;
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
    public List<Registration> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Registration entity) throws SQLException {

        return SQLUtil.execute("INSERT INTO Registration VALUES(?,?,?,?,?,?)",entity.getRegId(),entity.getName(),entity.getEmail(),entity.getDate(),entity.getParentId(),entity.getUserName());
    }

    @Override
    public boolean update(Registration entity) throws SQLException, ClassNotFoundException {
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
    public Registration search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
