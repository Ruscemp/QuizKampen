package Server;

import Client.Categories;
import Client.Question;

import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    ClientHandler player1;
    ClientHandler player2;
    int player1Score;
    int player2Score;

    Server(ClientHandler player1, ClientHandler player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setPlayerTurnTrue();
        player2.setPlayerTurnFalse();
    }

    @Override
    public void run() {
        while (true) {
            if (player1.gameOver && player2.gameOver) {
                System.out.println("SERVER: GAME OVER");
                player1.setPlayerTurnFalse();
                player2.setPlayerTurnFalse();
                player1Score = player1.sendScorePoints();
                player2Score = player2.sendScorePoints();
                if (player1Score > player2Score)
                    System.out.println("Player 1 wins \n");
                if (player1Score == player2Score)
                    System.out.println("It's a tie!");
                else
                    System.out.println("Player 2 wins \n");
                System.out.println("Player 1 Score: " + player1Score +
                        " \n Player 2 Score: " + player2Score);
                try {
                    Thread.sleep(999999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public ArrayList<Question> getQuestion(Categories category) {
        //HÄr skall en sout göras för att visa fel.
        QuestionsCards qc = new QuestionsCards();
        return (ArrayList<Question>) qc.getQuestionCardsByCategory(category);

    }
}

