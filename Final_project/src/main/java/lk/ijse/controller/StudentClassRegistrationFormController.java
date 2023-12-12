package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.*;
import lk.ijse.dto.TM.StudentClassTm;
import lk.ijse.model.*;

import java.sql.SQLException;
import java.util.List;

public class StudentClassRegistrationFormController {
    public JFXComboBox cmbStuID;
    public JFXComboBox cmbClassId;
    public JFXComboBox cmbSubId;
    public JFXComboBox cmbLecturerid;
    public Label lblStuName;
    public Label lblGrade;
    public Label lblSubName;
    public Label lblLectName;
    public TableView<StudentClassTm> tblRegistration;
    public TableColumn<?, ?> colstuId;
    public TableColumn<?, ?> colClassId;
    public TableColumn<?, ?> colSubName;
    public TableColumn<?, ?> colLecName;
    public TextField txtStuId;

    public void initialize() {
        loadAllStudentId();
        loadAllClassId();
        loadAllSubjectId();
        loadAllLecturerId();
        loadAllDetails();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colstuId.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        colClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colSubName.setCellValueFactory(new PropertyValueFactory<>("subName"));
        colLecName.setCellValueFactory(new PropertyValueFactory<>("lecName"));

    }

    private void loadAllDetails() {
        ObservableList<StudentClassTm> obList = FXCollections.observableArrayList();
        var model = new StudentClassModel();
        try {
            List<StudentClassDto> studentClassDtos = model.loadAllDetails();
            for (StudentClassDto dto : studentClassDtos) {
                obList.add(new StudentClassTm(dto.getId(), dto.getClassId(), dto.getSubName(), dto.getLecName()));
            }
            tblRegistration.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllLecturerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        var model = new Lecturermodel();
        try {
            List<lecturerDto> lecturerDtos = model.loadAllLecturer();
            for (lecturerDto dto : lecturerDtos) {
                obList.add(dto.getId());
            }
            cmbLecturerid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllSubjectId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        var model = new SubjectModel();
        try {
            List<subjectDto> subjectDtos = model.loadAllSubject();
            for (subjectDto dto : subjectDtos) {
                obList.add(dto.getId());
            }
            cmbSubId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllClassId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        var model = new ClassModel();
        try {
            List<class2Dto> class2Dtos = model.loadAllclassIds();
            for (class2Dto dto : class2Dtos) {
                obList.add(dto.getId());
            }
            cmbClassId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadAllStudentId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        var model = new studentModel();
        try {
            List<studentDto> studentDtos = model.loadAllStudent();
            for (studentDto dto : studentDtos) {
                obList.add(dto.getId());
            }
            cmbStuID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbLoadStudentNameOnAction(ActionEvent actionEvent) {
        String id = (String) cmbStuID.getValue();
        var model = new studentModel();
        try {
            var student = model.searchStudent(id);
            lblStuName.setText(student.getName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }

    public void cmbLoadGradeOnAction(ActionEvent actionEvent) {
        String id = (String) cmbClassId.getValue();
        var model = new ClassModel();
        try {
            var grade = model.searchSubject(id);
            lblGrade.setText(grade.getGrade());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void cmbLoadSubjectNameOnAction(ActionEvent actionEvent) {
        String id = (String) cmbSubId.getValue();
        var model = new SubjectModel();
        try {
            var student = model.searchSubject(id);
            lblSubName.setText(student.getName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void cmbLoadLecNameOnAction(ActionEvent actionEvent) {
        String id = (String) cmbLecturerid.getValue();
        var model = new Lecturermodel();
        try {
            var student = model.searchLecturer(id);
            lblLectName.setText(student.getName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String classId = (String) cmbClassId.getValue();
        String id = (String) cmbStuID.getValue();
        String subName = lblSubName.getText();
        String lectName = lblLectName.getText();

        var model = new classSubModel();
        try {
            boolean isAdded = model.addClass(classId,id, subName, lectName);
            if (isAdded) {
                clearField();
                loadAllDetails();

                new Alert(Alert.AlertType.INFORMATION, "Class Added").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    private void clearField() {
        lblLectName.setText("");
        lblSubName.setText("");
        lblGrade.setText("");
        lblStuName.setText("");
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id=txtStuId.getText();
        var model=new StudentClassModel();
        try {
            StudentClassDto dto=model.searchStudentClass(id);
            if(dto!=null){
               // cmbClassId.setValue(dto.getClassId());
                lblSubName.setText(dto.getSubName());
                lblLectName.setText(dto.getLecName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}