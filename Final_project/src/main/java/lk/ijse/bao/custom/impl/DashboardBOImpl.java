package lk.ijse.bao.custom.impl;

import lk.ijse.bao.custom.DashboardBO;
import lk.ijse.dao.Custom.AdminDAO;
import lk.ijse.dao.Custom.HallDAO;
import lk.ijse.dao.Custom.Impl.AdminDAOImpl;
import lk.ijse.dao.Custom.Impl.HallDAOImpl;
import lk.ijse.dao.Custom.Impl.LecturerDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.dao.Custom.LecturerDAO;
import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.dao.DAOFactory;

import java.sql.SQLException;

public class DashboardBOImpl implements DashboardBO {
    AdminDAO adminDAOImpl= (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ADMIN);
    HallDAO hallDAOImpl= (HallDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.HALL);
    private LecturerDAO lecturerDAOImpl=new LecturerDAOImpl();
    StudentDAO studentDAOImpl= new StudentDAOImpl();
    @Override
    public String searchTotalHall() throws SQLException {
        return hallDAOImpl.searchTotalHall();
    }

    @Override
    public String searchTotalLecturer() throws SQLException {
        return lecturerDAOImpl.searchTotalLecturer();
    }

    @Override
    public String searchTotalStaff() throws SQLException {
        return adminDAOImpl.searchTotalStaff();
    }

    @Override
    public String searchTotalStudent() throws SQLException {
        return studentDAOImpl.searchTotalStudent();
    }
}
