package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
      Parent rootNode= FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"));
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Main Form");
        stage.setResizable(false);
        stage.show();



    }
}
