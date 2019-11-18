package GUI.MultiPlayerGame;

import Client.Client;
import Client.Game;
import Client.Question;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class MultiPlayerGameController {

    public Label display;
    public Label total;
    public Label right;
    public Label wrong;
    public Button ExitButton;
    public Button MainMenuButton;
    public Button NewGameButton;
    public Button Answer1Button;
    public Button Answer2Button;
    public Button Answer3Button;
    public Button Answer4Button;
    public Button Category1Button;
    public Button Category2Button;
    public Button Category3Button;
    public Button Category4Button;
    public ListView PlayersList;
    public ListView PlayerPointsList;
    public Label Cur_Cat;
    public Label Cur_CatNumbers;

    private Scene menu;
    private Client client;
    private ArrayList<Button> disabledCategories = new ArrayList<Button>();


    public void setClient(Client client){
        this.client = client;
    }
    public void setMenu(Scene menu){
        this.menu = menu;
    }

    @FXML
    public void goBack(){
        if (Client.threads.get(1).isAlive()){
            Client.threads.get(1).interrupt();
        }
        client.setScene(menu);
    }

    public void answer(ActionEvent event) {
        if(((Button)event.getSource()).getText().equalsIgnoreCase(Game.getCorrectAnswer())){
            correct();
        } else {
            wrong();
        }
        total.setText(String.valueOf(Integer.parseInt(total.getText())+1));
        display.setText(display.getText()+"\n\n\nCorrect Answer was "+Game.getCorrectAnswer());
        Client.threads.get(1).start();
    }

    public void category(ActionEvent event) {
        Game.newGame(((Button)event.getSource()).getText());
        disabledCategories.add((Button)event.getSource());
        disableCategories();
        setQuestionOnDiplay(Game.getCurrentQuestion());
    }

    public void newGame(ActionEvent event) {
        if (Client.threads.get(1).isAlive()){
            Client.threads.get(1).interrupt();
        }
        NewGameButton.setText("New Game");
        total.setText("0");
        right.setText("0");
        wrong.setText("0");
        display.setText("Select question category from list to the right");
        disabledCategories.removeAll(disabledCategories);
        allowCategories();
    }

    public void exit(ActionEvent event) {
        Client.threads.get(2).start();
    }

    private void correct(){
        disableAnswers();
        display.setText("You are correct!");
        right.setText(String.valueOf(Integer.parseInt(right.getText())+1));
    }
    private void wrong(){
        disableAnswers();
        display.setText("You are wrong!");
        wrong.setText(String.valueOf(Integer.parseInt(wrong.getText())+1));
    }

    private void disableAnswers(){
        Answer1Button.setDisable(true);
        Answer2Button.setDisable(true);
        Answer3Button.setDisable(true);
        Answer4Button.setDisable(true);
    }
    private void disableCategories(){
        Category1Button.setDisable(true);
        Category2Button.setDisable(true);
        Category3Button.setDisable(true);
        Category4Button.setDisable(true);
    }

    private void allowAnswers(){
        Answer1Button.setDisable(false);
        Answer2Button.setDisable(false);
        Answer3Button.setDisable(false);
        Answer4Button.setDisable(false);
    }
    private void allowCategories(){
        Category1Button.setDisable(false);
        Category2Button.setDisable(false);
        Category3Button.setDisable(false);
        Category4Button.setDisable(false);
        for (Button button : disabledCategories){
            button.setDisable(true);
        }
    }

    private void setQuestionOnDiplay(Question q){
        display.setText(q.getQuestion());
        Answer1Button.setText(q.getAlternativeAnswers()[0]);
        Answer2Button.setText(q.getAlternativeAnswers()[1]);
        Answer3Button.setText(q.getAlternativeAnswers()[2]);
        Answer4Button.setText(q.getAlternativeAnswers()[3]);
        allowAnswers();
    }

    private void exit() {
        System.exit(0);
    }

    private void nextQuestion() {
        try {
            String text = display.getText();
            for (int i = 5; i>0; i--){

                int finalI = i;
                Platform.runLater(() -> display.setText(String.format("%s%s%d%s", text, "\n\nNext question in ", finalI, " seconds!")));
                if(Thread.interrupted()) {
                    break;
                }
                sleep(750);
            }
            if (Game.lastQuestion() && !Thread.interrupted()){
                Platform.runLater(() -> display.setText("Select next question category from list to the right"));
                Platform.runLater(this::allowCategories);
            } else if (!Thread.interrupted()){
                Game.nextQuestion();
                Platform.runLater(() -> setQuestionOnDiplay(Game.getCurrentQuestion()));
            }
        } catch (InterruptedException e){
            System.out.println("Next Question Thread is interrupted!");
        }
        Client.threads.set(1, new Thread(this::nextQuestion));
    }

    private void sleep(int i) throws InterruptedException {
        Thread.sleep(i);
    }

    public void initializeGameScene(){
        disableAnswers();
        disableCategories();
        total.setText("0");
        right.setText("0");
        wrong.setText("0");
        String startText = "In the top left corner you see your status\n" +
                "\tTotal answered questions, number of right and wrong answers\n" +
                "Under status you have 3 buttons\n" +
                "\t• Start Game starts the QuizKamp game\n" +
                "\t• Main Menu brings you back to main menu\n" +
                "\t• Exit closes the game\n" +
                "Down in the middle you have 4 buttons with which to answer questions\n" +
                "Buttons to the right select Category of the next question. ";
        display.setText(startText);
    }

    public void setMultiGameMenuThreads(){
        Client.threads.add(2, new Thread(this::nextQuestion));
    }
}
