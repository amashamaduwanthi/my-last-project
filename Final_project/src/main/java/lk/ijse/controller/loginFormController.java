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
import lk.ijse.dto.AdminDto;
import lk.ijse.email.email;
import lk.ijse.model.AdminModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class loginFormController {
    public AnchorPane root1;
    public TextField txtUserName;



    public PasswordField pwPassword;
    public ComboBox cmbType;
    private AdminModel adminModel=new AdminModel();
    public void initialize(){
        loadAllTyes();
    }

    private void loadAllTyes() {
        ObservableList<String> obList= FXCollections.observableArrayList();
        obList.add("Admin");
        obList.add("staff");
        cmbType.setItems(obList);
    }

    public void btnSinginOnAction(ActionEvent actionEvent) throws IOException {
       Parent rootNode= FXMLLoader.load(this.getClass().getResource("/view/signForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage= (Stage) root1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Signin Form");
        stage.centerOnScreen();
        stage.show();


    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String name = txtUserName.getText();
        String password = pwPassword.getText();
        String type = (String) cmbType.getValue();


        var model = new AdminModel();

        try {
           AdminDto dto= model.checkLogin(name, password,type);
           if(dto!=null) {
               if (name.equals(dto.getUsername()) && password.equals(dto.getPassword()) && type.equals("Admin")) {
                   LoadDashBoard();

               } else if (name.equals(dto.getUsername()) && password.equals(dto.getPassword()) && type.equals("staff")) {
                   LoadDashBoard2();
               } else {
                   new Alert(Alert.AlertType.CONFIRMATION, "Invalid Username or Password or Type").show();
               }
           }else{
               new Alert(Alert.AlertType.CONFIRMATION, "Invalid Username or Password or Type").show();
           }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Invalid Username or Password or Type").show();
        }
    }


    public void LoadDashBoard() throws IOException {

        Parent rootNode= FXMLLoader.load(this.getClass().getResource("/view/dashboardForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage= (Stage) root1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
        stage.show();
    }




    public void LoadDashBoard2() throws IOException {

        Parent rootNode= FXMLLoader.load(this.getClass().getResource("/view/staffdashbordForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage= (Stage) root1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
        stage.show();
    }
}
