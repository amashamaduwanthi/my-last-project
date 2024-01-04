package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dao.Custom.AdminDAO;
import lk.ijse.dto.AdminDto;
import lk.ijse.dao.Custom.Impl.AdminDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class signFormController {
    public AnchorPane root2;
    public TextField txtuserName;
    public TextField txtEmail;
    public PasswordField pwPassword;
    public ComboBox cmbType;

    public void initialize() {
        loadAllTyes();
    }

    private void loadAllTyes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Admin");
        obList.add("Staff");

        cmbType.setItems(obList);
    }
   AdminDAO adminDAOImpl= new AdminDAOImpl();
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) root2.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String username = txtuserName.getText();
        String password = pwPassword.getText();
        String email = txtEmail.getText();
        String type = (String) cmbType.getValue();

        boolean isValidated = validateSignUp();
        var dto = new AdminDto(username, password, email, type);
        if (isValidated) {
            try {

                boolean isSaved = adminDAOImpl.save(dto);
                if (isSaved) {
                   // new Alert(Alert.AlertType.CONFIRMATION, "User added successfully!!!").show();
                    clearField();
                }
            } catch (SQLException | ClassNotFoundException e) {
               // new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            new Alert(Alert.AlertType.INFORMATION, "User added successfully!!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User added failed!!!").show();
        }
    }

    private boolean validateSignUp() {
        boolean ismatch;
        String username = txtuserName.getText();
        ismatch= Pattern.compile("[A-Za-z]{4,}").matcher(username).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid userName !!!").show();
            return false;
        }

        String password = pwPassword.getText();
        ismatch= Pattern.compile("[A-Za-z0-9]{4,}").matcher(password).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid password !!!").show();
            return false;
        }

        String email = txtEmail.getText();
        ismatch= Pattern.compile("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(email).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid email !!!").show();
            return false;
        }
        String type = (String) cmbType.getValue();
        if(type==null){
            new Alert(Alert.AlertType.ERROR,"invalid type !!!").show();
            return false;
        }
        return true;
    }



    public void clearField(){
        txtuserName.setText("");
        txtEmail.setText("");
        pwPassword.setText("");

    }

}
