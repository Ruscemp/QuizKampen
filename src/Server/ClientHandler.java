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
    BufferedReader in;
    ObjectOutputStream out;


    ClientHandler(Socket player) throws IOException {
        this.player = player;
        in = new BufferedReader(
                new InputStreamReader(player.getInputStream()));
        out = new ObjectOutputStream(player.getOutputStream());
    }

    @Override
    public void run() {

        try {
            while (true) {
                while (yourTurn) {
                    if (chooseCategory) {
                        String category = sendCategories();
                        sendQuestion(category);
                    }
                    startQuiz();
                    chooseCategory = false;
                    opponent.chooseCategory = false;
                yourTurn = false;
                opponent.yourTurn = true;
                break;
                }
            Thread.sleep(1000);
            }
//                        opponent.chooseCategory = false;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

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


    public Client.Question getQuestion(Categories category) {
        QuestionsCards qc = new QuestionsCards();
        return qc.getQuestionCardsByCategory(category).get(0);

    }

    public String sendCategories() throws IOException {
        //Fixa uppläsning från Enum (Categories) Loopa genom enum-listan och printa alla alternativ.
        //Vi vill inte ha det hårdkodat som nu.
//        opponent.sendMessage("Waiting for player to choose category");

        out.writeObject("Choose Category" + '\n' +
                "Politics" + '\n' +
                "Food" + '\n' +
                "Nature" + '\n' +
                "Geography" + '\n' +
                "Sport");
        return in.readLine();


        //Gör jämförelsen mot ENUM istället för mot if satsen som gick igenom hårdkodade kategorier.
    }

    public void sendQuestion(String category) {

        try {
            Client.Categories CategoryFromEnum = Categories.valueOf(category.toUpperCase());
            question = getQuestion(CategoryFromEnum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String s) {
        try {
            out.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startQuiz() throws IOException {
        String inputLine;
        for (int i = 0; i < 2; i++) {
            System.out.println("New turn");
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
        }
    }

    public void setChooseCategoryTrue(){
        chooseCategory = true;
    }

    public void setChooseCategoryFalse(){
        chooseCategory = false;
    }
}

