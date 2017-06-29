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

    public NetworkCommProtocolThread networkCommProtocolThread = new NetworkCommProtocolThread();

    public NetworkConnection(Consumer<Serializable> consumer) {
        networkCommProtocolThread.setDaemon(true);
    }


    public NetworkConnection()
    {
    }


    public void startConnection (Socket socket, BlockingQueue<MoveTransfer> blockingDeque)
    {
        networkCommProtocolThread.setName("CONNECTION THREAD");
        networkCommProtocolThread.socket = socket;
        networkCommProtocolThread.setDaemon(true);
        networkCommProtocolThread.blockingQueue = blockingDeque;

        networkCommProtocolThread.start();
    }

    public void sendData(Serializable data)
    {
        try
        {
            networkCommProtocolThread.out.writeObject(data);
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
