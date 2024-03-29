package lk.ijse.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    List<T>getAll() throws SQLException, ClassNotFoundException ;
    boolean save(T dto) throws SQLException, ClassNotFoundException ;
    boolean update(T dto) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    String generateNextId() throws SQLException, ClassNotFoundException ;

    String ChangeId(String subId) ;

    T search(String id) throws SQLException, ClassNotFoundException;
}
