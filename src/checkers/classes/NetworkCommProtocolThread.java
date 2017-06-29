package checkers.classes;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**



 * Created by Praca on 2017-06-18.
 */
public class NetworkCommProtocolThread extends Thread{


    public Socket socket ;
    public ObjectOutputStream out;
    public ObjectInputStream in;
    public long  currentThreadID;
    public MoveTransfer moveTransfer = new MoveTransfer();
    public  MoveTransfer tempMoveTranfer = new MoveTransfer();
    //private Consumer<Serializable> onReceiveCallback;
    public BlockingQueue<MoveTransfer> blockingQueue ;



    public boolean newDataToSend = false;
    public boolean newDataToReceive = true;


@Override
    public void run()
    {
        currentThreadID = Thread.currentThread().getId();
        System.out.println("Thread ID: "+currentThreadID + " is working");

        try
        {



            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            socket.setTcpNoDelay(true); // ???



            while(true)
            {
                if(newDataToReceive == true)
                {
                    System.out.println("Before receiving ?");
                    tempMoveTranfer = (MoveTransfer) in.readObject();
                    tempMoveTranfer.showAllData();
                   // moveTransfer = new MoveTransfer(tempMoveTranfer);



                    //if( tempMoveTranfer.equals(moveTransfer) )
                    if(tempMoveTranfer.hashCode()==moveTransfer.hashCode())
                    {
                        System.out.println("Data are the same !");
                        throw new IllegalAccessException("NOPE");
                    }
                    else
                    {

                        moveTransfer = new MoveTransfer(tempMoveTranfer) ;
                        this.newDataToReceive = false;
                        this.newDataToSend = true;
                        blockingQueue.put( new MoveTransfer(moveTransfer));
                        Thread.sleep(50);
                        System.out.println("First data received");
                    }
                }


                Thread.sleep(100);

            }

        }
         catch (SocketException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            System.out.println("Data from output are the same !!!");
        }

    }

}
