package checkers.classes;


import checkers.enums.MoveTransferOrder;

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
public class NetworkCommProtocolThread extends Thread
{


    public boolean isReader;
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;
    public long currentThreadID;
    public MoveTransfer moveTransfer;
    public MoveTransfer tempMoveTranfer = new MoveTransfer();
    //private Consumer<Serializable> onReceiveCallback;
    public BlockingQueue<MoveTransfer> blockingQueue;


    public boolean newDataToSend = false;
    public boolean newDataToReceive = true;

    public NetworkCommProtocolThread(MoveTransfer moveTransfer, boolean isReader)

    {
        this.isReader = isReader;
        this.moveTransfer = moveTransfer;
    }


    @Override
    public void run()
    {

        if (isReader)
        {
            currentThreadID = Thread.currentThread().getId();
            System.out.println("Read Thread ID: " + currentThreadID + " is working");

            try
            {


                in = new ObjectInputStream(socket.getInputStream());
                socket.setTcpNoDelay(true); // ???


                while (moveTransfer.getOrder() != MoveTransferOrder.END_OF_GAME)
                {
                    if (newDataToReceive == true)
                    {
                        System.out.println("Before receiving ?");
                        tempMoveTranfer = (MoveTransfer) in.readObject();
                        tempMoveTranfer.showAllData();
                        // moveTransfer = new MoveTransfer(tempMoveTranfer);


                        //if( tempMoveTranfer.equals(moveTransfer) )
                        if (tempMoveTranfer.hashCode() == moveTransfer.hashCode())
                        {
                            System.out.println("Data are the same !");
                            throw new IllegalAccessException("NOPE");
                        } else
                        {

                            moveTransfer = new MoveTransfer(tempMoveTranfer);
                            this.newDataToReceive = false;
                            this.newDataToSend = true;
                            blockingQueue.put(new MoveTransfer(moveTransfer));
//                            Thread.sleep(50);
                            System.out.println("First data received");
                        }
                    }


                    Thread.sleep(100);

                }

            } catch (SocketException e)
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
            } finally
            {
                currentThreadID = Thread.currentThread().getId();
                System.out.println("Read Thread ID: " + currentThreadID + "Completed it work");
                try
                {
                    socket.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        } else
        {
            currentThreadID = Thread.currentThread().getId();
            System.out.println("Write Thread ID: " + currentThreadID + " is working");
            try
            {
                out = new ObjectOutputStream(socket.getOutputStream());
                socket.setTcpNoDelay(true); //może się przyda - może nie.


                while (moveTransfer.getOrder() != MoveTransferOrder.END_OF_GAME.END_OF_GAME)
                {
                    if (newDataToSend == true)
                    {

                        out.reset();
                        out.writeObject(moveTransfer);
                        out.flush();
                        newDataToSend = false;
                        System.out.println("Data from thread " + currentThreadID + " has been sent");


                    }


                    Thread.sleep(100);
                }


            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                currentThreadID = Thread.currentThread().getId();
                System.out.println("Write Thread ID: " + currentThreadID + "Completed it work");
                try
                {
                    socket.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }




            /*
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
*/

        }

    }

}
