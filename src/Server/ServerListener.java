package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerListener {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(11111)) {
            while (true) {
                ClientHandler player1handler = new ClientHandler(serverSocket.accept(),0);
                ClientHandler player2handler = new ClientHandler(serverSocket.accept(), 1);
                player1handler.setOpponent(player2handler);
                player2handler.setOpponent(player1handler);
                System.out.println("All players connected");
                player1handler.start();
                player2handler.start();
                Server server = new Server(player1handler, player2handler);
                server.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
