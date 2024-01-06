package lk.ijse.bao.custom.impl;

import lk.ijse.bao.custom.StudentClassBO;
import lk.ijse.dao.Custom.ClassSubjectDetailsDAO;
import lk.ijse.dao.Custom.Impl.ClassSubjectDetailsDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentClassDAOImpl;
import lk.ijse.dao.Custom.StudentClassDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.StudentClassDto;

import java.sql.SQLException;
import java.util.List;

public class StudentClassRegistrationBOImpl implements StudentClassBO {
    StudentClassDAO studentClassDAO= (StudentClassDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENTCLASS);
    ClassSubjectDetailsDAO classSubjectDetailsDAOImpl= (ClassSubjectDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CLASSSUBJECTDETAILS);

    @Override
    public List<StudentClassDto> loadAllDetails() throws SQLException, ClassNotFoundException {
        return studentClassDAO.getAll();
    }

    @Override
    public StudentClassDto searchStudentClass(String id) throws SQLException, ClassNotFoundException {
        return studentClassDAO.search(id);
    }

    @Override
    public boolean addClass(String classId, String id, String subName, String lectName) throws SQLException {
        return classSubjectDetailsDAOImpl.addClass(classId, id, subName, lectName);
    }
}
