package student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    TextArea certificateText;
    @FXML
    AnchorPane bankAccountHolder;
    @FXML
    AnchorPane certificateHolder;
    @FXML
    TextField accountNumber;
    @FXML
    PasswordField password;
    @FXML
    Label error;
    @FXML
    Label save;

    String cer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        save.setText("");
        try {
            Socket socket = new Socket(Main.IP, Main.PORT);
            String command = "initializeCertificate";
            String delimiter = "?";
            String sendingData = "" + delimiter + "" + delimiter + command + delimiter + LogInController.currentStudent;

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(sendingData);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            String receivingData = (String) objectInputStream.readObject();
            String[] data = receivingData.split("\\?");

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();

            certificateText.setText(data[3]);
            cer = data[3];


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        certificateHolder.setVisible(false);
        bankAccountHolder.setVisible(true);
        error.setText("");
    }

    public void setDownloadCer(ActionEvent event) throws IOException {
        File file = new File(LogInController.currentStudent + ".txt");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(cer.getBytes());
        fout.close();
        save.setText("File saved to " + file.getAbsolutePath());

    }

    public void setDownload(ActionEvent event)
    {
        String accountNumber = this.accountNumber.getText();
        String password = this.password.getText();

        if(accountNumber.isEmpty())
            accountNumber = "";
        if(password.isEmpty())
            password = "";

        try{

            Socket socket = new Socket("localhost", 8080);

            String command = "loginFromModule";
            String delimiter = "?";
            String sendingData = "" + delimiter + "" + delimiter + command + delimiter + accountNumber + delimiter + password;

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(sendingData);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            String receivingData = (String) objectInputStream.readObject();
            String[] data = receivingData.split("\\?");

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();



            if(data[3].equals("true"))
            {
                error.setText("Login successful.");

                if(data[4].equals("false"))
                    error.setText("Insufficient balance!");
                else{

                    bankAccountHolder.setVisible(false);
                    certificateHolder.setVisible(true);

                }

            }

            else
                error.setText("Can't login! try again");


        }catch(Exception e)
        {

        }

    }
}
