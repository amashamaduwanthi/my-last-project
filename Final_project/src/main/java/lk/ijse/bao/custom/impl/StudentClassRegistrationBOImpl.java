package lk.ijse.bao.custom.impl;

import lk.ijse.Entity.Payment;
import lk.ijse.Entity.StudentClass;
import lk.ijse.bao.custom.StudentClassBO;
import lk.ijse.dao.Custom.ClassSubjectDetailsDAO;
import lk.ijse.dao.Custom.Impl.ClassSubjectDetailsDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentClassDAOImpl;
import lk.ijse.dao.Custom.StudentClassDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.PayementDto;
import lk.ijse.dto.StudentClassDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentClassRegistrationBOImpl implements StudentClassBO {
    StudentClassDAO studentClassDAO= (StudentClassDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENTCLASS);
    ClassSubjectDetailsDAO classSubjectDetailsDAOImpl= (ClassSubjectDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CLASSSUBJECTDETAILS);

    @Override
    public List<StudentClassDto> loadAllDetails() throws SQLException, ClassNotFoundException {
        List<StudentClass> all = studentClassDAO.getAll();
        List<StudentClassDto> studentClassDtos = new ArrayList<>();

        for (StudentClass studentClass : all) {
            studentClassDtos.add(new StudentClassDto(studentClass.getId(),
                    studentClass.getClassId(),studentClass.getSubName(),studentClass.getLecName()));


        }
        return studentClassDtos;
    }


    @Override
    public StudentClassDto searchStudentClass(String id) throws SQLException, ClassNotFoundException {
        StudentClass search= studentClassDAO.search(id);
        return  new StudentClassDto(search.getId(),search.getClassId(),search.getSubName(),search.getLecName());
    }

    @Override
    public boolean addClass(String classId, String id, String subName, String lectName) throws SQLException {
        return classSubjectDetailsDAOImpl.addClass(classId, id, subName, lectName);
    }
}
