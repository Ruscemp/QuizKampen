package Server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Server extends Thread{

    private Socket clientSocket;
    private Socket clientSocket2;
    private int clientsConnected;
    Question questions;

    Server(Socket clientSocket, Socket clientSocket2) {
        this.clientSocket = clientSocket;
        this.clientSocket = clientSocket2;
    }

    public void run() {

        try (
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));



        ) {
            String inputLine;
            QuestionsCards QC = new QuestionsCards();

//                       Question question = new Question("Bilar", "Vart har Volvo sitt ursprung?", "Kina", "Africa", "Sverige", "Nord Korea");

//            ServerProtocol protocol = new ServerProtocol();
//            out.println(protocol.processInput(null));

            try {
                while (true) {

                        out.writeObject(QC.allQuestions.get(1));

                    while ((inputLine = in.readLine()) != null) {

                        if (inputLine.equals(questions.correctAnswer)){
                            out.writeObject(questions.getCorrectAnswer());
                        } else
                        {
                            out.writeObject("EHHH wrong, now its player 2s turn");
                        }
                       // out2.println(questions.getQuestion());
                        //out.println(protocol.processInput(inputLine));
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
                System.out.println("Socket Exception: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public int getClientsConnected() {
        return clientsConnected;
    }
}
