package lk.ijse.bao.custom;

import lk.ijse.dao.SuperDAO;

import java.sql.SQLException;

public interface DashboardBO extends SuperDAO {
    String searchTotalHall() throws SQLException;
    String searchTotalLecturer() throws SQLException ;
    String searchTotalStaff() throws SQLException ;
    String searchTotalStudent() throws SQLException;
}
