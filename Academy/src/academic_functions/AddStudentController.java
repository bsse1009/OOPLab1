package academic_functions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import student_information.Student;
import student_information.StudentController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {
    @FXML
    TextField name;
    @FXML
    TextField roll;
    @FXML
    TextField cls;
    @FXML
    TextField cgpa;
    @FXML
    TextField registrationNumber;
    @FXML
    PasswordField password;
    @FXML
    Label error;
    @FXML
    AnchorPane rootPane;

    public void setAdd(ActionEvent event) throws IOException {
        String name = this.name.getText();
        String roll = this.roll.getText();
        String cls = this.cls.getText();
        String cgpa = this.cgpa.getText();
        String registrationNumber = this.registrationNumber.getText();
        String password = this.password.getText();

        Student student = new Student();
        student.setName(name);
        student.setRoll(roll);
        student.setCls(cls);
        student.setCls(cgpa);
        student.setRegistrationNumber(registrationNumber);
        student.setPassword(password);

        StudentController studentController = new StudentController();
        boolean isAdded = studentController.addStudent(student);

        if(isAdded)
            error.setText("Successfully Added.");
        else
            error.setText("Can't Add student_information! try again");
    }

    public void setBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/academic_functions/UI.fxml"));
        Scene scene = new Scene(root);
        Stage currWindow = (Stage) rootPane.getScene().getWindow();
        currWindow.setScene(scene);
        currWindow.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        error.setText("");
    }
}
