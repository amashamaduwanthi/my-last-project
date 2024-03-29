package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bao.custom.BOFactory;
import lk.ijse.bao.custom.LecturerBO;
import lk.ijse.bao.custom.SubjectBO;
import lk.ijse.bao.custom.impl.LecturerBOImpl;
import lk.ijse.bao.custom.impl.SubjectBOImpl;
import lk.ijse.dao.Custom.LecturerDAO;
import lk.ijse.dao.Custom.LecturerSubjectDAO;
import lk.ijse.dto.SubjectLecturerDto;
import lk.ijse.dto.TM.SubjectTm;
import lk.ijse.dto.lecturerDto;
import lk.ijse.dto.subjectDto;
import lk.ijse.dao.Custom.Impl.LecturerSubjectDAOImpl;
import lk.ijse.dao.Custom.Impl.LecturerDAOImpl;
import lk.ijse.dao.Custom.Impl.SubjectDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class subjectFormController {
    public TextField txtId;
    public TextField txtName;
    public ComboBox cmbLecId;

    public TextField txtdescription;
    public TableView<SubjectTm> tableColoum;

    public TableColumn<?,?> txtSubName;
    public TableColumn<?,?> colDescription;
    public TextField txtLecturerName;
    public ComboBox cmbSubId;
    public TextField txtSubjectName;
    public Label lblLecturerName;
    public Label lblSubjectName;
    public TableColumn<?,?> colLecturerName;
    public TableColumn<?,?> colLecId;
    public TableColumn <?,?>colLecName;
    public TableColumn<?,?> colSubId;
    public TableColumn<?,?> colSubName;
    public TextField txtSubId;
    public Label lblsubId;

    SubjectBO subjectBO= (SubjectBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.SUBJECT);

    public void initialize() throws ClassNotFoundException {
        loadAllLecturerIds();
        loadAllSubIds();
        loadAllsubLect();
        setCellValueFactory();
        generateNextId();

}

    private void generateNextId() throws ClassNotFoundException {
        try{
            String subId=subjectBO.generateNxtSubjectId();
            lblsubId.setText(subId);

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void loadAllsubLect() {
        ObservableList<SubjectTm> obList = FXCollections.observableArrayList();
        try{
            new LecturerSubjectDAOImpl();
            List<SubjectLecturerDto> subjectLecturerDtos = subjectBO.loadAllSubjectLecturer();
            for (SubjectLecturerDto dto : subjectLecturerDtos) {
                obList.add(new SubjectTm(dto.getLecId(),dto.getLecName(),dto.getSubId(),dto.getSubName()));
            }
            tableColoum.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllSubIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<subjectDto> subjectDtos = subjectBO.loadAllSubject();
            for (subjectDto dto : subjectDtos) {
                obList.add(dto.getId());
            }
            cmbSubId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllLecturerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<lecturerDto> lecturerDtos = subjectBO.loadAllLecturer();
            for (lecturerDto dto : lecturerDtos) {
                obList.add(dto.getId());
            }
            cmbLecId.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCellValueFactory() {

        colLecId.setCellValueFactory(new PropertyValueFactory<>("lecId"));
        colLecName.setCellValueFactory(new PropertyValueFactory<>("lecName"));
        colSubId.setCellValueFactory(new PropertyValueFactory<>("subId"));
        colSubName.setCellValueFactory(new PropertyValueFactory<>("subName"));
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id=lblsubId.getText();
        String name=txtName.getText();
        String description=txtdescription.getText();
        boolean isValidated=validatedSubject();
        if(isValidated) {
            var dto = new subjectDto(id, name, description);

            try {
                boolean isSaved = subjectBO.saveSubject(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Subject saved successfully").show();
                    clearField();
                    generateNextId();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"invalid").show();;
        }

    }

    private void clearField() {
        lblsubId.setText("");
        txtName.setText("");
        txtdescription.setText("");
    }

    public void btndeleteOnAction(ActionEvent actionEvent) {
        String id=txtSubId.getText();
        try {
           boolean isDeleted= subjectBO.deleteSubject(id);
           if(isDeleted){

               new Alert(Alert.AlertType.CONFIRMATION,"Subject deleted successfully.").show();
               clearField();
               generateNextId();
           }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id=lblsubId.getText();
        String name=txtName.getText();
        String description=txtdescription.getText();
        var dto=new subjectDto(id,name,description);
        try {
            boolean isUpdated= subjectBO.UpdateSubject(dto);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Subject Updated").show();
                clearField();
                generateNextId();
            }else{
              new Alert(Alert.AlertType.CONFIRMATION,"Subject not update.").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }


    }

    public void cmbLectIdOnAction(ActionEvent actionEvent) {
        String id= (String) cmbLecId.getValue();
        try {
            lecturerDto dto = subjectBO.searchLecturer(id);
            lblLecturerName.setText(dto.getName());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnCmbSubIdOnAction(ActionEvent actionEvent) {
        String id= (String) cmbSubId.getValue();
        try {
            subjectDto dto = subjectBO.searchSubject(id);
            lblSubjectName.setText(dto.getName());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id=txtSubId.getText();
        try {
            subjectDto subjectDto=subjectBO.searchSubject(id);
            if(subjectDto!=null){
                lblsubId.setText(subjectDto.getId());
                txtName.setText(subjectDto.getName());
                txtdescription.setText(subjectDto.getDescription());

            }else{
                new Alert(Alert.AlertType.INFORMATION,"Subject not found").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String lecId = (String) cmbLecId.getValue();
        String subId = (String) cmbSubId.getValue();
        String lecName=lblLecturerName.getText();
        String subName=lblSubjectName.getText();
        boolean isValidated=validatedSubjectDetail();
        if(isValidated) {

            var dto2 = new SubjectLecturerDto(lecId, lecName, subId, subName);
            try {
                boolean isSaved2 = subjectBO.saveSubjectLecturer(dto2);
                if (isSaved2) {
                    clearField();
                    loadAllsubLect();
                    generateNextId();

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved successfully").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validatedSubject() {
        boolean isMatch;
        String name=txtName.getText();
        isMatch= Pattern.compile("[A-Za-z]{6,}").matcher(name).matches();
        if(!isMatch){
            new Alert(Alert.AlertType.ERROR,"invalid subject Name").show();
            return false;
        }
        String description=txtdescription.getText();
        isMatch=Pattern.compile("^[A-Za-z'\\/\\.\\,]{5,}$").matcher(description).matches();
        if(!isMatch){
            new Alert(Alert.AlertType.ERROR,"invalid description").show();
            return false;
        }
        return true;
    }

    private boolean validatedSubjectDetail() {
        boolean isMatch;
        String name=lblSubjectName.getText();
        isMatch= Pattern.compile("^[a-zA-Z '.-]{4,}$").matcher(name).matches();
        if(!isMatch){
            new Alert(Alert.AlertType.ERROR,"invalid subject Name").show();
            return false;
        }
        String lName=lblLecturerName.getText();
        isMatch=Pattern.compile("^[a-zA-Z '.-]{4,}$").matcher(lName).matches();
        if(!isMatch){
            new Alert(Alert.AlertType.ERROR,"invalid Lecturer Name").show();
            return false;
        }
        return true;
    }

    }
