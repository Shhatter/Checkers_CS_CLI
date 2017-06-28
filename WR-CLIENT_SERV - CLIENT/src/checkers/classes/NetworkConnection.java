package checkers.classes;

import java.io.Serializable;
import java.net.Socket;
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


    public void startConnection (Socket socket) throws Exception
    {
        networkCommProtocolThread.setName("CONNECTION THREAD");
        networkCommProtocolThread.socket = socket;
        networkCommProtocolThread.setDaemon(true);
        networkCommProtocolThread.start();
    }

    public void sendData(Serializable data) throws Exception
    {
        networkCommProtocolThread.out.writeObject(data);
    }
//    public Consumer<Serializable> getData() throws Exception
//    {
//        return consumer;
//    }




}
