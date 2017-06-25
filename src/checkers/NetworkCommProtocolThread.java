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

    public String [] commands = {"LOCK_LAYOUT","UNLOCK_LAYOUT","START_YOUR_MOVE","MOVE_ENDED","YOU_WIN","YOU_LOOSE"};

    public NetworkCommProtocolThread(Socket socket, String threadNumbner)
    {
        super(threadNumbner);
        this.socket = socket;
    }

    public NetworkCommProtocolThread()
    {

    }




@Override
    public void run()
    {
        System.out.println("Dziala watek klienta ");

        try
        {
            socket = new Socket("127.0.0.1",5555) ;
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            socket.setTcpNoDelay(true); // ???


            //#################
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connection monitor");
            alert.setHeaderText("Status");
            alert.setContentText("Connected!!!");
            System.out.println("Połączono !!!");
            //#################



            while(true)
            {

            }

        }
        catch(Exception e)

        {

        }







/*
        try {


            System.out.println("Dziala try ");

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            this.socket = socket;
            this.out = out;

//            socket.setTcpNoDelay(true); może się przyda - może nie.

        while (true)
        {

            if (Thread.activeCount()==4)
            {
                System.out.println("We have both players online !");
                Serializable data = (Serializable) in.readObject();
                consumer.accept(data);

            }
                else
            {
                System.out.println("Not enough players !");
            }




//            Serializable data = (Serializable) in.readObject();
//            consumer.accept(data);



        }
//        socket.close();

        } catch (IOException e)
        {
            e.printStackTrace();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {


        }*/


    }








}
