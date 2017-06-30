package checkers.classes;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * Created by Praca on 2017-06-25.
 */
public class NetworkConnection
{

    public MoveTransfer moveTransfer = new MoveTransfer();
    public NetworkCommProtocolThread netComPortThcREAD = new NetworkCommProtocolThread(moveTransfer,true);
    public NetworkCommProtocolThread netComPortThrWRITE = new NetworkCommProtocolThread(moveTransfer,false);


    public NetworkConnection(Consumer<Serializable> consumer) {
        netComPortThcREAD.setDaemon(true);
    }


    public NetworkConnection()
    {
    }


    public void startConnection (Socket socket, BlockingQueue<MoveTransfer> blockingQueue)
    {
        netComPortThcREAD.setName("RECEIVE THREAD ACTIVE ");
        netComPortThcREAD.socket = socket;
        netComPortThcREAD.setDaemon(true);
        netComPortThcREAD.blockingQueue = blockingQueue;

        netComPortThcREAD.start();


        netComPortThrWRITE.setName("SEND THREAD ACTIVE ");
        netComPortThrWRITE.socket = socket;
        netComPortThrWRITE.setDaemon(true);
        netComPortThrWRITE.blockingQueue = blockingQueue;

        netComPortThrWRITE.start();
        
        
        
        
    }

    public void sendData(Serializable data)
    {
        try
        {
            netComPortThcREAD.out.writeObject(data);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
//    public Consumer<Serializable> getData() throws Exception
//    {
//        return consumer;
//    }




}
