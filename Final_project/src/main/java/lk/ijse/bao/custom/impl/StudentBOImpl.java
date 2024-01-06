package lk.ijse.bao.custom.impl;

import lk.ijse.bao.custom.StudentBo;
import lk.ijse.dao.Custom.Impl.ParentDAOImpl;
import lk.ijse.dao.Custom.Impl.RegisterDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.dao.Custom.ParentDAO;
import lk.ijse.dao.Custom.RegisterDAO;
import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ParentDto;
import lk.ijse.dto.RegistrationDto;
import lk.ijse.dto.studentDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentBOImpl implements StudentBo {
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
    ParentDAO parentDAO = (ParentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PARENT);
    RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.REGISTER);
    @Override
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

            boolean isSaved = studentDAO.save(dto);
            System.out.println(isSaved);
            if (isSaved) {

                boolean isAdded = parentDAO.save(parentDto);
                System.out.println(isAdded);
                if (isAdded) {

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

    @Override
    public ParentDto searchParent(String parentId) throws SQLException, ClassNotFoundException {
        return parentDAO.search(parentId);
    }

    @Override
    public List<studentDto> loadAllStudent() throws SQLException, ClassNotFoundException {
        return studentDAO.getAll();
    }

    @Override
    public boolean SaveStudent(String id, String name, String address, String email, int contactNo, String gender, String dateOfBirth) throws SQLException, ClassNotFoundException {
        studentDto dto = new studentDto(id,name,address,email,contactNo,gender,dateOfBirth);
        return studentDAO.save(dto);
    }

    @Override
    public studentDto searchStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.search(id);
    }

    @Override
    public boolean updateStudent(studentDto dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update(dto);
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

    @Override
    public String generateNextRegId() throws SQLException, ClassNotFoundException {
        return studentDAO.generateNextId();
    }

    @Override
    public String ChangeId(String stuId) {
        return studentDAO.ChangeId(stuId);
    }
}
