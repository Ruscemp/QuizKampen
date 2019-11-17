package Client;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static QuestionsCards questionsCards = new QuestionsCards();
    private static ArrayList<Question> currentQuestionCard;
    private static int categoriesCleared = 0;
    private static Question currentQuestion;

    public static void newGame(String category) {
        currentQuestionCard = questionsCards.getQuestionCardsByCategory(category);
        currentQuestion = currentQuestionCard.get(new Random().nextInt(currentQuestionCard.size()));
    }

    public static String getCorrectAnswer() {
        return currentQuestion.correctAnswer;
    }
    public static Question getCurrentQuestion(){
        return currentQuestion;
    }
    public static int getCategoriesCleared() {
        return categoriesCleared;
    }
    public static void nextQuestion() {
        currentQuestionCard.remove(currentQuestion);
        currentQuestion = currentQuestionCard.get(new Random().nextInt(currentQuestionCard.size()));
    }
    public static boolean lastQuestion(){
        return currentQuestionCard.size() == 1;
    }
}
