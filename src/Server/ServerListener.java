package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(33332)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Socket clientSocket2 = serverSocket.accept();
                ClientHandler player1handler = new ClientHandler(clientSocket);
                ClientHandler player2handler = new ClientHandler(clientSocket2);
                player1handler.setOpponent(player2handler);
                player2handler.setOpponent(player1handler);
                System.out.println("All players connected");
                player1handler.start();
                player2handler.start();
                Server server = new Server(player1handler, player2handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

