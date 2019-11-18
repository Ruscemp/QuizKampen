package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler extends Thread{
    public static String hostName, hostPort;
    private static String Exception = "";

    public static String getException() { return Exception; }

    public void run(){
        try (Socket addressSocket = new Socket(hostName, Integer.parseInt(hostPort));
             ObjectInputStream in = new ObjectInputStream(addressSocket.getInputStream());
             PrintWriter out = new PrintWriter(addressSocket.getOutputStream(), true)
        ) {
            while (true){

            }
        } catch (IOException e) {
            Exception = e.getMessage();
            e.printStackTrace();
        }
    }
}
