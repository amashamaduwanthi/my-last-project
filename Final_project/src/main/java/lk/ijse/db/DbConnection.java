package lk.ijse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection connection;
    private static DbConnection dbConnection;
    private DbConnection() throws SQLException {
       connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/ThigmaSmartLearn","root","Ijse@1234");
    }
   public static DbConnection getInstance() throws SQLException {
        return (null==dbConnection?dbConnection=new DbConnection():dbConnection);
   }
   public Connection getConnection(){
        return connection;
   }
}
