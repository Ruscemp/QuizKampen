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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
---> Socket = väntar på godkänd anslutning till servern
---> Socket 2 = väntar också på godkännande till servern

---> Sout("Alla spelare är anslutnga");
---> Thread(() -> new Game(socket 1, socket2)).start();
---> Game.java implements Runnable
    public Game(Socket p1, Socket p2){
    Settings settings = new Settings();
    player1.setOpponent(p2);
    player2.setOpponent(p1);
    totalQuestionsForEachRound = settings.getProperty("questions");
    totalRoundsForGame = settings.getProperties("rounds");
    }
 */
