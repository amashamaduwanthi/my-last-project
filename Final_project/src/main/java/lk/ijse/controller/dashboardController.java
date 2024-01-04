package lk.ijse.controller;

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
import lk.ijse.dao.Custom.AdminDAO;
import lk.ijse.dao.Custom.HallDAO;
import lk.ijse.dao.Custom.Impl.AdminDAOImpl;
import lk.ijse.dao.Custom.Impl.HallDAOImpl;
import lk.ijse.dao.Custom.Impl.LecturerDAOImpl;
import lk.ijse.dao.Custom.Impl.StudentDAOImpl;
import lk.ijse.dao.Custom.LecturerDAO;
import lk.ijse.dao.Custom.StudentDAO;
import lk.ijse.dao.StudentModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class dashboardController {
    public AnchorPane panel;
    public Label dashboardPanel;
    public PieChart piechartThigma;
    public Label lbldate;
    public Label lblTotalStaff;
    public Label lblTotalStudent;
    public Label lblTotalLecturer;
    public Label lblTotalHalls;
    public Label lblTime;
    public BarChart barchart;
   AdminDAO adminDAOImpl= new AdminDAOImpl();
   HallDAO hallDAOImpl=new HallDAOImpl();
    private LecturerDAO lecturerDAOImpl=new LecturerDAOImpl();
    StudentDAO studentDAOImpl= new StudentDAOImpl();

    public void loadBarChart(){
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

    public void btnlogoutOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode=FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage= (Stage) dashboardPanel.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();


    }

    public void btnMangeStaffOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/securityForm.fxml"));
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

    public void btnStaffOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/securityForm.fxml"));
        panel.getChildren().clear();
        panel.getChildren().add(rootNode);
    }

    public void btnManageClassOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/classForm.fxml"));
        panel.getChildren().clear();
        panel.getChildren().add(rootNode);
    }

    public void setPiechartThigma(){
        ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
                new PieChart.Data("Students", 1500),
                new PieChart.Data("Lecturers", 30),
                new PieChart.Data("Staff", 30),
                new PieChart.Data("External users", 20));

        piechartThigma.getData().addAll(piechartData);
    }
    public void initialize(){
        setPiechartThigma();
       loadBarChart();
        loadDate();
        loadTotalStaff();
        loadTotalStudent();
        loadTotalLecturer();
        loadTotalHalls();
        setTime();
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

    private void loadTotalHalls() {
        String HallValue="0";
        try{
            HallValue= hallDAOImpl.searchTotalHall();
        }catch (Exception e){
            HallValue="0";
        }

        lblTotalHalls.setText(HallValue);

    }

    private void loadTotalLecturer() {
        String lecturerValue="0";
        try{
            lecturerValue= lecturerDAOImpl.searchTotalLecturer();
        }catch (Exception e){
            lecturerValue="0";
        }

        lblTotalLecturer.setText(lecturerValue);

    }

    private void loadTotalStudent() {
        String studentValue="0";
        try{

            studentValue= studentDAOImpl.searchTotalStudent();
        }catch (Exception e){
            studentValue="0";
        }

        lblTotalStudent.setText(studentValue);

    }

    private void loadTotalStaff() {
        String staffValue="0";
        try{
            staffValue=adminDAOImpl.searchTotalStaff();
        }catch (Exception e){
            staffValue="0";
        }

        lblTotalStaff.setText(staffValue);
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lbldate.setText(simpleDateFormat.format(date));
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/backdashboardForm.fxml"));
        panel.getChildren().clear();
        panel.getChildren().add(rootNode);
    }

    //new
}
