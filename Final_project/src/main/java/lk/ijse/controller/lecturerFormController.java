package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.TM.LecturerTm;
import lk.ijse.dto.lecturerDto;
import lk.ijse.model.Lecturermodel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class lecturerFormController {


    public AnchorPane panel2;

    public TextField txtid;
    public TextField txtName;
    public TextField txtTel;
    public TextField txtAddress;
    public TextField txtNic;
    public TextField txtUni;
    public TableView <LecturerTm>tblLec;
    public TableColumn<?,?> colId;
    public TableColumn<?,?> colName;
    public TableColumn <?,?>colTel;
    public TableColumn<?,?> colAddress;
    public Label lblLecId;
    public TextField txtLectId;
    private Lecturermodel lecturermodel=new Lecturermodel();




    public void btnAddOnAction(ActionEvent actionEvent) {
        String id=lblLecId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel= Integer.parseInt(txtTel.getText());
        String nic = txtNic.getText();
        String uni = txtUni.getText();
        boolean isVlidated=validateLecturer();
        if(isVlidated) {
            var dto = new lecturerDto(id, name, address, tel, nic, uni);
            try {
                boolean isSaved = lecturermodel.saveLecturer(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Lecturer saved").show();
                    clearField();
                    generateId();
                    loadLecturer();



                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"invalid").show();

            }
    }

    private boolean validateLecturer() {
        boolean ismatch;
        String lecName= txtName.getText();
                ismatch= Pattern.compile("[A-Za-z]{4,}").matcher(lecName).matches();
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
        String nic=txtNic.getText();
        ismatch=Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$").matcher(nic).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid nic").show();

        }
        String university= txtUni.getText();
        ismatch= Pattern.compile("[A-Za-z]{4,}").matcher(lecName).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid university !!!").show();
            return false;
        }
        return true;
    }

    private void clearField() {
        lblLecId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
        txtTel.setText("");
        txtNic.setText("");
        txtUni.setText("");

    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id=lblLecId.getText();
        String name=txtName.getText();
        String address = txtAddress.getText();
        int tel= Integer.parseInt(txtTel.getText());
        String nic = txtNic.getText();
        String uni = txtUni.getText();

        var dto=new lecturerDto(id,name,address,tel,nic,uni);
        try {
            boolean isUpdated=lecturermodel.updateLecturer(dto);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Lecturer updated").show();
                clearField();
                loadLecturer();
                generateId();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Lecturer not updated").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public  void  btnSearchlecturerOnAction(ActionEvent actionEvent){
        String id=txtLectId.getText();
        try {
            lecturerDto dto=lecturermodel.searchLecturer(id);
            if(dto!=null){
                setField(dto);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Lecturer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setField(lecturerDto dto) {
        lblLecId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtTel.setText(String.valueOf(dto.getTel()));
        txtNic.setText(dto.getNic());
        txtUni.setText(dto.getUniversity());
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id=txtLectId.getText();
        try {
           boolean isDeleted= lecturermodel.delteLecturer(id);
           if(isDeleted){
               clearField();
               loadLecturer();
               generateId();

               new Alert(Alert.AlertType.CONFIRMATION,"Lecturer deleted successfully").show();
           }
        } catch (SQLException e) {
            new  Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }
    public void btnClickOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/subjectForm.fxml"));
        panel2.getChildren().clear();
        panel2.getChildren().add(rootNode);
    }
    public void initialize(){
        loadLecturer();
        generateId();
        setCellValueFactory();
    }

    private void generateId() {
        try{
            String lecId=Lecturermodel.generateNxtLecturerId();
            lblLecId.setText(lecId);

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private void loadLecturer() {
        ObservableList<LecturerTm> obList = FXCollections.observableArrayList();

        try{
            List<lecturerDto> lecturers = lecturermodel.loadAllLecturer();
            for (lecturerDto dto : lecturers) {
                obList.add(new LecturerTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getTel()

                ));
            }
            tblLec.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}

