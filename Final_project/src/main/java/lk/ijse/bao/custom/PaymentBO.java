package lk.ijse.bao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.PayementDto;
import lk.ijse.dto.class2Dto;
import lk.ijse.dto.studentDto;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperDAO {
    String generateNxtPaymentId() throws SQLException, ClassNotFoundException;

    String ChangeId(String classfeeId);

    boolean savePayment(PayementDto dto) throws SQLException, ClassNotFoundException;

    boolean updatePayment(PayementDto dto) throws SQLException, ClassNotFoundException;

    PayementDto getPayment(String id) throws SQLException, ClassNotFoundException;

    List<PayementDto> getAllPayment() throws SQLException, ClassNotFoundException;
    studentDto searchStudent(String id) throws SQLException, ClassNotFoundException;
    List<studentDto> loadAllStudent() throws SQLException, ClassNotFoundException;
    List<class2Dto> loadAllclassIds() throws SQLException ;
}
