package ctcm;

import java.io.*;
import java.net.*;
import java.util.*;

public class Utility
{
    public Socket socket;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;

    Utility (String ip, int port)
    {
        try
        {
            this.socket = new Socket (ip, port);
            oos = new ObjectOutputStream (socket.getOutputStream ());
            ois = new ObjectInputStream (socket.getInputStream ());

        } catch (IOException ex)
        {

        }
    }

    Utility (Socket s)
    {
        try
        {
            this.socket = s;
            oos = new ObjectOutputStream (socket.getOutputStream ());
            ois = new ObjectInputStream (socket.getInputStream ());

        } catch (IOException ex)
        {

        }
    }

    public Object read ()
    {
        Object ob = null;

        try
        {
            ob = ois.readObject ();

        } catch (IOException | ClassNotFoundException ex)
        {

        }
        return ob;
    }

    public void write (Object ob)
    {
        try
        {
            oos.writeObject (ob);

        } catch (IOException ex)
        {

        }
    }
}
