package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Socket clientSocket2 = serverSocket.accept();

                if (clientSocket.isConnected() && clientSocket2.isConnected()) {
                    Server server = new Server(clientSocket, clientSocket2);
                   // System.out.println("Two clients connected to server!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

