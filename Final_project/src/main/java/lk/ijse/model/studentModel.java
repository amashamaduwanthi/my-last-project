package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ParentDto;
import lk.ijse.dto.RegistrationDto;
import lk.ijse.dto.studentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class studentModel {

    public static List<studentDto> loadAllStudent() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Student";
        PreparedStatement pstm = connection.prepareStatement(sql);
        List<studentDto> studentDtos = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            studentDtos.add(new studentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return studentDtos;
    }

    public boolean SaveStudent(String id, String name, String address, String email, int contactNo, String gender, String dateOfBirth) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Student VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setString(2, name);
        pstm.setString(3, address);
        pstm.setString(4, email);
        pstm.setInt(5, contactNo);
        pstm.setString(6, gender);
        pstm.setString(7, dateOfBirth);

        return pstm.executeUpdate() > 0;
    }

    public static studentDto searchStudent(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Student WHERE stuId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        studentDto dto = null;
        if (resultSet.next()) {
            String stu_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String email = resultSet.getString(4);
            int contactNo = Integer.parseInt(resultSet.getString(5));
            String gender = resultSet.getString(6);
            String dateOfBirth = resultSet.getString(7);

            dto = new studentDto(stu_id, name, address, email, contactNo, gender, dateOfBirth);

        }
        return dto;
    }

    public boolean updateStudent(studentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE Student SET name=?,address=?,email=? ,contactNo=?,gender=?,dateOfBirth=?WHERE stuId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getEmail());
        pstm.setInt(4, dto.getContactNo());
        pstm.setString(5, dto.getGender());
        pstm.setString(6, dto.getDateOfBirth());
        pstm.setString(7, dto.getId());
        return pstm.executeUpdate() > 0;
    }

    public boolean deleteStudent(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM Student WHERE stuId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;

    }

    public String generateNextRegId() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT stuId FROM Student  ORDER BY stuId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return ChangeId(resultSet.getString(1));
        }
        return ChangeId(null);
    }

    private String ChangeId(String stuId) {
        if (stuId != null) {
            String[] split = stuId.split("S0");
            int id = Integer.parseInt(split[1]);
            id++;
            if(id<10){
                return "S00" + id;
            }else{
                return "S0"+id;
            }

        } else {
            return "S001";
        }
    }


    public boolean setStudent(studentDto dto, ParentDto parentDto, RegistrationDto registrationDto) throws SQLException {
        System.out.println(dto);
        System.out.println(parentDto);
        System.out.println(registrationDto);

        String id = dto.getId();
        String name = dto.getName();
        String address = dto.getAddress();
        String email = dto.getEmail();
        int contactNo = dto.getContactNo();
        String gender = dto.getGender();
        String dateOfBirth = dto.getDateOfBirth();

        boolean isOk = false;

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSaved = SaveStudent(id, name, address, email, contactNo, gender, dateOfBirth);
            System.out.println(isSaved);
            if (isSaved) {
                var parentModel = new ParentModel();
                boolean isAdded = parentModel.SaveStudent(parentDto.getParentId(), parentDto.getParentName(), parentDto.getParentContactNo(), parentDto.getStuId());
                System.out.println(isAdded);
                if (isAdded) {
                    var registrationModel = new RegisterMode();
                    boolean isRegistered = registrationModel.saveRegistration(registrationDto.getRegId(), registrationDto.getName(), registrationDto.getEmail(), registrationDto.getDate(), registrationDto.getParentId(),registrationDto.getUserName());
                    System.out.println(isRegistered);
                    if (isRegistered) {
                        connection.commit();
                        isOk= true;
                    }
                }
            }

            }catch (SQLException e) {
            connection.rollback();
        }  finally{
                connection.setAutoCommit(true);

            }
            return isOk;
        }
    }

