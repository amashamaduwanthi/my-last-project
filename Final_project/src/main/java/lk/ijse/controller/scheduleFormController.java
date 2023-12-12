package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.dto.HallDto;
import lk.ijse.dto.TM.ScheduleTm;
import lk.ijse.dto.TM.ScheduleTm2;
import lk.ijse.dto.classDto;
import lk.ijse.model.HallModel;
import lk.ijse.model.ScheduleModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;


public class scheduleFormController {
    public TextField txtSchedule;
    public TextField txtDescription;
    public TextField txtDuration;
    public JFXComboBox cmbHallId;
    public TextField txtSeach;
    public AnchorPane ScedulePanel;
    public Label lblHallName;
    public Label lblCapacity;
    public Label lblAvailability;
    public Label lblSceduleId;
    public TableView<ScheduleTm2> tblSchedule;
    public TableColumn<?,?> colSceduleId;
    public TableColumn<?,?>  colDuration;
    public TableColumn<?,?>  colDescription;
    public TableColumn<?,?>  colHallId;
    ScheduleModel scedulemodel=new ScheduleModel();
    HallModel hallModel=new HallModel();
    public void initialize(){
        loadCmbHallId();
        generateNextScheduleId();
        loadAllScedules();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colSceduleId.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    private void loadAllScedules() {
        ObservableList<ScheduleTm2> oblist = FXCollections.observableArrayList();
        var model=new ScheduleModel();
        try {
            List<classDto> classDtos = model.loadAllSchedule();
            for(classDto dto:classDtos){
                oblist.add(new ScheduleTm2(dto.getId(), dto.getDuration(), dto.getDescription() ));
            }tblSchedule.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextScheduleId() {
        try {
          String ScheduleId=  ScheduleModel.generateId();
          lblSceduleId.setText(ScheduleId);

        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadCmbHallId() {
        var model= new HallModel();
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
          List<HallDto>hallDtos= model.loadAllHallIds();
            for (HallDto dto : hallDtos) {
                obList.add(dto.getId());
            }
            cmbHallId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddSceduleOnAction(ActionEvent actionEvent) {
        String scheduleId = lblSceduleId.getText();
        String description = txtDescription.getText();
        String duration = txtDuration.getText();
        String hallId = cmbHallId.getValue().toString();
        boolean isVlidated = validateScedule();
        if (isVlidated) {
            var dto = new classDto(scheduleId, description, duration);
            try {
                boolean isSaved = scedulemodel.saveSchedule(dto, hallId);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Scedule saved").show();
                    clearField();
                    loadAllScedules();
                    generateNextScheduleId();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateScedule() {
        boolean ismatch;

        String description =txtDescription .getText();
        ismatch= Pattern.compile("[A-Za-z]{4,}").matcher(description).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid description!!!").show();
            return false;
        }
        String duration=txtDuration.getText();
        ismatch=Pattern.compile("[A-Za-z0-9'\\/\\.\\,]{5,}$").matcher(duration).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid duration").show();
            return false;
        }
        String hallId= (String) cmbHallId.getValue();
        if(hallId==null) {
            new Alert(Alert.AlertType.ERROR, "Enter the hall id !!!").show();
            return false;
        }
        return true;
    }

    private void clearField() {
        lblSceduleId.setText("");
        txtDuration.setText("");
        txtDescription.setText("");
    }

    public void btnCmbHallIdOnAction(ActionEvent actionEvent) {
        String id = cmbHallId.getValue().toString();
        try {
            HallDto dto = hallModel.searchHall(id);
            lblHallName.setText(dto.getName());
            lblAvailability.setText(String.valueOf(dto.getAvailability()));
            lblCapacity.setText(String.valueOf(dto.getCapacity()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id=txtSeach.getText();
        try {
           boolean isDeleted= scedulemodel.deleteSchedule(id);
           if(isDeleted){
               new Alert(Alert.AlertType.CONFIRMATION,"Schedule deleted successfully.").show();
               clearField();
               loadAllScedules();
               generateNextScheduleId();
           }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = lblSceduleId.getText();
        String description = txtDescription.getText();
        String duration = txtDuration.getText();
        String hallId = cmbHallId.getValue().toString();
        var dto = new classDto(id, description, duration);
        try {
            boolean isUpdated = scedulemodel.updateSchedule(dto, hallId);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Schedule updated").show();
                clearField();
                loadAllScedules();
                generateNextScheduleId();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Schedule not updated").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id=txtSeach.getText();
        try {
            classDto dto=scedulemodel.searchSchedule(id);
            if(dto!=null){
                setField(dto);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Schedule not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setField(classDto dto) {
        lblSceduleId.setText(dto.getId());
        txtDescription.setText(dto.getDescription());
        txtDuration.setText(dto.getDuration());
    }

    public void btnGoClassOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/classForm.fxml"));
        ScedulePanel.getChildren().clear();
        ScedulePanel.getChildren().add(rootNode);
    }

    public void btnScheduledOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/ViewScheduleDetails.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
