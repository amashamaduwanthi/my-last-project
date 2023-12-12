package lk.ijse.controller;

import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.QR.GenerateQr;
import lk.ijse.dto.*;
import lk.ijse.email.email;
import lk.ijse.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;


public class studentFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContactNo;
    public JFXComboBox cmbGender;
    public DatePicker dfrDateOfBirth;
    public Label txtEmail;
    public Label lblRegDate;
    public Label lblRegId;
    public TextField txtParentId;
    public TextField txtpName;
    public TextField txtPContactNo;
    public TextField txtStuId;
    public TextField txtemail;
    public JFXRadioButton rdBtnYes;
    public JFXRadioButton rdBtnNo;
    public TextField txtstuId;
    public TextField txtEmails;
    public JFXComboBox cmbUserName;

    studentModel studentModel= new studentModel();
   ParentModel parentModel= new ParentModel();
   RegisterMode registerMode=new RegisterMode();

    //private studentModel studModel=new studentModel();
    public void initialize(){

     setDate();
     loadAllGender();
     generateRegId();
     loadUserName();

  }

    private void loadUserName() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        var model= new AdminModel();
        try {
            List<AdminDto> adminDtos = model.loadAllType();
            adminDtos.forEach(adminDto -> obList.add(adminDto.getUsername()));
            cmbUserName.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllGender() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Female");
        obList.add("Male");

        cmbGender.setItems(obList);
    }
    public void setDate(){
      lblRegDate.setText(String.valueOf(LocalDate.now()));
  }
    public void btnAddOnAction(ActionEvent actionEvent) {
        //student detail
        String id=txtId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String email=txtEmails.getText();
        int contactNo = Integer.parseInt(txtContactNo.getText());
        String gender = (String) cmbGender.getValue();
        String dateOfBirth = String.valueOf(dfrDateOfBirth.getValue());



        //parent detail
        String parentId = txtParentId.getText();
        String ParentName = txtpName.getText();
        int tel= Integer.parseInt(txtPContactNo.getText());
        String stuId = txtstuId.getText();

        //Registration Details
        String regId=lblRegId.getText();
        String sName=txtName.getText();
        String sEmail=txtEmails.getText();
        String date=lblRegDate.getText();
        String sParentId=txtParentId.getText();
        String userName= (String) cmbUserName.getValue();
        boolean isVlidated=validateStudent();
        if(isVlidated){

        var dto=new studentDto(id,name,address,email,contactNo,gender,dateOfBirth);
        var Dto = new ParentDto(parentId, ParentName, tel, stuId);
        var dto2=new RegistrationDto(regId,sName,sEmail,date,sParentId,userName);

            try {
                boolean isSaved = studentModel.setStudent(dto, Dto, dto2);
                // boolean isAdded=parentModel.SaveStudent(Dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " saved successfully").show();
                    GenerateQr generateQr = new GenerateQr(id, name, address);
                    clearField();
                    generateRegId();
                    sentMail(sEmail);
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }
            //sentMail(sEmail);
            new Alert(Alert.AlertType.CONFIRMATION,"Student register successfully!").show();

        }else{
            new Alert(Alert.AlertType.ERROR,"invalid").show();
        }
    }

   private void sentMail(String sEmail) {

        email mail = new email();
        mail.setMsg("Welcome..! \n\n\tYou are successfully register with thigmaSmartLearn center  \n\nThank you..!");
        mail.setTo(sEmail);
        mail.setSubject("thigma institute log in System Login");

        Thread thread = new Thread(mail);
        thread.start();
    }

    private boolean validateStudent() {
        boolean ismatch;
        String stuName = txtName.getText();
        ismatch= Pattern.compile("[A-Za-z]{4,}").matcher(stuName).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid userName !!!").show();
            return false;
        }
        String email = txtEmails.getText();
        ismatch= Pattern.compile("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(email).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid email !!!").show();
            return false;
        }
        String tel = txtContactNo.getText();
        ismatch= Pattern.compile("^[0-9]{10}$").matcher(tel).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid contactNo !!!").show();
            return false;
        }

        String pName = txtpName.getText();
        ismatch= Pattern.compile("[A-Za-z]{4,}").matcher(pName).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid userName !!!").show();
            return false;
        }
        String address=txtAddress.getText();
        ismatch=Pattern.compile("[A-Za-z0-9'\\/\\.\\,]{5,}$").matcher(address).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid address").show();
            return false;
        }
        String designation = txtstuId.getText();
        ismatch= Pattern.compile("[A-Za-z]{4,}").matcher(designation).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid designation !!!").show();
            return false;
        }

        return true;
    }

    private void clearField() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtEmails.setText("");
        txtContactNo.setText("");
        cmbGender.setValue("");

        txtParentId.setText("");
        txtpName.setText("");
        txtPContactNo.setText("");
        txtstuId.setText("");

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        studentDto studentDto= null;
        try {
            studentDto = studentModel.searchStudent(id);
            if(studentDto!=null){
                txtId.setText(studentDto.getId());
                txtName.setText(studentDto.getName());
                txtAddress.setText(studentDto.getAddress());
                txtEmails.setText(studentDto.getEmail());
                txtContactNo.setText(String.valueOf(studentDto.getContactNo()));
                cmbGender.setValue(studentDto.getGender());
                dfrDateOfBirth.setValue(LocalDate.parse(studentDto.getDateOfBirth()));


            }else {
                new Alert(Alert.AlertType.INFORMATION,"student not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }
    public void btnUpdateStudentOnAction(ActionEvent actionEvent) {
      String id=txtId.getText();
      String name=txtName.getText();
      String address=txtAddress.getText();
      String email=txtEmails.getText();
      int contactNo=Integer.parseInt(txtContactNo.getText());
      String gender=(String) cmbGender.getValue();
      String date=String.valueOf(dfrDateOfBirth.getValue());


      var dto=new studentDto(id,name,address,email,contactNo,gender,date);
        try {
          boolean isUpdated=  studentModel.updateStudent(dto);
          if(isUpdated){
              new Alert(Alert.AlertType.CONFIRMATION,"Student Updated").show();
              clearField();
              generateRegId();
          }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }
    public void btnDeleteStudentOnAction(ActionEvent actionEvent) {
        String id=txtId.getText();
        try {
            boolean isDeleted=studentModel.deleteStudent(id);
            if(isDeleted){
                clearField();
                generateRegId();
                new Alert(Alert.AlertType.CONFIRMATION,"Student Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }
    public void btnViewOnAction(ActionEvent actionEvent) throws IOException {
       Parent root= FXMLLoader.load(getClass().getResource("/view/ViewStudentDetails.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
  }
    public void generateRegId(){
      try{
          String regId=studentModel.generateNextRegId();
          lblRegId.setText(regId);

      }catch (SQLException e){
          new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
      }
    }

    public void btnParentSerachOnAction(ActionEvent actionEvent) {
        String parentId = txtParentId.getText();
        var model=new ParentModel();
        try {
            ParentDto parentDto = model.searchParent(parentId);
            if(parentDto!=null){
                txtParentId.setText(parentDto.getParentId());
                txtpName.setText(parentDto.getParentName());
                txtPContactNo.setText(String.valueOf(parentDto.getParentContactNo()));
                txtstuId.setText(parentDto.getStuId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
