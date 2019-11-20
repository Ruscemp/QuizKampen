package Client;

import java.io.Serializable;

public class Question implements Serializable {
    //Instansvariabler
    protected String question;
    protected String answer1;
    protected String answer2;
    protected String answer3;
    protected String correctAnswer;
    protected Categories category;

    public Question(String question, String answer1, String answer2, String answer3, String correctAnswer, Categories category) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correctAnswer = correctAnswer;
        this.category = category;
    }
//Konstruktor


    //Getters & Setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return this.category + ": " +
                this.question + " " +
                this.answer1 + " " +
                this.answer2 + " " +
                this.answer3 + " " +
                this.correctAnswer;
    }
}
