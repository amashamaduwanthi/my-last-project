package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainFormController  {

    public AnchorPane mainPnael;
    public ProgressBar ProcessBarId;
    public ProgressIndicator procesCalId;
    public Label mylabel;
    double progress;


    public void btnRunOnAction(ActionEvent actionEvent) throws IOException {

       Parent rootNode= FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage= (Stage) mainPnael.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();
    }


}
