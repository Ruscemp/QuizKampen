package Client;

import Server.Categories;

import java.io.Serializable;

public class Question implements Serializable {
    //Instansvariabler
    protected String question;
    protected String[] alternativeAnswers;
    protected String correctAnswer;
    protected Categories category;


    //Konstruktor
    public Question(String question,String[]alternativeAnswers,String correctAnswer,Categories category){
        setQuestion(question);
        setAlternativeAnswers(alternativeAnswers);
        setCorrectAnswer(correctAnswer);
        setCategory(category);
    }

    //Getters & Setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAlternativeAnswers() {
        return alternativeAnswers;
    }

    public void setAlternativeAnswers(String[] alternativeAnswers) {
        this.alternativeAnswers = alternativeAnswers;
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
                this.alternativeAnswers + " " +
                this.correctAnswer + " " +
                this.correctAnswer;
    }
}
