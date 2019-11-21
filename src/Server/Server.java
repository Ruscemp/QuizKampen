package Server;

import Client.Categories;
import Client.Question;

import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    ClientHandler player1;
    ClientHandler player2;
    private boolean player1Turn;
    private boolean player2Turn;
    private boolean gameOver;

    Server(ClientHandler player1, ClientHandler player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setPlayerTurnTrue();
        player2.setPlayerTurnFalse();

        //Varför finns dessa get questions? alltså numer Categories.Food
//        while (!gameOver) {
//            if (player1.yourTurn) {
//                player1.receiveQuestionFromServer(getQuestion(Categories.FOOD));
//            }
//            if (player2.yourTurn) {
//                player2.receiveQuestionFromServer(getQuestion(Categories.FOOD));
//            }
//        }
    }


    public ArrayList<Question> getQuestion(Categories category) {
        //HÄr skall en sout göras för att visa fel.
        QuestionsCards qc = new QuestionsCards();
        return (ArrayList<Question>) qc.getQuestionCardsByCategory(category);

    }
}

