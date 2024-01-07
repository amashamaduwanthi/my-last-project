package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bao.custom.BOFactory;
import lk.ijse.bao.custom.DashboardBO;
import lk.ijse.bao.custom.impl.DashboardBOImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class staffdashbordFormController {
    public AnchorPane panel;
    public Label lbldate;
    public PieChart piechartThigma;
    public BarChart barchart;
    public Label lblTotalStudent;
    public Label lblTotalLecturer;
    public Label lblTotalHalls;
    public Label lblTime;
    public JFXButton btnBack;
    public Label dashboardPanel;
    public JFXButton btnStudent;
    public JFXButton btnLecturer;
    public JFXButton btnClass;
    public JFXButton btnLogOut;
    public JFXButton btnExam;
    public JFXButton btnPayment;
    DashboardBO dashboardBO= (DashboardBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.DASHBOARD);

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/backStaffDashBoardForm.fxml"));
        panel.getChildren().clear();
        panel.getChildren().add(rootNode);
    }

    public void manageStudentOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/studentForm.fxml"));
        panel.getChildren().clear();
        panel.getChildren().add(rootNode);
    }

    public void btnManageLectuererOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/lecturerForm.fxml"));
        panel.getChildren().clear();
        panel.getChildren().add(rootNode);
    }

    public void btnManageClassOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/classForm.fxml"));
        panel.getChildren().clear();
        panel.getChildren().add(rootNode);
    }

    public void btnlogoutOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode=FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage= (Stage) dashboardPanel.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void btnManageExamOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/examForm.fxml"));
        panel.getChildren().clear();
        panel.getChildren().add(rootNode);
    }

    public void btnManagePaymentsOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/paymentForm.fxml"));
        panel.getChildren().clear();
        panel.getChildren().add(rootNode);
    }
    public void initialize(){
        setPiechartThigma();
        loadBarChart();
        loadDate();

        loadTotalStudent();
        loadTotalLecturer();
        loadTotalHalls();
        setTime();
    }

    private void loadTotalHalls() {
        String HallValue="0";
        try{
            HallValue= dashboardBO.searchTotalHall();
        }catch (Exception e){
            HallValue="0";
        }

        lblTotalHalls.setText(HallValue);

    }

    private void loadTotalLecturer() {
        String lecturerValue="0";
        try{
            lecturerValue= dashboardBO.searchTotalLecturer();
        }catch (Exception e){
            lecturerValue="0";
        }

        lblTotalLecturer.setText(lecturerValue);



    }

    private void loadTotalStudent() {
        String studentValue="0";
        try{

            studentValue= dashboardBO.searchTotalStudent();
        }catch (Exception e){
            studentValue="0";
        }

        lblTotalStudent.setText(studentValue);

    }



    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lbldate.setText(simpleDateFormat.format(date));
    }

    private void loadBarChart() {
        XYChart.Series series = new XYChart.Series();

        series.setName("Attentance");

        series.getData().add(new XYChart.Data("Sunday",1000));
        series.getData().add(new XYChart.Data("Monday",850));
        series.getData().add(new XYChart.Data( "Tuesday", 980)) ;
        series.getData().add(new XYChart.Data( "Wednesday", 900)) ;
        series.getData().add(new XYChart.Data( "Thursday", 598)) ;
        series.getData().add(new XYChart.Data( "Friday", 500)) ;
        series.getData().add(new XYChart.Data( "Saturday", 1300)) ;


        barchart.getData().addAll(series);

    }

    private void setPiechartThigma() {
        ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
                new PieChart.Data("Students", 1500),
                new PieChart.Data("Lecturers", 30),
                new PieChart.Data("Staff", 30),
                new PieChart.Data("External users", 20));

        piechartThigma.getData().addAll(piechartData);

    }
    private void setTime() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            while(true) {
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(timenow);
                });
            }
        });
        thread.start();
    }
}
