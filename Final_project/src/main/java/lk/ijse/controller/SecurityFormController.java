package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SecurityFormController {
    public TextField txtPassword;
    public AnchorPane securityPanel;
    public PasswordField pwPasword;


    public void btnOkOnAction(ActionEvent actionEvent) throws IOException {
        String password=pwPasword.getText();

        if(password.equals("Amasha@2000")){
          //  Stage stage = (Stage) securityPanel.getScene().getWindow();
           // stage.close();
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/staffForm.fxml"));
            securityPanel.getChildren().clear();
           securityPanel.getChildren().add(rootNode);
        }else{
            new Alert(Alert.AlertType.ERROR,"Wrong Password").show();
        }


    }
}
