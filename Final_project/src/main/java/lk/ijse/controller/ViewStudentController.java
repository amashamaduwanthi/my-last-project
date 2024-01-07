package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bao.custom.BOFactory;
import lk.ijse.bao.custom.StudentBo;
import lk.ijse.bao.custom.impl.StudentBOImpl;
import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.dto.TM.StudentTm;
import lk.ijse.dto.studentDto;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ViewStudentController {
    public TableView <StudentTm> tblViewStudent;
    public TableColumn <?,?> colId;
    public TableColumn <?,?> colName;
    public TableColumn <?,?> colAddress;
    public TableColumn <?,?> colEmail;
    public TableColumn <?,?> colTel;
    public TableColumn <?,?> colGender;
    public TableColumn <?,?> colDob;
    StudentBo studentBO = (StudentBo) BOFactory.getBoFactory().getBO(BOFactory.BOType.STUDENT);

    public void initialize() throws SQLException {
        loadAllStudent();
        setCellValueFactory();
    }
    public void loadAllStudent() throws SQLException {
       ObservableList<StudentTm> studentList = FXCollections.observableArrayList();


       try{
           List<studentDto> dtoList =studentBO.loadAllStudent();
           for(studentDto dto :dtoList){
               studentList.add(
                       new StudentTm(
                               dto.getId(),
                               dto.getName(),
                               dto.getAddress(),
                               dto.getEmail(),
                               dto.getContactNo(),
                               dto.getGender(),
                               dto.getDateOfBirth()

               ));
           }
           tblViewStudent.setItems(studentList);

       }catch (SQLException | ClassNotFoundException e){
           throw  new RuntimeException(e);
       }
   }
    public void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

   }





}
