package student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @FXML
    AnchorPane rootPane1;
    public static String currentStudent;
    @FXML
    AnchorPane rootPane;
    @FXML
    TextField registrationNumber;
    @FXML
    TextField password;
    @FXML
    TextField accNo;
    @FXML
    Label error;

    DataInputStream din;
    DataOutputStream dout;
    BufferedReader br;
    String str1="",str2=" "; // str2 - reply from server, str1 - client message
    Socket socket ;

    @FXML
    public void setLogin(ActionEvent event) throws Exception
    {
        socket = new Socket("localhost",4569);
        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1="1";

        dout.writeUTF(str1);


        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        Scene scene = new Scene(root);
        Stage currWindow = (Stage) rootPane.getScene().getWindow();
        currWindow.setScene(scene);
        currWindow.show();
    }


    @FXML
    public void processLogin(ActionEvent event)
    {
        String registrationNumber = this.registrationNumber.getText();
        String password = this.password.getText();
        String accNo = this.accNo.getText();

        try {
            socket = new Socket("localhost",4569);

            str1 = registrationNumber + " " + accNo + " " +password;
            dout.writeUTF(str1);

            System.out.println(str1);

            str2 = din.readUTF();   //login sucess or not
            System.out.println("server says : " + str2);

            if(str2.contains("failed") || str2.contains("invalid"))
            {
                error.setText("Can't login! try again");
            }


            else
            {
                currentStudent = registrationNumber;
                Parent root = FXMLLoader.load(getClass().getResource("Student.fxml"));
                Scene scene = new Scene(root);
                Stage currWindow = (Stage) rootPane.getScene().getWindow();
                currWindow.setScene(scene);
                currWindow.show();
            }
            socket.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // error.setText("");

    }
}
