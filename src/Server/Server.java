package Server;

import java.util.List;

public class Server extends Thread {
    ClientHandler player1;
    ClientHandler player2;
    private boolean player1Turn;
    private boolean player2Turn;
    private boolean gameOver;
    Question question;

    Server(ClientHandler player1, ClientHandler player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setPlayerTurnTrue();
        player2.setPlayerTurnFalse();

        while (!gameOver) {
            if (player1.yourTurn) {
                player1.receiveQuestionFromServer(getQuestion("food"));
            }
           if (player2.yourTurn) {
               player2.receiveQuestionFromServer(getQuestion("food"));
           }
        }

    }

    public List<Question> getQuestion(String category) {
        QuestionsCards qc = new QuestionsCards();
        return qc.getQuestionCardsByCategory(category);

    }
}

