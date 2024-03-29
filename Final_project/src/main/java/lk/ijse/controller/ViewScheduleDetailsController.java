package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bao.custom.BOFactory;
import lk.ijse.bao.custom.ClassBO;
import lk.ijse.bao.custom.ScheduleBO;
import lk.ijse.bao.custom.impl.ClassBOImpl;
import lk.ijse.bao.custom.impl.ScheduleBOImpl;
import lk.ijse.dao.Custom.ClassDAO;
import lk.ijse.dao.Custom.HallDAO;
import lk.ijse.dao.Custom.ScheduleDAO;
import lk.ijse.dto.HallDto;
import lk.ijse.dto.TM.ScheduleTm;
import lk.ijse.dto.class2Dto;
import lk.ijse.dto.classDto;
import lk.ijse.dao.Custom.Impl.ClassDAOImpl;
import lk.ijse.dao.Custom.Impl.HallDAOImpl;
import lk.ijse.dao.Custom.Impl.ScheduleDAOImpl;

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
    private ScheduleBO scheduleBO = (ScheduleBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.SCHEDULE);
    private ClassBO classBO = (ClassBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.CLASS);


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

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<classDto> classDtos = scheduleBO.loadAllSchedule();
            for (classDto dto : classDtos) {
                obList.add(dto.getId());
            }
            cmbScheduleId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllClass() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<class2Dto> class2Dtos =classBO.loadAllclassIds();
            for (class2Dto dto : class2Dtos) {
                obList.add(dto.getId());
            }
            cmbClassId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadAllHall() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<HallDto> hallDtos = classBO.loadAllHallIds();
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
            HallDto dto = classBO.searchHall(id);
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
            class2Dto dto = classBO.searchClass(id);
            lblGrade.setText(dto.getGrade());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbSchduleOnAction(ActionEvent actionEvent) {
        String id = cmbScheduleId.getValue().toString();
        try {
            classDto dto = scheduleBO.searchSchedule(id);
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