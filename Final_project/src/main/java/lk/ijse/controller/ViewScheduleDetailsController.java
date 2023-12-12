package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.HallDto;
import lk.ijse.dto.TM.ScheduleTm;
import lk.ijse.dto.class2Dto;
import lk.ijse.dto.classDto;
import lk.ijse.model.ClassModel;
import lk.ijse.model.HallModel;
import lk.ijse.model.ScheduleModel;

import java.sql.SQLException;
import java.util.List;

public class ViewScheduleDetailsController {

    public ComboBox cmbHallId;
    public ComboBox cmbClassId;
    public ComboBox cmbScheduleId;
    public Label lblHallName;
    public Label lblAvailability;
    public Label lblCapacity;
    public Label lblGrade;
    public Label lblDescription;
    public Label lblDuration;
    public TableView<ScheduleTm> tblSchedule;
    public TableColumn<?, ?> colHallName;
    public TableColumn<?, ?> colAvailability;
    public TableColumn<?, ?> colCapacity;
    public TableColumn<?, ?> colGrade;
    public TableColumn<?, ?> colDescription;
    public TableColumn<?, ?> colDuration;
    private ScheduleModel scheduleModel = new ScheduleModel();
    private ClassModel classModel = new ClassModel();
    private HallModel hallModel = new HallModel();

    public void initialize() {
        loadAllSchedule();
        loadAllHall();
        loadAllClass();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colHallName.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }


    private void loadAllSchedule() {
        var model = new ScheduleModel();
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<classDto> classDtos = model.loadAllSchedule();
            for (classDto dto : classDtos) {
                obList.add(dto.getId());
            }
            cmbScheduleId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllClass() {
        var model = new ClassModel();
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<class2Dto> class2Dtos = model.loadAllclassIds();
            for (class2Dto dto : class2Dtos) {
                obList.add(dto.getId());
            }
            cmbClassId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadAllHall() {
        var model = new HallModel();
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<HallDto> hallDtos = model.loadAllHallIds();
            for (HallDto dto : hallDtos) {
                obList.add(dto.getId());
            }
            cmbHallId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void cmbOnHallAction(ActionEvent actionEvent) {
        String id = cmbHallId.getValue().toString();
        try {
            HallDto dto = hallModel.searchHall(id);
            lblHallName.setText(dto.getName());
            lblAvailability.setText(dto.getAvailability());
            lblCapacity.setText(dto.getCapacity());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbClassOnAction(ActionEvent actionEvent) {
        String id = cmbClassId.getValue().toString();
        try {
            class2Dto dto = classModel.searchSubject(id);
            lblGrade.setText(dto.getGrade());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbSchduleOnAction(ActionEvent actionEvent) {
        String id = cmbScheduleId.getValue().toString();
        try {
            classDto dto = scheduleModel.searchSchedule(id);
            lblDescription.setText(dto.getDescription());
            lblDuration.setText(dto.getDuration());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnViewOnAction(ActionEvent actionEvent) {
        ObservableList<ScheduleTm> obList = FXCollections.observableArrayList();
        String HallName = lblHallName.getText();
        String availability = lblAvailability.getText();
        String capacity = lblCapacity.getText();
        String grade = lblGrade.getText();
        String description = lblDescription.getText();
        String duration = lblDuration.getText();
        if (!obList.isEmpty()) {
            for (int i = 0; i < tblSchedule.getItems().size(); i++) {

            }

        }
        var scheduleTm=new ScheduleTm(HallName,availability,capacity,grade,description,duration);
        obList.add(scheduleTm);
         tblSchedule.setItems(obList);

    }
}