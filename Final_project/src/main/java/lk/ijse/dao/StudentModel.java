package lk.ijse.dao;

import lk.ijse.dao.Custom.Impl.ParentDAOImpl;
import lk.ijse.dao.Custom.Impl.RegisterDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.dao.Custom.ParentDAO;
import lk.ijse.dao.Custom.RegisterDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ParentDto;
import lk.ijse.dto.RegistrationDto;
import lk.ijse.dto.studentDto;

import java.sql.Connection;
import java.sql.SQLException;

public class StudentModel {
 /*   public static String searchTotalStudent() throws SQLException {
        String count="0";
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM Student;";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                count=resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }*/
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
         StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
         boolean isSaved = studentDAOImpl.save(dto);
         System.out.println(isSaved);
         if (isSaved) {
             ParentDAO parentDAO = new ParentDAOImpl();
             boolean isAdded = parentDAO.save(parentDto);
             System.out.println(isAdded);
             if (isAdded) {
                 RegisterDAO registerDAO = new RegisterDAOImpl();
                 boolean isRegistered = registerDAO.save(registrationDto);
                 System.out.println(isRegistered);
                 if (isRegistered) {
                     connection.commit();
                     isOk= true;
                 }
             }
         }

     }catch (SQLException | ClassNotFoundException e) {
         connection.rollback();
     }  finally{
         connection.setAutoCommit(true);

     }
     return isOk;
 }
}
