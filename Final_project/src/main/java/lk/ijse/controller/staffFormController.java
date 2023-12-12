package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.TM.StaffTm;
import lk.ijse.model.AdminModel;

import java.sql.SQLException;
import java.util.List;

public class staffFormController {
    public TextField txtUserName;
    public TextField txtPassword;
    public TextField txtEmail;
    public TextField txtType;
    public TextField txtSeachStaff;
    public TableColumn<?,?> colPassword;
    public TableView<StaffTm> tblStaff;
    public TableColumn<? ,?> colStaffName;
    public TableColumn<? ,?> colEmail;
    private AdminModel adminModel=new AdminModel();
    public void initialize(){
        loadAllStaff();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colStaffName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void loadAllStaff() {
        ObservableList<StaffTm> obList = FXCollections.observableArrayList();
        try {
            List<AdminDto> adminDtos = adminModel.loadAllType();
            for (AdminDto dto : adminDtos) {
                obList.add(new StaffTm(dto.getUsername(),dto.getPassword(),dto.getEmail()));
            }
            tblStaff.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnStaffSearchOnAction(ActionEvent actionEvent) {
        String userName = txtSeachStaff.getText();
        try {
            AdminDto adminDto=adminModel.searchAdmin(userName);
            if (adminDto!=null){
                txtUserName.setText(adminDto.getUsername());
                txtPassword.setText(adminDto.getPassword());
                txtEmail.setText(adminDto.getEmail());
                txtType.setText(adminDto.getType());
            }else{
                new Alert(Alert.AlertType.ERROR,"User Not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }



    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String userName=txtUserName.getText();
        try {
           boolean isDeleted =adminModel.deleteAdmin(userName);
           if(isDeleted){
               clearField();
               loadAllStaff();
               new Alert(Alert.AlertType.CONFIRMATION,"User Deleted").show();
           }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    private void clearField() {
        txtUserName.setText("");
        txtPassword.setText("");
        txtEmail.setText("");
        txtType.setText("");
    }

    public void btnUpdateStaffOnAction(ActionEvent actionEvent) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();
        String type = txtType.getText();
        var dto=new AdminDto(userName,password,email,type);
        try {
            boolean isUpdated=adminModel.updateAdmin(dto);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"User Updated").show();
                clearField();
                loadAllStaff();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }
}
