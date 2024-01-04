package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.AdminDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.AdminDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public  String searchTotalStaff() throws SQLException {
        String count="0";
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM Admin;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet= SQLUtil.execute("SELECT COUNT(*) FROM Admin;");
        if(resultSet.next()){
            count=resultSet.getString(1);
        }
        return count;

    }
    @Override

    public boolean save(AdminDto dto) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO Admin VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getUsername());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getType());

        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("INSERT INTO Admin VALUES(?,?,?,?)",dto.getUsername(),dto.getPassword(),dto.getEmail(),dto.getType());
    }
    @Override

    public AdminDto checkLogin(String name, String password,String type) throws SQLException {
    /*    Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Admin WHERE userName=? AND password=? AND type=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,name);
        pstm.setString(2,password);
        pstm.setString(3,type);

        ResultSet resultSet = pstm.executeQuery();*/
        AdminDto dto=null;
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Admin WHERE userName=? AND password=? AND type=?",name,password,type);
        if(resultSet.next()){
            String username = resultSet.getString(1);
            String pw = resultSet.getString(2);
            String email = resultSet.getString(3);
            String t = resultSet.getString(4);

           dto= new AdminDto(username,pw,type);
        }
        return dto;
    }
    @Override

    public boolean delete(String userName) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM Admin WHERE userName=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,userName);
        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("DELETE FROM Admin WHERE userName=?",userName);
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

    public AdminDto search(String userName) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Admin WHERE userName=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet resultSet = pstm.executeQuery();*/
        AdminDto dto=null;
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Admin WHERE userName=?",userName);
        if(resultSet.next()){
            dto=new AdminDto(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
        }
        return dto;
    }
    @Override

    public boolean update(AdminDto dto) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE Admin SET userName=?,password=?,email=?,type=? WHERE userName=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getUsername());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getType());
        pstm.setString(5, dto.getUsername());
        return pstm.executeUpdate()>0;*/
        return SQLUtil.execute("UPDATE Admin SET userName=?,password=?,email=?,type=? WHERE userName=?",dto.getUsername(),dto.getPassword(),dto.getEmail(),dto.getType(),dto.getUsername());
    }
@Override
    public List<AdminDto> getAll() throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM Admin";
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        List<AdminDto> adminDtos = new ArrayList<>();
        ResultSet resultSet =SQLUtil.execute("SELECT * FROM Admin");
        while (resultSet.next()){
            adminDtos.add(new AdminDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return adminDtos;
    }

}

