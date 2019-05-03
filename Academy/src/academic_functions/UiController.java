package academic_functions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UiController {

    @FXML
    AnchorPane rootPane;

    public void setAddStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/academic_functions/AddStudent.fxml"));
        Scene scene = new Scene(root);
        Stage currWindow = (Stage) rootPane.getScene().getWindow();
        currWindow.setScene(scene);
        currWindow.show();
    }

    public void setRemoveStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/academic_functions/RemoveStudent.fxml"));
        Scene scene = new Scene(root);
        Stage currWindow = (Stage) rootPane.getScene().getWindow();
        currWindow.setScene(scene);
        currWindow.show();
    }

}
