package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Thread {
    private Socket player;
    Question questionFromServer;
    boolean yourTurn = false;


    ClientHandler(Socket player) {
        this.player = player;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(player.getInputStream()));
                ObjectOutputStream out = new ObjectOutputStream(player.getOutputStream());
        ) {
            String inputLine = "Placeholder";
            Question question = new Question("Vad heter Englands huvudstad?", "Stockholm", "Birmingham", "Seoul", "London", Categories.GEOGRAPHY);

            try {
                while (true) {
                    while (yourTurn) {
                        Question questionToSend = questionFromServer;
                        out.writeObject(question);

                        inputLine = in.readLine();
                        System.out.println(inputLine);
                        if (inputLine.equalsIgnoreCase(question.correctAnswer)) {
                            System.out.println("Win");
                        } else {
                            System.out.println("EHHH wrong, now its player 2s turn");
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

    public void receiveQuestionFromServer(Question question) {
        questionFromServer = question;
    }

    public void sendMessageToClient(String message){

    }

    public void setTurnTrue(){
        yourTurn = true;
    }

    public void setTurnFalse(){
        yourTurn = false;
    }
}

