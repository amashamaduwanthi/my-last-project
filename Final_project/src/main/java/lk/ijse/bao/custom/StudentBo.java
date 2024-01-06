package lk.ijse.bao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.ParentDto;
import lk.ijse.dto.RegistrationDto;
import lk.ijse.dto.studentDto;

import java.sql.SQLException;
import java.util.List;

public interface StudentBo extends SuperDAO {
    List<studentDto> loadAllStudent() throws SQLException, ClassNotFoundException;

    boolean SaveStudent(String id, String name, String address, String email, int contactNo, String gender, String dateOfBirth) throws SQLException, ClassNotFoundException;

    studentDto searchStudent(String id) throws SQLException, ClassNotFoundException;

    boolean updateStudent(studentDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;

    String generateNextRegId() throws SQLException, ClassNotFoundException;

    String ChangeId(String stuId) ;
    boolean setStudent(studentDto dto, ParentDto parentDto, RegistrationDto registrationDto) throws SQLException;
    ParentDto searchParent(String parentId) throws SQLException, ClassNotFoundException;

}
