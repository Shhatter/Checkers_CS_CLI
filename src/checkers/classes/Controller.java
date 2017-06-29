package checkers.classes;
import checkers.enums.PawnColor;
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
import sun.font.TextLabel;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Controller implements Initializable
{
// FXML
 public Button connectToServerButton,startGameButton;
 public GridPane checkerBoard;
 public javafx.scene.image.ImageView testImgView;
 public NetworkConnection networkConnection = new NetworkConnection();
 public Socket clientSocket;
 public LinkedBlockingQueue queue;
 public BlockingQueue<MoveTransfer> blockingQueue = new LinkedBlockingQueue<MoveTransfer>();;
 public TextField portTextField, ipTextField;
 public TextLabel playerMoveLabel;



    public StackPane
            field_01	,field_03	,field_05	,field_07
            ,field_10,	field_12	,field_14	,field_16
            ,field_21,	field_23	,field_25	,field_27
            ,field_30,	field_32	,field_34	,field_36
            ,field_41,	field_43	,field_45	,field_47
            ,field_50,	field_52	,field_54	,field_56
            ,field_61,	field_63	,field_65	,field_67
            ,field_70,	field_72	,field_74	,field_76;
    ArrayList<StackPane> paneList = new ArrayList<StackPane>();

// FXML


    Image black = new Image("/images/black.jpg");
    Image white  = new Image("/images/white.jpg");
    Image blackKing = new Image("/images/blackKing.png");
    Image whiteKing = new Image("/images/whiteKing.png/");
    String playerID;

 ArrayList<FieldViewControl> fieldManager = new ArrayList<FieldViewControl>();
 MoveTransfer moveTransferInController = new MoveTransfer();



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





//        for (int i = 0; i<8;i++)
//        {
//            for (int j = 0 ;j<4;j++)
//            {
//                if (i%2==0)
//                {
//                    for (int m = 1;m<=7;m=m+2)
//                    {
//                        fieldManager.add(new FieldViewControl(paneList.get(holder++),i,m,PawnColor.NONE));
//                    }
//                    continue;
//                }
//
//                else{
//                    for (int m = 0;m<=6;m=m+2)
//                                    {
//                                        fieldManager.add(new FieldViewControl(paneList.get(holder++),i,m,PawnColor.NONE));
//                                        continue;
//                                    }
//                }
//
//            }
//        }


        int holder = 0;

        for (int i = 0;i<8;i++)
        {


            if(i%2==0)
            {

                fieldManager.add(new FieldViewControl(paneList.get(holder),i,1, PawnColor.NONE));
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

        testImgView = new javafx.scene.image.ImageView(black);
        testImgView.fitWidthProperty().bind(fieldManager.get(0).getGridField().widthProperty());
        testImgView.fitHeightProperty().bind(fieldManager.get(0).getGridField().heightProperty());



        //region White - Top
/*
        insertImage(white,new ImageView(),0,1,PawnColor.WHITE);
        insertImage(white,new ImageView(),0,3,PawnColor.WHITE);
        insertImage(white,new ImageView(),0,5,PawnColor.WHITE);
        insertImage(white,new ImageView(),0,7,PawnColor.WHITE);

        insertImage(white,new ImageView(),1,0,PawnColor.WHITE);
        insertImage(white,new ImageView(),1,2,PawnColor.WHITE);
        insertImage(white,new ImageView(),1,4,PawnColor.WHITE);
        insertImage(white,new ImageView(),1,6,PawnColor.WHITE);

        insertImage(white,new ImageView(),2,1,PawnColor.WHITE);
        insertImage(white,new ImageView(),2,3,PawnColor.WHITE);
        insertImage(white,new ImageView(),2,5,PawnColor.WHITE);
        insertImage(white,new ImageView(),2,7,PawnColor.WHITE);

        //endregion
        //region Black

        insertImage(black,new ImageView(),5,0,PawnColor.BLACK);
        insertImage(black,new ImageView(),5,2,PawnColor.BLACK);
        insertImage(black,new ImageView(),5,4,PawnColor.BLACK);
        insertImage(black,new ImageView(),5,6,PawnColor.BLACK);

        insertImage(black,new ImageView(),6,1,PawnColor.BLACK);
        insertImage(black,new ImageView(),6,3,PawnColor.BLACK);
        insertImage(black,new ImageView(),6,5,PawnColor.BLACK);
        insertImage(black,new ImageView(),6,7,PawnColor.BLACK);


        insertImage(black,new ImageView(),7,0,PawnColor.BLACK);
        insertImage(black,new ImageView(),7,2,PawnColor.BLACK);
        insertImage(black,new ImageView(),7,4,PawnColor.BLACK);
        insertImage(black,new ImageView(),7,6,PawnColor.BLACK);
        //endregion

        //region blank initial fields

        insertImage(null,new ImageView(),3,0,PawnColor.NONE);
        insertImage(null,new ImageView(),3,2,PawnColor.NONE);
        insertImage(null,new ImageView(),3,4,PawnColor.NONE);
        insertImage(null,new ImageView(),3,6,PawnColor.NONE);
        
        
        insertImage(null,new ImageView(),4,1,PawnColor.NONE);
        insertImage(null,new ImageView(),4,3,PawnColor.NONE);
        insertImage(null,new ImageView(),4,5,PawnColor.NONE);
        insertImage(null,new ImageView(),4,7,PawnColor.NONE);


        for (FieldViewControl x: fieldManager)
        {
            setupGestureSource(x);
            setupGestureTarget(x);

        }
*/



    }

    @FXML
    public void searchForServer(ActionEvent event) throws IOException
    {
        try
        {
            clientSocket = new Socket(ipTextField.getText(), Integer.parseInt(portTextField.getText())) ;

//            clientSocket = new Socket(ipTextField.getText(), Integer.parseInt(ipTextField.getText())) ;
         //   clientSocket = new Socket("127.0.0.1", Integer.parseInt(ipTextField.getText())) ;

            networkConnection.startConnection(clientSocket,blockingQueue);
            //blockingQueue.take();

//            moveTransferInController = new MoveTransfer(networkConnection.networkCommProtocolThread.moveTransfer);

            System.out.println("Blocking queue send data");


//            while (networkConnection.networkCommProtocolThread.moveTransfer.equals(moveTransferInController))
//            {
//         //       System.out.println("The object from server didn't change yet :/ ");
//            }


        }
        catch  (NullPointerException e)
        {
            System.out.println("pusty bufor !/");


        }
        catch (IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connection monitor");
            alert.setHeaderText("Status");
            alert.setContentText("Connection problem!");
            alert.showAndWait();


        } catch (Exception e)
        {
            e.printStackTrace();
        }

        connectToServerButton.setDisable (true);
        startGameButton.setDisable(false);
    }

    @FXML
    public void startGame(ActionEvent event)
    {
        try
        {
            moveTransferInController = new MoveTransfer(networkConnection.networkCommProtocolThread.blockingQueue.take());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if(moveTransferInController.getColor() == PawnColor.WHITE)
        {
            fillTheBoard(PawnColor.WHITE,white,PawnColor.BLACK,black);
        }
        else
        {

            fillTheBoard(PawnColor.BLACK,black,PawnColor.WHITE,white);

        }



        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gane status");
        alert.setHeaderText("Status");
        alert.setContentText("User want to play");

        alert.showAndWait();
    }

    private void fillTheBoard(PawnColor top,Image imageTop,PawnColor bottom,Image imageBottom)
    {

        insertImage(imageTop,new ImageView(),0,1,top);
        insertImage(imageTop,new ImageView(),0,3,top);
        insertImage(imageTop,new ImageView(),0,5,top);
        insertImage(imageTop,new ImageView(),0,7,top);

        insertImage(imageTop,new ImageView(),1,0,top);
        insertImage(imageTop,new ImageView(),1,2,top);
        insertImage(imageTop,new ImageView(),1,4,top);
        insertImage(imageTop,new ImageView(),1,6,top);

        insertImage(imageTop,new ImageView(),2,1,top);
        insertImage(imageTop,new ImageView(),2,3,top);
        insertImage(imageTop,new ImageView(),2,5,top);
        insertImage(imageTop,new ImageView(),2,7,top);

        //endregion
        //region Black

        insertImage(imageBottom,new ImageView(),5,0,bottom);
        insertImage(imageBottom,new ImageView(),5,2,bottom);
        insertImage(imageBottom,new ImageView(),5,4,bottom);
        insertImage(imageBottom,new ImageView(),5,6,bottom);

        insertImage(imageBottom,new ImageView(),6,1,bottom);
        insertImage(imageBottom,new ImageView(),6,3,bottom);
        insertImage(imageBottom,new ImageView(),6,5,bottom);
        insertImage(imageBottom,new ImageView(),6,7,bottom);


        insertImage(imageBottom,new ImageView(),7,0,bottom);
        insertImage(imageBottom,new ImageView(),7,2,bottom);
        insertImage(imageBottom,new ImageView(),7,4,bottom);
        insertImage(imageBottom,new ImageView(),7,6,bottom);
        //endregion

        //region blank initial fields

        insertImage(null,new ImageView(),3,0,PawnColor.NONE);
        insertImage(null,new ImageView(),3,2,PawnColor.NONE);
        insertImage(null,new ImageView(),3,4,PawnColor.NONE);
        insertImage(null,new ImageView(),3,6,PawnColor.NONE);


        insertImage(null,new ImageView(),4,1,PawnColor.NONE);
        insertImage(null,new ImageView(),4,3,PawnColor.NONE);
        insertImage(null,new ImageView(),4,5,PawnColor.NONE);
        insertImage(null,new ImageView(),4,7,PawnColor.NONE);


        for (FieldViewControl x: fieldManager)
        {
            setupGestureSource(x);
            setupGestureTarget(x);

        }
    }


    void insertImage(Image image,ImageView inImageView,int  n,int m,PawnColor pwnColor)
    {


        int index = retrunFieldfromFieldManagerList (n,m);

        inImageView.setImage(image);
        inImageView.setFitHeight(30);
        inImageView.setFitWidth(30);
        inImageView.setPreserveRatio(true);

        fieldManager.get(index).setViewedImage(inImageView);
        fieldManager.get(index).getGridField().getChildren().add(inImageView);
        fieldManager.get(index).setPawnColor(pwnColor);




    }

    /*
      Gesture management !!!!!!!!!!!!!!!!!!!!!!!!!!!!
    */
    void setupGestureSource(final FieldViewControl source)
    {
        source.getViewedImage().setOnDragDetected(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Dragboard dragBoard = source.getViewedImage().startDragAndDrop(TransferMode.MOVE);

                ClipboardContent content = new ClipboardContent();
                Image sourceImage = source.getViewedImage().getImage();


                content.putImage(sourceImage);
                dragBoard.setContent(content);
                source.getGridField().getChildren().removeAll();
//                pawnInitialField(source.getN(),source.getM(),source.getPawnColor(),playerID);
                //moveTransferInController[0].setAllData(source.getN(),source.getM(),source.getPawnColor(),playerID);



                event.consume();

            }
        });


        source.getViewedImage().setOnDragDone(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent event)
            {
                if (event.getTransferMode() == TransferMode.MOVE)
                {
                    source.getViewedImage().setImage(null);
                    source.setPawnColor(PawnColor.NONE);

                }
                event.consume();
            }
        });

        source.getViewedImage().setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                source.getViewedImage().setCursor(Cursor.HAND);
            }
        });


    }



    void setupGestureTarget (final FieldViewControl target)
{

    target.getGridField().setOnDragOver(new EventHandler<DragEvent>()
    {
        @Override
        public void handle(DragEvent event)
        {
            Dragboard dragBoard = event.getDragboard();

            if (dragBoard.hasImage())
            {

                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        }
    });


    target.getGridField().setOnDragDropped(new EventHandler<DragEvent>()
    {
        @Override
        public void handle(DragEvent event)
        {
            Dragboard dragBoard = event.getDragboard();
            if (dragBoard.hasImage() && target.getPawnColor() ==PawnColor.NONE)
            {

//                pawnDestinationField(target.getN(),target.getM(),target.getPawnColor(),playerID);
                /////moveTransferInController[1].setAllData(target.getN(),target.getM(),target.getPawnColor(),playerID);

            target.getViewedImage().setImage(dragBoard.getImage());

            target.getGridField().getChildren().removeAll();



/*               // ((FieldViewControl) event.getGestureSource())
                target.getGridField().getChildren().removeAll();
                target.getGridField().getChildren().add(new ImageView());
                ;*/

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

    /*
      Gesture management !!!!!!!!!!!!!!!!!!!!!!!!!!!!
    */

/*public void pawnInitialField(int n,int m,PawnColor color,String owner)
{

}
public void pawnDestinationField(int n,int m,PawnColor color,String owner)
{

}*/


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



void blockAllControlsOnEnemyMove()
{



}

public void setUpInitialPawnLocation(MoveTransfer moveTransfer)
{
    
    

}





}
