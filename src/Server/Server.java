package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server extends Thread{

    private Socket clientSocket;

    Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run(){

        try (
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String inputLine, outputLine;

            ServerProtocol protocol = new ServerProtocol();
            out.println(protocol.processInput(null));

            while ((inputLine = in.readLine()) != null){
                out.println(protocol.processInput(inputLine));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
