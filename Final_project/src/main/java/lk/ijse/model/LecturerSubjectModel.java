package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.SubjectLecturerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LecturerSubjectModel {
    public List<SubjectLecturerDto> loadAllSubjectLecturer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM lecSubDetails";
        PreparedStatement pstm = connection.prepareStatement(sql);
        List<SubjectLecturerDto> subjectLecturerDtos = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            subjectLecturerDtos.add(new SubjectLecturerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return subjectLecturerDtos;
    }
}
