package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    public static void main(String[] args) {

        Categories[] EnumCategories = Categories.values();
        QuestionsCards QC=new QuestionsCards();

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Socket clientSocket2 = serverSocket.accept();

                if (clientSocket.isConnected() == true && clientSocket2.isConnected() == true) {
                    Server clientHandler = new Server(clientSocket, clientSocket2);
                    clientHandler.start();
                   // System.out.println("Two clients connected to server!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean verifyConnectedUsers(Socket player1, Socket player2){
        Boolean isTwoPlayersConnected = false;

        if (player1.isConnected() == true && player2.isConnected() == true){
            isTwoPlayersConnected = true;
            System.out.println("Two clients has connected to event");
        }
        return isTwoPlayersConnected;
    }

}

