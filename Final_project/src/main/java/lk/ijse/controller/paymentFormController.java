package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bao.custom.BOFactory;
import lk.ijse.bao.custom.PaymentBO;
import lk.ijse.bao.custom.impl.PaymentBOImpl;
import lk.ijse.dao.Custom.PaymentDAO;
import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.PayementDto;
import lk.ijse.dto.TM.PayementTm;
import lk.ijse.dto.class2Dto;
import lk.ijse.dto.studentDto;
import lk.ijse.dao.Custom.Impl.ClassDAOImpl;
import lk.ijse.dao.Custom.Impl.PaymentDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class paymentFormController {
    public TextField txtId;
    public TextField txtAmount;
    public TextField txtDate;
    public TextField txtStatus;
    public JFXComboBox cmbClassID;
    public JFXComboBox cmbStuId;
    public Label lblStudentName;
    public TableView<PayementTm> tblPayment;
    public TableColumn<?,?> colDate;

    public TableColumn<?,?> colStatus;
    public TableColumn<?,?> colClassId;
    public TableColumn<?,?> colPId;
    public TableColumn<?,?> colAmount;
    public Label lblPaymentId;
    public TableColumn<?,?> colStuId;
    public TextField txtPaymentId;
   PaymentBO paymentBO= (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.PAYMENT);



    public void initialize() throws ClassNotFoundException {
        loadAllClassId();
        loadAllAtudentIds();
        loadAllPayments();
        setCellValueFactory();
        generatePaymentId();
    }

    private void generatePaymentId() {
        try{
            String lecId= paymentBO.generateNxtPaymentId();
            lblPaymentId.setText(lecId);

        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {

        colPId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colStuId.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        loadAllPayments();
    }

    private void loadAllPayments() {
        ObservableList<PayementTm> obList = FXCollections.observableArrayList();

        try {
         List<PayementDto> payementDtos =   paymentBO.getAllPayment();
            for(PayementDto dto:payementDtos){
                obList.add(new PayementTm(dto.getId(),dto.getAmount(),dto.getDate(),dto.getStatus(),dto.getClassId(),dto.getStuId()));
            }
            tblPayment.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllAtudentIds() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<studentDto> studentDtos = paymentBO.loadAllStudent();
            for(studentDto dto:studentDtos){
                obList.add(dto.getId());
            }
            cmbStuId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllClassId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<class2Dto> class2Dtos = paymentBO.loadAllclassIds();
            for(class2Dto dto:class2Dtos){
                obList.add(dto.getId());
            }cmbClassID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnAddPayementOnAction(ActionEvent actionEvent) {
        String id = lblPaymentId.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String date = txtDate.getText();
        String status = txtStatus.getText();
        String classId = (String) cmbClassID.getValue();
        String stuId = (String) cmbStuId.getValue();
        boolean isVlidated = validatePayment();
        if(isVlidated){

        var dto = new PayementDto(id, amount, date, status, classId, stuId);

        try {
            boolean isSaved = paymentBO.savePayment(dto);
            if (isSaved) {
                clearField();
                loadAllPayments();
                generatePaymentId();
                new Alert(Alert.AlertType.CONFIRMATION, "payment Successfully").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    }

    private boolean validatePayment() {
        boolean ismatch;
        String amount = txtAmount.getText();
        ismatch= Pattern.compile("^[0-9]+\\.?[0-9]*$").matcher(amount).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid Amount !!!").show();
            return false;
        }

        String status = txtStatus.getText();
        ismatch= Pattern.compile("[A-Za-z]{4,}").matcher(status).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid status !!!").show();
            return false;
        }

        String classId= (String) cmbClassID.getValue();
        if(classId==null) {
            new Alert(Alert.AlertType.ERROR, "Enter the class id !!!").show();
            return false;
        }
        return true;
    }

    private void clearField() {
        lblPaymentId.setText("");
        txtAmount.setText("");
        txtDate.setText("");
        txtStatus.setText("");

    }

    public void btnUpdatePaymentOnAction(ActionEvent actionEvent) {
        String id=lblPaymentId.getText();
        double amount= Double.parseDouble(txtAmount.getText());
        String date=txtDate.getText();
        String status=txtStatus.getText();
        String classId=(String)cmbClassID.getValue();
        String stuId= (String) cmbStuId.getValue();
        var dto=new PayementDto(id,amount,date,status,classId,stuId);

        try {
            boolean isUpdate=paymentBO.updatePayment(dto);
            if(isUpdate){

                new Alert(Alert.AlertType.CONFIRMATION,"updated successfully").show();
                clearField();
                loadAllPayments();
                generatePaymentId();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void cmbStudentOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String id= (String) cmbStuId.getValue();

        try {
            studentDto dto = paymentBO.searchStudent(id);
            lblStudentName.setText(dto.getName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }

    public void BtnReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/PaymentReport.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());


        JasperViewer.viewReport(jasperPrint, false);

        
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtPaymentId.getText();

        try {
            PayementDto dto=paymentBO.getPayment(id);
            lblPaymentId.setText(dto.getId());
            txtAmount.setText(String.valueOf(dto.getAmount()));
            txtDate.setText(dto.getDate());
            txtStatus.setText(dto.getStatus());
            cmbClassID.setValue(dto.getClassId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

