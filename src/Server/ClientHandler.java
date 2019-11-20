package Server;

import Client.Categories;
import Client.Question;
import jdk.jfr.Category;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket player;
    private Server server;
    ArrayList<Client.Question> questionListFromServer = new ArrayList<Question>();
    boolean yourTurn;
    private ClientHandler opponent;
    boolean chooseCategory = true;
    private int score;
    Question question;


    ClientHandler(Socket player) {
        this.player = player;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(player.getInputStream()));
                ObjectOutputStream out = new ObjectOutputStream(player.getOutputStream());
        ) {
            String inputLine;
            try {
                while (true) {
                    while (yourTurn) {
                        if (chooseCategory) {

                            //Fixa uppläsning från Enum (Categories) Loopa genom enum-listan och printa alla alternativ.
                            //Vi vill inte ha det hårdkodat som nu.
                            out.writeObject("Choose Category" + '\n' +
                                    "Politics" + '\n' +
                                    "Food" + '\n' +
                                    "Nature" + '\n' +
                                    "Geography" + '\n' +
                                    "Sport");
                            inputLine = in.readLine();

                            //Gör jämförelsen mot ENUM istället för mot if satsen som gick igenom hårdkodade kategorier.
                            try {
                                Client.Categories CategoryFromEnum = Categories.valueOf(inputLine.toUpperCase());
                                question = getQuestion(CategoryFromEnum);
                            }catch (Exception e) {
                                System.out.println("Incorrect");
                            }
                        }
                        for (int i = 0; i < 2; i++) {
                            System.out.println("New turn");
//                        Question question = new Question("vem är du?", "han", "hon",
//                                "den", "jag", Categories.FOOD);
//                           Question questionToSend = questionListFromServer.get(i);
                            out.writeObject(question);
                            inputLine = in.readLine();
                            System.out.println(inputLine);
                            if (inputLine.equalsIgnoreCase(question.correctAnswer)) {
                                System.out.println("Correct");
                                score++;
                            } else {
                                System.out.println("Wrong");
                            }
                            chooseCategory = false;
                            yourTurn = false;
                        }
                            break;
                    }
//                        opponent.chooseCategory = false;
                        opponent.yourTurn = true;
                        Thread.sleep(1000);
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

    public void receiveQuestionFromServer(ArrayList<Client.Question> questionList) {
        questionListFromServer = questionList;
    }

    public void setPlayerTurnTrue() {
        this.yourTurn = true;
    }

    public void setPlayerTurnFalse() {
        this.yourTurn = false;
    }

    public void setOpponent(ClientHandler opponent) {
        this.opponent = opponent;
    }

    public void setServer(Server server){
        this.server = server;
    }

    public Client.Question getQuestion(Categories category) {
        QuestionsCards qc = new QuestionsCards();
        return qc.getQuestionCardsByCategory(category).get(0);

    }
}

