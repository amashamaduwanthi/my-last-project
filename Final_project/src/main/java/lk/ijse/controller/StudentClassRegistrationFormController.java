package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bao.custom.*;
import lk.ijse.bao.custom.impl.*;
import lk.ijse.dao.Custom.*;
import lk.ijse.dao.Custom.Impl.*;
import lk.ijse.dto.*;
import lk.ijse.dto.TM.StudentClassTm;

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

    private LecturerBO lecturerBO= (LecturerBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.LECTURER);
    private ClassBO classBO= (ClassBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.CLASS);
   StudentClassBO studentClassBO= (StudentClassBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.STUDENTCLASSREGISTRATION);
  StudentBo studentBo= (StudentBo) BOFactory.getBoFactory().getBO(BOFactory.BOType.STUDENT);
  SubjectBO subjectBo= (SubjectBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.SUBJECT);

    public void initialize() throws ClassNotFoundException {
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

    private void loadAllDetails() throws ClassNotFoundException {
        ObservableList<StudentClassTm> obList = FXCollections.observableArrayList();

        try {
            List<StudentClassDto> studentClassDtos = studentClassBO.loadAllDetails();
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

        try {
            List<lecturerDto> lecturerDtos = lecturerBO.loadAllLecturer();
            for (lecturerDto dto : lecturerDtos) {
                obList.add(dto.getId());
            }
            cmbLecturerid.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllSubjectId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<subjectDto> subjectDtos = subjectBo.loadAllSubject();
            for (subjectDto dto : subjectDtos) {
                obList.add(dto.getId());
            }
            cmbSubId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllClassId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<class2Dto> class2Dtos = classBO.loadAllclassIds();
            for (class2Dto dto : class2Dtos) {
                obList.add(dto.getId());
            }
            cmbClassId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadAllStudentId() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<studentDto> studentDtos =studentBo.loadAllStudent();
            for (studentDto dto : studentDtos) {
                obList.add(dto.getId());
            }
            cmbStuID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbLoadStudentNameOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String id = (String) cmbStuID.getValue();

        try {
            var student =studentBo.searchStudent(id);
            lblStuName.setText(student.getName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }

    public void cmbLoadGradeOnAction(ActionEvent actionEvent) {
        String id = (String) cmbClassId.getValue();

        try {
            var grade = classBO.searchClass(id);
            lblGrade.setText(grade.getGrade());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void cmbLoadSubjectNameOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String id = (String) cmbSubId.getValue();

        try {
            var student = subjectBo.searchSubject(id);
            lblSubName.setText(student.getName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void cmbLoadLecNameOnAction(ActionEvent actionEvent) {
        String id = (String) cmbLecturerid.getValue();

        try {
            var student = lecturerBO.searchLecturer(id);
            lblLectName.setText(student.getName());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String classId = (String) cmbClassId.getValue();
        String id = (String) cmbStuID.getValue();
        String subName = lblSubName.getText();
        String lectName = lblLectName.getText();


        try {
            boolean isAdded = studentClassBO.addClass(classId,id, subName, lectName);
            if (isAdded) {
                clearField();
                loadAllDetails();

                new Alert(Alert.AlertType.INFORMATION, "Class Added").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
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

    public void btnSearchOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String id=txtStuId.getText();

        try {
            StudentClassDto dto=studentClassBO.searchStudentClass(id);
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