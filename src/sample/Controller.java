package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable
{

 public Button connectToServerButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void searchForServer(ActionEvent event) throws IOException
    {
/*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Connection monitor");
        alert.setHeaderText("Status");
        alert.setContentText("Connected");

        alert.showAndWait();
*/
        try {
            Socket checkerSocket = new Socket("127.0.0.1",5555);
            PrintWriter out = new PrintWriter(checkerSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader((checkerSocket.getInputStream())));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connection monitor");
            alert.setHeaderText("Status");
            alert.setContentText("Server unavaiable");

            alert.showAndWait();

        }


    }




}
