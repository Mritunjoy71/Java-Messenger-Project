package ctcm;

import java.util.*;

public class ReadServer implements Runnable
{
    Thread thread;
    Utility client_utility;
    HashMap<String, Utility> table;
    String client_name;

    ReadServer (Utility utl, HashMap<String, Utility> table, String name)
    {
        this.thread = new Thread (this);
        thread.start ();
        this.table = table;
        client_name = name;
        this.client_utility = utl;
    }

    public void run ()
    {
        while (true)
        {
            int idx = 0;
            String s = (String) client_utility.read ();
            String[] arr = new String[2];
            StringTokenizer st = new StringTokenizer (s, ":");
            while (st.hasMoreTokens ())
            {
                arr[idx++] = st.nextToken ();
            }
            Utility reciver_utl = table.get (arr[0]);
            reciver_utl.write ("From " + client_name + ": " + arr[1]);
        }
    }
}
