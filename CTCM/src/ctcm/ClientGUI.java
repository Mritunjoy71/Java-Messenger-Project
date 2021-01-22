package ctcm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientGUI extends Application
{
    Group group;
    Scene scene;
    public String users=null;
    Utility client_utility;
    HashMap<String, Utility> table;

    public void start (Stage primaryStage) throws Exception
    {

        TextField text_field = new TextField ("Enter Your Name");
        text_field.setLayoutX (170.0);
        text_field.setLayoutY (200.0);
        text_field.setPrefSize (200, 50);
        Button enter = new Button ("Enter");
        enter.setLayoutX (170.0);
        enter.setLayoutY (255.0);
        group = new Group (text_field, enter);
        scene = new Scene (group, 500, 500);
        scene.setFill (Color.BROWN);
        primaryStage.setTitle ("Client-Window");
        enter.setOnAction (e ->
        {
            client_utility = new Utility ("127.0.0.1", 8888);
            String client_name = text_field.getText ();
            client_utility.write (client_name);
            chat_screen (primaryStage, client_name);
            users=client_name;
            String active=client_name+" ";
            String path="src\\users.txt";
            try {
                Files.write( Paths.get(path), active.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                Logger.getLogger(WriteServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        ;
        primaryStage.setScene (scene);
        primaryStage.show ();
    }

    public static void main (String[] args)
    {
        launch (args);
    }

    void chat_screen (Stage stage, String name)
    {
        TextField message_field = new TextField ();
        message_field.setFont (new Font (14));
        message_field.setLayoutX (10);
        message_field.setLayoutY (450);
        message_field.setPrefSize (250, 50);
        TextArea recieve_message = new TextArea ();
        recieve_message.setEditable (false);
        recieve_message.setFont (new Font (14));
        recieve_message.setLayoutX (10);
        recieve_message.setLayoutY (20);
        recieve_message.setPrefSize (250, 300);
        TextArea online = new TextArea ();
        online.setEditable (false);
        online.setFont (new Font (14));
        online.setLayoutX (300);
        online.setLayoutY (20);
        online.setPrefSize (190, 300);
        ScrollPane scroll = new ScrollPane ();
        scroll.setContent (recieve_message);
        new WriteThread (client_utility);
        new ReadThread (client_utility, recieve_message);
        Button send = new Button ("Send");
        //Button sendfile=new Button("Send File");
        
        Button refresh = new Button ("Refresh");
        send.setLayoutX (300);
        send.setLayoutY (460);
        refresh.setLayoutX (350);
        refresh.setLayoutY (325);
        //sendfile.setLayoutX(350);
        //sendfile.setLayoutY(370);
        Text text = new Text ("Type your message:");
        text.setX (10);
        text.setY (440);
        text.setFont (new Font (16));
        Text text2 = new Text ("Recieved Messages:");
        text2.setX (50);
        text2.setY (15);
        text2.setFont (new Font (16));
        Text text3 = new Text ("Available Online:");
        text3.setX (325);
        text3.setY (15);
        text3.setFont (new Font (16));
        Text text4=new Text("Chat With");
        text4.setX(10);
        text4.setY(350);
        text4.setFont (new Font (16));
        TextField person= new TextField ();
        person.setFont (new Font (14));
        person.setLayoutX (10);
        person.setLayoutY (360);
        person.setPrefSize (250, 50);
        
        Group root = new Group (message_field, recieve_message, send, online, refresh, text, text2, text3,text4,person);
        Scene scene = new Scene (root, 550, 550);
        scene.setFill (Color.CADETBLUE);
        stage.setTitle (name);
        send.setOnAction (e ->
        {
            String msg = message_field.getText ();
           /* String[] arr = new String[2];
            int idx=0;
            StringTokenizer st = new StringTokenizer (msg, ":");
            while (st.hasMoreTokens ())
            {
                arr[idx++] = st.nextToken ();
            }
            String ontxt=arr[0]+"\n";
            
            online.setText(ontxt);*/
           online.setText(users);
            
            
            
            client_utility.write (msg);
        });

        refresh.setOnAction (e ->
        {
            table = Server.get_map ();
            table.keySet ().forEach ((key) ->
            {
                online.appendText(key+"\n");
            });
        });
        stage.setScene (scene);
        stage.show ();
    }

}
