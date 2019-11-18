package Server;

import java.net.Socket;

public class Server extends Thread {
    private Socket player1;
    private Socket player2;
    private boolean player1Turn;
    private boolean player2Turn;
    private boolean gameOver;
    Question question;

    Server(Socket player1, Socket player2) {
        this.player1 = player1;
        this.player2 = player2;

        ClientHandler player1handler = new ClientHandler(player1);
        ClientHandler player2handler = new ClientHandler(player2);
        player1handler.start();
        player2handler.start();
        System.out.println("All players connected");
        player1Turn = true;
        player2Turn = false;

        while (true) {
            while (player1Turn = true) {
               player1handler.receiveQuestionFromServer(getQuestion());
               player1handler.setTurnTrue();
            }
            while (player2Turn = true) {
                player2handler.receiveQuestionFromServer(getQuestion());
            }

        }
    }
//
//    public void run() {

////        try (
////                BufferedReader in = new BufferedReader(
////                        new InputStreamReader(player1.getInputStream()));
////                ObjectOutputStream out = new ObjectOutputStream(player1.getOutputStream());
//
//
//        ) {
//            String inputLine;

//                       Question question = new Question("Bilar", "Vart har Volvo sitt ursprung?", "Kina", "Africa", "Sverige", "Nord Korea");

//            ServerProtocol protocol = new ServerProtocol();
//            out.println(protocol.processInput(null));
//
//            try {
//                while (true) {
//
//                    Question questionToSend = getQuestion();
//                    out.writeObject(questionToSend);
//
//                    inputLine = in.readLine();
//                    System.out.println(inputLine);
//                    if (inputLine.equalsIgnoreCase(questionToSend.correctAnswer)) {
//                        System.out.println("Win");
//                    } else {
//                        System.out.println("EHHH wrong, now its player 2s turn");
//                    }
//                    // out2.println(questions.getQuestion());
//                    //out.println(protocol.processInput(inputLine));
//                }
//            } catch (SocketException e) {
//                e.printStackTrace();
//                System.out.println("Socket Exception: " + e.getMessage());
//
//            } catch (Exception e) {
//                System.out.println("Exception: " + e.getMessage());
//                e.printStackTrace();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("IOException: " + e.getMessage());
//        }
//    }

    public Question getQuestion() {
        Categories enumCategories = Categories.getRandom();
        String randomCategory = enumCategories.toString();
        Question question;
        QuestionsCards qc = new QuestionsCards();
        question = qc.getQuestionCardsByCategory(randomCategory).get(0);
        return question;
    }
}

