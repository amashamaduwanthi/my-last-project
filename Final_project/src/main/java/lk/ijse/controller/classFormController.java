package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bao.custom.ClassBO;
import lk.ijse.bao.custom.impl.ClassBOImpl;
import lk.ijse.dto.HallDto;
import lk.ijse.dto.TM.ClassTm;
import lk.ijse.dto.class2Dto;
import lk.ijse.dao.Custom.Impl.ClassDAOImpl;
import lk.ijse.dao.Custom.Impl.HallDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class classFormController {
    public TextField txtClassId;
    public TextField txtGrade;
    public TextField txtScheduleId;
    public TextField txtLectId;
    public TextField txtSerchClassId;
    public TextField tctHallId;
    public TextField txtHallName;
    public TextField txtAvailabitiy;
    public TextField txtCapacity;
    public AnchorPane panel;
    public TableView<ClassTm> tblClass;
    public TableColumn<?,?> colClassId;
    public TableColumn<?,?> colGrade;
    public Label lblClassId;
    private ClassBO classBO=new ClassBOImpl();

    public void initialize(){
        loadAllClasses();
        setCellValueFactory();
        generateNextId();
    }

    private void generateNextId() {
        try{
            String classId= classBO.generateNxtClassId();
            lblClassId.setText(classId);

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
    }

    private void loadAllClasses() {
        ObservableList<ClassTm> obList = FXCollections.observableArrayList();
        try {
            List<class2Dto> class2Dtos =classBO.loadAllclassIds();
            for(class2Dto dto:class2Dtos) {
                obList.add(new ClassTm(dto.getId(), dto.getGrade()));
            }
            tblClass.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }


    public void btnAddClassOnAction(ActionEvent actionEvent) {
        String classId = lblClassId.getText();
        String grade = txtGrade.getText();
        boolean isValidated=validatedClassDetail();
        if(isValidated) {

            var dto = new class2Dto(classId, grade);

            try {
                boolean isSaved = classBO.saveClass(dto);

                if (isSaved) {
                    clearField();
                    loadAllClasses();
                    generateNextId();
                    new Alert(Alert.AlertType.CONFIRMATION, "Class saved Successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }

    }

    private void clearField() {
        lblClassId.setText("");
        txtGrade.setText("");
    }

    private boolean validatedClassDetail() {
        boolean isMatch;
        String grade=txtGrade.getText();
        isMatch= Pattern.compile("[A-Za-z0-9'\\/\\.\\,]{5,}$").matcher(grade).matches();
        if(!isMatch){
            new Alert(Alert.AlertType.ERROR,"invalid grade").show();
            return false;
        }
        return true;
    }

    public void btnDeleteClassOnAction(ActionEvent actionEvent) {
        String classId = txtSerchClassId.getText();
        try {
          boolean isDeleted= classBO.deleteClass(classId);
            if(isDeleted){
                clearField();
                loadAllClasses();
                generateNextId();
                new Alert(Alert.AlertType.CONFIRMATION,"Class Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateClassOnAction(ActionEvent actionEvent) {
        String classId = lblClassId.getText();
        String grade = txtGrade.getText();


        var dto=new class2Dto(classId,grade);
        try {
            boolean isUpdated=classBO.updateClass(dto);
            if(isUpdated){
                clearField();
                loadAllClasses();
                generateNextId();
                new Alert(Alert.AlertType.CONFIRMATION,"Class Updated").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Class Not Updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }
    public void btnSearchClassOnAction(ActionEvent actionEvent) {
        String classId = txtSerchClassId.getText();
        try {
            class2Dto class2Dto=classBO.searchClass(classId);
            if(class2Dto!=null){
                   lblClassId.setText(class2Dto.getId());
                txtGrade.setText(class2Dto.getGrade());


            }else{
                new Alert(Alert.AlertType.INFORMATION,"Subject not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnScheduleOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/ScheduleForm.fxml"));
        panel.getChildren().clear();
        panel.getChildren().add(rootNode);
    }

    public void txtSerachOnAction(ActionEvent actionEvent) {

        String id = tctHallId.getText();
        try {
            HallDto hallDto=classBO.searchHall(id);
            if(hallDto!=null){
                txtHallName.setText(hallDto.getName());
                txtAvailabitiy.setText(hallDto.getAvailability());
                txtCapacity.setText(hallDto.getCapacity());
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Hall not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/StudentClassRegistrationForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

