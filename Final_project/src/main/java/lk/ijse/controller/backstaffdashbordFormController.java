package lk.ijse.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bao.custom.BOFactory;
import lk.ijse.bao.custom.DashboardBO;
import lk.ijse.bao.custom.impl.DashboardBOImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class backstaffdashbordFormController {
    public AnchorPane panel;
    public Label lbldate;
    public PieChart piechartThigma;
    public BarChart barchart;
    public Label lblTotalStudent;
    public Label lblTotalLecturer;
    public Label lblTotalHalls;
    public Label lblTime;
    DashboardBO dashboardBO= (DashboardBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.DASHBOARD);
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
