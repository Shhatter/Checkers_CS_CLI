package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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
 public GridPane clientBoard;
 public javafx.scene.image.ImageView testImgView;


 public StackPane
         field_01	,field_03	,field_05	,field_07
    ,field_10,	field_12	,field_14	,field_16
    ,field_21,	field_23	,field_25	,field_27
    ,field_30,	field_32	,field_34	,field_36
    ,field_41,	field_43	,field_45	,field_47
    ,field_50,	field_52	,field_54	,field_56
    ,field_61,	field_63	,field_65	,field_67
    ,field_70,	field_72	,field_74	,field_76;



    public void initialize(URL location, ResourceBundle resources) {

 Image black = new Image("/images/black.jpg");
 testImgView =  new javafx.scene.image.ImageView(black);
 testImgView.setPreserveRatio(true);

 //clientBoard.getChildren().






 /* testImgView.fitWidthProperty().bind(p00.widthProperty());
 testImgView.fitHeightProperty().bind(p00.heightProperty()) ;
 //p00.getChildren().add(testImgView);*/

//testImgView.fitWidthProperty().bind(clientBoard.getChildren().get(0+0));
//clientBoard.add(testImgView,5,5);


 /*
  testImgView.fitWidthProperty();
  testImgView.fitHeightProperty();

clientBoard.add(testImgView,5,5);
 testImgView.setFitHeight(0);
 */






    }

    @FXML
    public void searchForServer(ActionEvent event) throws IOException
    {

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
