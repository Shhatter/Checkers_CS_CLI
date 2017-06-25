package checkers;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Created by Praca on 2017-06-25.
 */
public class NetworkConnection
{

    //public Consumer<Serializable> consumer;
    public NetworkCommProtocolThread networkCommProtocolThread = new NetworkCommProtocolThread();

    public NetworkConnection(Consumer<Serializable> consumer) {
      //  this.consumer = consumer;
        networkCommProtocolThread.setDaemon(true);
    }


    public NetworkConnection()
    {
        networkCommProtocolThread.setDaemon(true);
    }


    public void startConnection () throws Exception
    {
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
