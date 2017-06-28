package checkers;



import checkers.enums.PawnColor;
import javafx.scene.control.Alert;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

/**



 * Created by Praca on 2017-06-18.
 */
public class NetworkCommProtocolThread extends Thread{


    public Socket socket ;
    public ObjectOutputStream out;
    public ObjectInputStream in;
    long  currentThreadID;
    MoveTransfer moveTransfer = new MoveTransfer();
    MoveTransfer tempMoveTranfer = new MoveTransfer();
    private Consumer<Serializable> onReceiveCallback;


    boolean newDataToSend = false;
    boolean newDataToReceive = true;

    //public String [] commands = {"LOCK_LAYOUT","UNLOCK_LAYOUT","START_YOUR_MOVE","MOVE_ENDED","YOU_WIN","YOU_LOOSE"};

//    public NetworkCommProtocolThread(Socket socket, String threadNumbner)
//    {
//
//        super(threadNumbner);
//        this.socket = socket;
//        this.newDataToReceive = true;
//    }


@Override
    public void run()
    {
        currentThreadID = Thread.currentThread().getId();
        System.out.println("Thread ID: "+currentThreadID + " is working");

        try
        {



            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            //socket.setTcpNoDelay(true); // ???




            while(true)
            {
                if(newDataToReceive == true)
                {
                    System.out.println("Before receiving ?");
                    tempMoveTranfer = (MoveTransfer) in.readObject();

/*                    Serializable data = (Serializable) in.readObject();
                    onReceiveCallback.accept(data);*/

                    System.out.println("After receiving");
                    if( tempMoveTranfer.equals(moveTransfer) )
                    {
                        System.out.println("Data are the same !");
                    }
                    else
                    {

                        moveTransfer = new MoveTransfer(tempMoveTranfer) ;
                        newDataToReceive = false;
                        System.out.println("First data received");


                    }



                }
                //else if(newDataToSend == true;)



            }

        }
        catch(Exception e)

        {

        }

    }

}
