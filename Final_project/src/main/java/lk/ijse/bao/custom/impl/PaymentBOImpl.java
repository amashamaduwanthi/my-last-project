package lk.ijse.bao.custom.impl;

import lk.ijse.Entity.*;
import lk.ijse.bao.custom.PaymentBO;
import lk.ijse.dao.Custom.Impl.ClassDAOImpl;
import lk.ijse.dao.Custom.Impl.PaymentDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.dao.Custom.PaymentDAO;
import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;
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
        return paymentDAOImpl.save(new Payment(dto.getId(),dto.getAmount(),dto.getDate(),dto.getStatus(),dto.getClassId(),dto.getStuId()));
    }

    @Override
    public boolean updatePayment(PayementDto dto) throws SQLException, ClassNotFoundException {
        return paymentDAOImpl.update(new Payment(dto.getId(),dto.getAmount(),dto.getDate(),dto.getStatus(),dto.getClassId(),dto.getStuId()));
    }

    @Override
    public PayementDto getPayment(String id) throws SQLException, ClassNotFoundException {
        Payment search= paymentDAOImpl.search(id);
       return new PayementDto(search.getId(),search.getAmount(),search.getDate(),search.getStatus(),search.getClassId(),search.getStuId());
    }

    @Override
    public List<PayementDto> getAllPayment() throws SQLException, ClassNotFoundException {
        List<Payment> all = paymentDAOImpl.getAll();
        List<PayementDto> payementDtos = new ArrayList<>();

        for (Payment payment : all) {
            payementDtos.add(new PayementDto(payment.getId(),
                  payment.getAmount(),payment.getDate(),payment.getStatus(),payment.getClassId(),payment.getStuId()));


        }
        return payementDtos;
    }


    @Override
    public studentDto searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student search= studentDAO.search(id);
        return new studentDto(search.getId(),search.getName(),search.getAddress(),search.getEmail(),search.getContactNo(),search.getGender(),search.getDateOfBirth());
    }

    @Override
    public List<studentDto> loadAllStudent() throws SQLException, ClassNotFoundException {
        List<Student> all = studentDAO.getAll();
        List<studentDto>studentDtos = new ArrayList<>();

        for (Student student : all) {
            studentDtos.add(new studentDto(
                    student.getId(),
                    student.getName(),
                    student.getAddress(),
                    student.getEmail(),
                    student.getContactNo(),
                    student.getGender(),
                    student.getDateOfBirth()));
        }
        return studentDtos;
    }

    @Override
    public List<class2Dto> loadAllclassIds() throws SQLException {

            List<Classes> all = classDAOImpl.getAll();
            List<class2Dto> class2Dtos = new ArrayList<>();

            for (Classes classes : all) {
                class2Dtos.add(new class2Dto(
                        classes.getId(),
                        classes.getGrade()));
            }
            return class2Dtos;
        }

    }

