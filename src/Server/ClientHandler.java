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
    List<Question> questionList = new ArrayList<Question>();
    boolean yourTurn;
    private ClientHandler opponent;
    private int counterChooseCategory;
    boolean chooseCategory = true;
    private int score;
    private int numberOfRounds = 1;
    private int numberOfQuestions = 3;
    boolean gameOver;
    BufferedReader in;
    ObjectOutputStream out;
    String category;


    ClientHandler(Socket player, int counterChooseCategory) throws IOException {
        this.player = player;
        this.counterChooseCategory = counterChooseCategory;
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
                        category = sendCategories();
                        getQuestionList(category);
                    }
                    opponent.setQuestionList(questionList);
                    startQuiz();
                    counterChooseCategory++;
                    numberOfRounds--;
                    if (numberOfRounds == 0) {
                        this.gameOver = true;
                        System.out.println("GameOver");
                    }
                    if (counterChooseCategory == 2 && !gameOver) {
                        chooseCategory = true;
                        counterChooseCategory = 0;
                        continue;
                    }
                    opponentsTurn();
                    break;
                }
            Thread.sleep(1000);
            }

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


    public List<Question> getQuestion(Categories category) {
        QuestionsCards qc = new QuestionsCards();
        return qc.getQuestionCardsByCategory(category);

    }

    public String sendCategories() throws IOException {

        String Category="";
        for  (Categories c: Categories.values()) {
            Category=Category+c+System.lineSeparator();
        }
        out.writeObject(Category);
        return in.readLine();
      /*  out.writeObject("Choose Category" + '\n' +
                "Politics" + '\n' +
                "Food" + '\n' +
                "Nature" + '\n' +
                "Geography" + '\n' +
                "Sport");*/
        //return in.readLine();

    }

    public List<Question> getQuestionList(String category) {

        try {
            Categories CategoryFromEnum = Categories.valueOf(category.toUpperCase());
            questionList = getQuestion(CategoryFromEnum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionList;
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
        chooseCategory = false;
        for (int i = 0; i < numberOfQuestions; i++) {
            System.out.println("New turn");
            out.writeObject(questionList.get(i));
            inputLine = in.readLine();
            System.out.println(inputLine);
            if (inputLine.equalsIgnoreCase(questionList.get(i).correctAnswer)) {
                System.out.println("Correct");
                score++;
            } else {
                System.out.println("Wrong");
            }
        }
    }

    public void setChooseCategoryTrue() {
        chooseCategory = true;
    }

    public void setChooseCategoryFalse() {
        chooseCategory = false;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void opponentsTurn() {
        chooseCategory = false;
        opponent.chooseCategory = false;
        yourTurn = false;
        opponent.yourTurn = true;
    }

    public int sendScorePoints(){
        return score;
    }
}

