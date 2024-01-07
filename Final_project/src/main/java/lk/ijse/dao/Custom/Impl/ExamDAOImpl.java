package lk.ijse.dao.Custom.Impl;

import lk.ijse.Entity.Exam;
import lk.ijse.dao.Custom.ExamDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ExamDto;
import lk.ijse.dto.ResultDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamDAOImpl implements ExamDAO {
    @Override
    public  String generateNextId() throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT examId FROM Exams  ORDER BY examId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet= SQLUtil.execute("SELECT examId FROM Exams  ORDER BY examId DESC LIMIT 1 ");
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);

    }
    @Override

   public   String ChangeId(String examId) {
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
@Override
   public boolean save(Exam entity) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Exams VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getDate());


        return pstm.executeUpdate() > 0;*/
    return SQLUtil.execute("INSERT INTO Exams VALUES(?,?,?)",entity.getId(),entity.getName(),entity.getDate());
    }
@Override
    public boolean update(Exam entity) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE Exams SET name=?,date=? WHERE examId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getDate());

        pstm.setString(3, dto.getId());
        return pstm.executeUpdate() > 0;*/
    return SQLUtil.execute("UPDATE Exams SET name=?,date=? WHERE examId=?",entity.getName(),entity.getDate(),entity.getId());
    }




    @Override
    public boolean delete(String id) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM Exams WHERE examId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;*/
    return SQLUtil.execute( "DELETE FROM Exams WHERE examId=?",id);
    }

    public Exam search(String id) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Exams WHERE examId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();*/
        Exam entity = null;
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM Exams WHERE examId=?",id);
        if (resultSet.next()) {
           return new Exam(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }else {
            return null;
        }

    }
@Override
    public boolean SaveResult(ResultDto dto1) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO StudentResult VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto1.getStudentId());
        pstm.setString(2, dto1.getExamId());
        pstm.setDouble(3, dto1.getMark());


        return pstm.executeUpdate() > 0;*/
    return SQLUtil.execute("INSERT INTO StudentResult VALUES(?,?,?)",dto1.getStudentId(),dto1.getExamId(),dto1.getMark());
    }
@Override
    public List<Exam> getAll() throws SQLException {
      /*  DbConnection dbConnection = DbConnection.getInstance();
        Connection connection = dbConnection.getConnection();
        String sql = "SELECT * FROM Exams";

            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();*/
    ResultSet resultSet=SQLUtil.execute("SELECT * FROM Exams");
            List<Exam> examList = new ArrayList<>();
            while (resultSet.next()) {

                examList.add(new Exam(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return examList;

    }
@Override
    public List<ResultDto> loadAllResult() throws SQLException {
       /* DbConnection dbConnection = DbConnection.getInstance();
        Connection connection = dbConnection.getConnection();
        String sql = "SELECT * FROM StudentResult";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM StudentResult");
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