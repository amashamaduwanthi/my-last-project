package lk.ijse.dao.Custom.Impl;

import lk.ijse.Entity.Parent;
import lk.ijse.dao.Custom.ParentDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ParentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ParentDAOImpl implements ParentDAO {
    @Override
    public List<Parent> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
@Override
    public boolean save(Parent entity) throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Parent VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        pstm.setString(2,name);
        pstm.setInt(3, contactNo);
        pstm.setString(4, designation);

        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("INSERT INTO Parent VALUES(?,?,?,?)",entity.getParentId(),entity.getParentName(),entity.getParentContactNo(),entity.getStuId());

    }

    @Override
    public boolean update(Parent dto) throws SQLException, ClassNotFoundException {
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

    public Parent search(String parentId) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Parent WHERE parentId=? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,parentId);
        ParentDto dto=null;
        ResultSet resultSet = pstm.executeQuery();*/
        Parent entity=null;
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Parent WHERE parentId=? ",parentId);
        if(resultSet.next()){
            entity=new Parent(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4));
        }
         return entity;
    }
}
