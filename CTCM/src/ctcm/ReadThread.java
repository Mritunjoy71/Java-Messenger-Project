package ctcm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;

public class ReadThread implements Runnable
{
    Thread thread;
    Utility client_utility;
    TextArea txt_area;

    ReadThread (Utility utl, TextArea area)
    {
        this.thread = new Thread (this);
        thread.start ();
        this.client_utility = utl;
        txt_area = area;
    }

    public void run ()
    {
        while (true)
        {
            String msg = (String) client_utility.read ();
            String fstr=msg+"\n";
            txt_area.appendText(msg+"\n");
            String path="src\\chats.txt";
            try {
                Files.write( Paths.get(path), fstr.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                Logger.getLogger(WriteServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
