package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.ClassSubjectDetailsDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClassSubjectDetailsDAOImpl implements ClassSubjectDetailsDAO {
    @Override
    public boolean addClass(String classId, String id, String subName, String lectName) throws SQLException {
     /*   Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO studentClassDetails VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, classId);
        pstm.setString(2, id);
        pstm.setString(3, subName);
        pstm.setString(4, lectName);
        return pstm.executeUpdate() > 0;
    }*/
        return SQLUtil.execute("INSERT INTO studentClassDetails VALUES(?,?,?,?)", classId, id, subName, lectName);
    }

    @Override
    public List getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Object dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Object dto) throws SQLException, ClassNotFoundException {
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
    public Object search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}