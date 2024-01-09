package lk.ijse.dao.Custom.Impl;

import lk.ijse.Entity.SubjectLecturer;
import lk.ijse.dao.Custom.LecturerSubjectDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SubjectLecturerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LecturerSubjectDAOImpl implements LecturerSubjectDAO {
    @Override
    public List<SubjectLecturer> getAll() throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM lecSubDetails";
        PreparedStatement pstm = connection.prepareStatement(sql);
        List<SubjectLecturerDto> subjectLecturerDtos = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();*/
        List<SubjectLecturer> subjectLecturer = new ArrayList<>();
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM lecSubDetails");
        while (resultSet.next()){
            subjectLecturer.add(new SubjectLecturer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return subjectLecturer;
    }

    @Override
    public boolean save(SubjectLecturer entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO lecSubDetails VALUES(?,?,?,?)",entity.getLecId(),entity.getLecName(),entity.getSubId(),entity.getSubName());
    }

    @Override
    public boolean update(SubjectLecturer dto) throws SQLException, ClassNotFoundException {
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
    public SubjectLecturer search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
