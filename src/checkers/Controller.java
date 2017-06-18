package checkers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class Controller implements Initializable
{

 public Button connectToServerButton;
 public GridPane checkerBoard;
 public javafx.scene.image.ImageView testImgView;
 public Dragboard dragBoard;

public StackPane p00;
 public StackPane
         field_01	,field_03	,field_05	,field_07
    ,field_10,	field_12	,field_14	,field_16
    ,field_21,	field_23	,field_25	,field_27
    ,field_30,	field_32	,field_34	,field_36
    ,field_41,	field_43	,field_45	,field_47
    ,field_50,	field_52	,field_54	,field_56
    ,field_61,	field_63	,field_65	,field_67
    ,field_70,	field_72	,field_74	,field_76;
    Image black = new Image("/images/black.jpg");
    Image white  = new Image("/images/white.jpg");
    Image blackKing = new Image("/images/blackKing.png");;
    Image whiteKing = new Image("/images/whiteKing.png");;

 ArrayList<StackPane> paneList = new ArrayList<StackPane>();
 ArrayList<FieldViewControl> fieldManager = new ArrayList<FieldViewControl>();



    public void initialize(URL location, ResourceBundle resources) {



        paneList.addAll(Arrays.asList(
                field_01	,field_03	,field_05	,field_07
                ,field_10,	field_12	,field_14	,field_16
                ,field_21,	field_23	,field_25	,field_27
                ,field_30,	field_32	,field_34	,field_36
                ,field_41,	field_43	,field_45	,field_47
                ,field_50,	field_52	,field_54	,field_56
                ,field_61,	field_63	,field_65	,field_67
                ,field_70,	field_72	,field_74	,field_76));



int holder = 0;
        for (int i = 0;i<8;i++)
        {
            if(i%2==0)
            {
                fieldManager.add(new FieldViewControl(paneList.get(holder),i,1,PawnColor.NONE));
                fieldManager.add(new FieldViewControl(paneList.get(holder+1),i,3,PawnColor.NONE));
                fieldManager.add(new FieldViewControl(paneList.get(holder+2),i,5,PawnColor.NONE));
                fieldManager.add(new FieldViewControl(paneList.get(holder+3),i,7,PawnColor.NONE));
            }
            else
            {
                fieldManager.add(new FieldViewControl(paneList.get(holder),i,0,PawnColor.NONE));
                fieldManager.add(new FieldViewControl(paneList.get(holder+1),i,2,PawnColor.NONE));
                fieldManager.add(new FieldViewControl(paneList.get(holder+2),i,4,PawnColor.NONE));
                fieldManager.add(new FieldViewControl(paneList.get(holder+3),i,6,PawnColor.NONE));
            }
holder+=4;

        }


        //fieldManager.get(4).getGridField().getChildren().add(testImgView);
        testImgView = new javafx.scene.image.ImageView(black);
 //     testImgView.setPreserveRatio(true);  // zachowuje ratio wybranego elementu
        testImgView.fitWidthProperty().bind(fieldManager.get(0).getGridField().widthProperty());
        testImgView.fitHeightProperty().bind(fieldManager.get(0).getGridField().heightProperty());
       // testImgView.drag


//        clientBoard.add(testImgView,5,5);
//        clientBoard.add(new ImageView(black),3,3);
        //fieldManager.get(0).getGridField().getChildren().add(testImgView);
        //checkerBoard.add(testImgView,0,0);


        //region White - Top
        insertImage(white,new ImageView(),0,1);
        insertImage(white,new ImageView(),0,3);
        insertImage(white,new ImageView(),0,5);
        insertImage(white,new ImageView(),0,7);

        insertImage(white,new ImageView(),1,0);
        insertImage(white,new ImageView(),1,2);
        insertImage(white,new ImageView(),1,4);
        insertImage(white,new ImageView(),1,6);

        insertImage(white,new ImageView(),2,1);
        insertImage(white,new ImageView(),2,3);
        insertImage(white,new ImageView(),2,5);
        insertImage(white,new ImageView(),2,7);

        //endregion
        //region Black

        insertImage(black,new ImageView(),5,0);
        insertImage(black,new ImageView(),5,2);
        insertImage(black,new ImageView(),5,4);
        insertImage(black,new ImageView(),5,6);

        insertImage(black,new ImageView(),6,1);
        insertImage(black,new ImageView(),6,3);
        insertImage(black,new ImageView(),6,5);
        insertImage(black,new ImageView(),6,7);


        insertImage(black,new ImageView(),7,0);
        insertImage(black,new ImageView(),7,2);
        insertImage(black,new ImageView(),7,4);
        insertImage(black,new ImageView(),7,6);
        //endregion

        //region blank initial fields

        insertImage(null,new ImageView(),3,0);
        insertImage(null,new ImageView(),3,2);
        insertImage(null,new ImageView(),3,4);
        insertImage(null,new ImageView(),3,6);
        
        
        insertImage(null,new ImageView(),4,1);
        insertImage(null,new ImageView(),4,3);
        insertImage(null,new ImageView(),4,5);
        insertImage(null,new ImageView(),4,7);



        //endregion

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


    void insertImage(Image image,ImageView inImageView,int  n,int m)
    {


        int index = retrunFieldfromFieldManagerList (n,m);

        inImageView.setImage(image);
//        inImageView.fitWidthProperty().bind(fieldManager.get(0).getGridField().widthProperty());
//        inImageView.fitHeightProperty().bind(fieldManager.get(0).getGridField().heightProperty());
        inImageView.setFitHeight(30);
        inImageView.setFitWidth(30);
        inImageView.setPreserveRatio(true);


        fieldManager.get(index).setViewedImage(inImageView);
        fieldManager.get(index).getGridField().getChildren().add(inImageView);
        setupGestureSource(inImageView);
        setupGestureTarget (fieldManager.get(index).getGridField(),inImageView);
        //checkerBoard.add(inImageView,m,n);



    }


void setupGestureTarget (final StackPane target,final ImageView targetImage)
{

    target.setOnDragOver(new EventHandler<DragEvent>()
    {
        @Override
        public void handle(DragEvent event)
        {
            dragBoard = event.getDragboard();

            if (dragBoard.hasImage())
            {

                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        }
    });


    target.setOnDragDropped(new EventHandler<DragEvent>()
    {
        @Override
        public void handle(DragEvent event)
        {
            dragBoard = event.getDragboard();
            if (dragBoard.hasImage())
            {
                target.getChildren().remove(targetImage);
                target.getChildren().add(new ImageView());
                event.setDropCompleted(true);
            }
            else
            {
                event.setDropCompleted(false);
            }
    event.consume();

        }
    });

}


void setupGestureSource(final ImageView inImageView)
    {
        inImageView.setOnDragDetected(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                dragBoard = inImageView.startDragAndDrop(TransferMode.MOVE);

                ClipboardContent content = new ClipboardContent();
                Image sourceImage = inImageView.getImage();

                content.putImage(sourceImage);
                dragBoard.setContent(content);

                event.consume();

            }
        });

        inImageView.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                inImageView.setCursor(Cursor.HAND);

            }
        });


    }



public void actualiseBySendDataToServer()
{



}







int retrunFieldfromFieldManagerList (int n,int m)
{

        for (int index =0; index<fieldManager.size();index++)
        {
            if(fieldManager.get(index).getN() == n &&fieldManager.get(index).getM() == m)
            {

                return index;
            }
        }
    return 99999;
}





}
