package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bao.custom.ExamBO;
import lk.ijse.bao.custom.impl.ExamBOImpl;
import lk.ijse.dao.Custom.ExamDAO;
import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ExamDto;
import lk.ijse.dto.ResultDto;
import lk.ijse.dto.TM.ResultTm;
import lk.ijse.dto.studentDto;
import lk.ijse.dao.Custom.Impl.ExamDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class examFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtDate;
    public TextField txtResult;
    public TableColumn<?,?> colStudentName;
    public TableColumn<?,?> colExamName;
    public TableColumn<?,?> colMarks;
    public JFXComboBox cmbStudentId;
    public JFXComboBox cmbExamId;
    public Label lblStudentName;
    public Label lblExamName;
    public TableView<ResultTm> tblResult;
    public Label lblExamId;
    private ExamBO examBO = new ExamBOImpl();

    public void initialize() {

            loadAllStudent();
            loadAllExam();
            setCellValueFactory();
            generateNextId();
            loadResults();
    }

    private void loadResults() {
        ObservableList<ResultTm> obList = FXCollections.observableArrayList();

        try {
            List<ResultDto> resultDtos= examBO.loadAllResult();
            for(ResultDto dto:resultDtos){
                obList.add(new ResultTm(dto.getExamId(), dto.getStudentId(), dto.getMark()));
            }
            tblResult.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void generateNextId() {
        try{
            String examId= examBO.generateNxtExamId();
            lblExamId.setText(examId);

        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {

        colStudentName.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        colExamName.setCellValueFactory(new PropertyValueFactory<>("examId"));
        colMarks.setCellValueFactory(new PropertyValueFactory<>("mark"));
    }

    private void loadAllExam() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            List<ExamDto> examDtos = examBO.getAllExam();
            for (ExamDto dto : examDtos) {
                observableList.add(dto.getId());
            }
            cmbExamId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    private void loadAllStudent() {

        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<studentDto> studentDtos = examBO.loadAllStudent();
            for (studentDto dto : studentDtos) {
                obList.add(dto.getId());
            }
            cmbStudentId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }
    public void btnAddExamOnAction(ActionEvent actionEvent) {
        String Id = lblExamId.getText();
        String name = txtName.getText();
        String date = txtDate.getText();
        boolean isVlidated = validateExam();
        if (isVlidated) {
            var dto = new ExamDto(Id, name, date);
            try {
                boolean isSaved = examBO.SaveExam(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Exam saved").show();
                    clearField();
                    generateNextId();
                    loadAllExam();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }
    }

    private void clearField() {
        lblExamId.setText("");
        txtName.setText("");
        txtDate.setText("");
    }

    public void btnUpdateExamOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String date = txtDate.getText();
        var dto = new ExamDto(id, name, date);

        try {
            boolean isUpdated = examBO.UpdateExam(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Exam updated").show();
                clearField();
                generateNextId();
                loadAllExam();
            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }

    public void btnDeleteExamOnAction(ActionEvent actionEvent){
        String id = txtId.getText();

        try {
            boolean isDeleted =examBO.deleteExam(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Exam deleted successfully.").show();
                clearField();
                generateNextId();
                loadAllExam();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnResultOnAction(ActionEvent actionEvent) {
        String studentId = (String) cmbStudentId.getValue();
        String examId = (String) cmbExamId.getValue();
        double mark = Double.parseDouble(txtResult.getText());
        boolean isVlidated = validateResult();
        if (isVlidated) {

            var dto1 = new ResultDto(studentId, examId, mark);

            try {
                boolean isSaved1 = examBO.SaveResult(dto1);
                if (isSaved1) {
                    new Alert(Alert.AlertType.CONFIRMATION, "saved successfully").show();
                    loadResults();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateResult() {
        boolean ismatch;
        String marks = txtResult.getText();
        ismatch= Pattern.compile("^[0-9]+\\.?[0-9]*$").matcher(marks).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid results !!!").show();
            return false;
        }
        return true;
    }

    public void cmbStudentOnAction(ActionEvent actionEvent) {
        String id= (String) cmbStudentId.getValue();
        try {
            studentDto studentDto= examBO.searchStudent(id);
            lblStudentName.setText(studentDto.getName());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void cmbExamOnAction(ActionEvent actionEvent) {
        String id= (String) cmbExamId.getValue();
        try {
            ExamDto examDto=examBO.searchExam(id);
            lblExamName.setText(examDto.getName());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnSearchExamOnAction(ActionEvent actionEvent) {
            String id=txtId.getText();

            try {
                ExamDto examDto =examBO .searchExam(id);
                if(examDto!=null){
                    lblExamId.setText(examDto.getId());
                    txtName.setText(examDto.getName());
                    txtDate.setText(examDto.getDate());
                }else {
                    new Alert(Alert.AlertType.INFORMATION,"Exam not founf").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }

        }

    public void btnGenerateReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {


        InputStream resourceAsStream = getClass().getResourceAsStream("/report/report2.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());


        JasperViewer.viewReport(jasperPrint, false);

    }

    private boolean validateExam() {
        boolean ismatch;

        String name = txtName.getText();
        ismatch= Pattern.compile("[A-Za-z]{4,}").matcher(name).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid name !!!").show();
            return false;
        }
        return true;
    }
    }



