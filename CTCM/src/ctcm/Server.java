package ctcm;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
    static HashMap<String, Utility> map = new HashMap<> ();
    
    static HashMap get_map()
    {
        return map;
    }
    
    public static void main (String[] args)
    {
        try
        {
            ServerSocket server_socket = new ServerSocket (8888);
            new WriteServer (map);

            while (true)
            {
                Socket socket = server_socket.accept ();
                Utility utl = new Utility (socket);
                new ClientThread (map, utl);
            }

        } catch (IOException ex)
        {

        }
    }
}
