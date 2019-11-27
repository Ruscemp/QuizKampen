package Server;

public class Server extends Thread {
    ClientHandler player1;
    ClientHandler player2;
    int player1Score;
    int player2Score;
    String scoreBoard;
    String roundScore;
    String bla;

    Server(ClientHandler player1, ClientHandler player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setPlayerTurnTrue();
        player2.setPlayerTurnFalse();
    }

    @Override
    public void run() {
        while (true) {
            bla = "2";
            if (player1.roundOver && player2.roundOver)
                roundIsOver();
            if (player1.gameOver && player2.gameOver)
                gameIsOver();
        }
    }

    public void roundIsOver() {
        player1Score = player1.sendScorePoints();
        player2Score = player2.sendScorePoints();
        calculateRoundScore(player1Score, player2Score);
         player1.sendMessage(roundScore);
         player2.sendMessage(roundScore);
         player1.roundOver = false;
         player2.roundOver = false;
         roundScore = "";
    }

    public void gameIsOver() {
        System.out.println("SERVER: GAME OVER");
        player1.setPlayerTurnFalse();
        player2.setPlayerTurnFalse();
        player1Score = player1.sendScorePoints();
        player2Score = player2.sendScorePoints();
        calculateEndResults(player1Score, player2Score);
        player1.sendMessage(scoreBoard);
        player2.sendMessage(scoreBoard);
        try {
            Thread.sleep(999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void calculateRoundScore(int Player1Score, int Player2Score) {
        roundScore = "Player 1 current score: " + player1Score;
        roundScore += "\nPlayer 2 current score: " + player2Score;
    }

    public void calculateEndResults(int Player1Score, int Player2Score) {
        if (player1Score > player2Score) {
            scoreBoard = "Player 1 wins \n";
        }
        if (player1Score == player2Score) {
            scoreBoard = "It's a Tie!\n";
        } else if (player1Score < player2Score) {
            scoreBoard = "Player 2 wins \n";
        }
        scoreBoard += "Player 1 Score: " + player1Score +
                "\nPlayer 2 Score: " + player2Score;
    }
}

