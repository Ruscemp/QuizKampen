package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server extends Thread{

    private Socket clientSocket;

    public Server(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
    }

    public void run(){

        try (
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine, outputLine;
            out.println("CONNECTED");


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
