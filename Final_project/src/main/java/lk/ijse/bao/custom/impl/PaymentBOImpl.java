package lk.ijse.bao.custom.impl;

import lk.ijse.bao.custom.PaymentBO;
import lk.ijse.dao.Custom.Impl.ClassDAOImpl;
import lk.ijse.dao.Custom.Impl.PaymentDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.dao.Custom.PaymentDAO;
import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.PayementDto;
import lk.ijse.dto.class2Dto;
import lk.ijse.dto.studentDto;

import java.sql.SQLException;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAOImpl= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT);
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
    ClassDAOImpl classDAOImpl= (ClassDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CLASS);

    @Override
    public String generateNxtPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAOImpl.generateNextId();
    }

    @Override
    public String ChangeId(String classfeeId) {
        return paymentDAOImpl.ChangeId(classfeeId);
    }

    @Override
    public boolean savePayment(PayementDto dto) throws SQLException, ClassNotFoundException {
        return paymentDAOImpl.save(dto);
    }

    @Override
    public boolean updatePayment(PayementDto dto) throws SQLException, ClassNotFoundException {
        return paymentDAOImpl.update(dto);
    }

    @Override
    public PayementDto getPayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAOImpl.search(id);
    }

    @Override
    public List<PayementDto> getAllPayment() throws SQLException, ClassNotFoundException {
        return paymentDAOImpl.getAll();
    }

    @Override
    public studentDto searchStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.search(id);
    }

    @Override
    public List<studentDto> loadAllStudent() throws SQLException, ClassNotFoundException {
        return studentDAO.getAll();
    }

    @Override
    public List<class2Dto> loadAllclassIds() throws SQLException {
        return classDAOImpl.getAll();
    }
}
