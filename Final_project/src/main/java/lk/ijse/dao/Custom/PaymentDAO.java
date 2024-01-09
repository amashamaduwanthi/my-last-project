package lk.ijse.dao.Custom;

import lk.ijse.Entity.Payment;
import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.PayementDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
  /*  String generateNxtPaymentId() throws SQLException;

    String ChangeId(String classfeeId);

    boolean savePayment(PayementDto dto) throws SQLException;

    boolean updatePayment(PayementDto dto) throws SQLException;

    PayementDto getPayment(String id) throws SQLException;

    List<PayementDto> getAllPayment() throws SQLException;*/
}
