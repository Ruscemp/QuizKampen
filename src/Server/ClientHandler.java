package Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
    private int numberOfRounds;
    private int numberOfQuestions;
    boolean gameOver;
    boolean roundOver;
    boolean exit;
    BufferedReader in;
    ObjectOutputStream out;
    String category;
    String inputLine;

    ClientHandler(Socket player, int counterChooseCategory) throws IOException {
        GameOptions gameOptions = new GameOptions();
        numberOfRounds = gameOptions.rounds;
        numberOfQuestions = gameOptions.xQuestions;
        this.player = player;
        this.counterChooseCategory = counterChooseCategory;
        in = new BufferedReader(new InputStreamReader(player.getInputStream()));
        out = new ObjectOutputStream(player.getOutputStream());
        score = 0;
        exit = false;
    }

    @Override
    public void run() {
        try {
            while (!exit) {
                while (yourTurn) {
                    if (chooseCategory) {
                        category = sendCategories();
                        getQuestionList(category);
                    }
                    opponent.setQuestionList(questionList);
                    startQuestionRound();
                    if (numberOfRounds == 0) {
                        this.gameOver = true;
                    }
                    if (counterChooseCategory == 2 && !gameOver) {
                        chooseCategory = true;
                        counterChooseCategory = 0;
                        continue;
                    }
                    opponentsTurn();
                    if (!gameOver){
                        out.writeObject("END");
                    }
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


    public List<Question> getQuestions(Categories category) {
        QuestionsCards qc = new QuestionsCards();
        return qc.getQuestionCardsByCategory(category);

    }

    public String sendCategories() throws IOException {
        StringBuilder Category = new StringBuilder();
        for (Categories c : Categories.values()) {
            Category.append(c).append(System.lineSeparator());
        }
        out.writeObject(Category.toString());
        System.out.println("Sedning: "+Category.toString());
        return in.readLine();
    }

    public List<Question> getQuestionList(String category) {

        try {
            Categories CategoryFromEnum = Categories.valueOf(category.toUpperCase());
            questionList = getQuestions(CategoryFromEnum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public void sendMessage(String s) {
        try {
            out.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startQuestionRound() throws IOException {
        chooseCategory = false;

        for (int i = 0; i < numberOfQuestions; i++) {
            System.out.println("Sending questionList");
            System.out.println(questionList.get(i));
            out.writeObject(questionList.get(i));
            System.out.println("Getting input");
            String s;
            inputLine = in.readLine();
            System.out.println(inputLine);
            if (inputLine.trim().equalsIgnoreCase(questionList.get(i).correctAnswer.trim())) {
                score++;
                System.out.println("Score +\nScore: "+score);
            }
        }
        roundOver = true;
        numberOfRounds--;
        counterChooseCategory++;
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

    public int sendScorePoints() {
        return score;
    }
}

