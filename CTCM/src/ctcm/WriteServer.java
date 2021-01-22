package ctcm;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteServer implements Runnable {

    Thread thread;
    HashMap<String, Utility> table;

    WriteServer(HashMap<String, Utility> map) {
        this.thread = new Thread(this);
        this.table = map;
        thread.start();
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            int idx = 0;
            String s;
            String[] arr = new String[2];
            s = in.nextLine();
            StringTokenizer st = new StringTokenizer(s, ":");
            while (st.hasMoreTokens()) {
                arr[idx++] = st.nextToken();
            }
            Utility client_utility = table.get(arr[0]);
            
            if (client_utility != null) {
                client_utility.write(arr[1]);
            }
        }
    }
}
