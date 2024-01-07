package lk.ijse.dao.Custom;

import lk.ijse.Entity.Hall;
import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.HallDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface HallDAO extends CrudDAO<Hall> {
    String searchTotalHall() throws SQLException ;

    //List<HallDto> loadAllHallIds() throws SQLException ;

   //  HallDto searchHall(String id) throws SQLException ;

}
