package ctcm;

import java.util.*;

public class WriteThread implements Runnable
{
    Thread thread;
    Utility client_utility;

    WriteThread (Utility utl)
    {
        this.thread = new Thread (this);
        thread.start ();
        this.client_utility = utl;
    }

    public void run ()
    {
        while (true)
        {
            String msg;
            Scanner in = new Scanner(System.in);
            msg = in.nextLine ();
            client_utility.write (msg);
        }
    }
}