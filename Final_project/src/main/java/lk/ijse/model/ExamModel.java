package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ExamDto;
import lk.ijse.dto.ResultDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamModel {
    public static String generateNxtExamId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT examId FROM Exams  ORDER BY examId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);
    }

    private static String ChangeId(String examId) {
        if (examId!= null) {
            String[] split = examId.split("E0");
            int id = Integer.parseInt(split[1]);
            id++;
            if(id<10){
                return "E00" + id;
            }else{
                return "E0"+id;
            }

        } else {
            return "E001";
        }
    }


    public boolean SaveExam(ExamDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Exams VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getDate());


        return pstm.executeUpdate() > 0;
    }

    public boolean UpdateExam(ExamDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE Exams SET name=?,date=? WHERE examId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getDate());

        pstm.setString(3, dto.getId());
        return pstm.executeUpdate() > 0;
    }


    public boolean deleteExam(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM Exams WHERE examId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public ExamDto searchStudent(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Exams WHERE examId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ExamDto dto = null;
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            dto = new ExamDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return dto;
    }

    public boolean SaveResult(ResultDto dto1) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO StudentResult VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto1.getStudentId());
        pstm.setString(2, dto1.getExamId());
        pstm.setDouble(3, dto1.getMark());


        return pstm.executeUpdate() > 0;
    }

    public List<ExamDto> getAllExam() throws SQLException {
        DbConnection dbConnection = DbConnection.getInstance();
        Connection connection = dbConnection.getConnection();
        String sql = "SELECT * FROM Exams";

            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            List<ExamDto> examList = new ArrayList<>();
            while (resultSet.next()) {

                examList.add(new ExamDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return examList;

    }

    public List<ResultDto> loadAllResult() throws SQLException {
        DbConnection dbConnection = DbConnection.getInstance();
        Connection connection = dbConnection.getConnection();
        String sql = "SELECT * FROM StudentResult";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<ResultDto> resultList = new ArrayList<>();
        while (resultSet.next()) {

            resultList.add(new ResultDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            ));
        }
        return resultList;

    }


}