package ctcm;

import java.util.*;

public class ClientThread implements Runnable
{
    Thread thread;
    HashMap<String, Utility> table;
    Utility utl;
    String client_name;

    ClientThread (HashMap<String, Utility> map, Utility utl)
    {
        thread = new Thread (this);
        this.table = map;
        this.utl = utl;
        thread.start ();
    }

    public void run ()
    {
        client_name = (String) utl.read ();
        table.put (client_name, utl);
        System.out.println ("[" + client_name + "] Enter");
        new ReadServer (utl, table, client_name);
    }
}
