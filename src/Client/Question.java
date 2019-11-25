package Client;


import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    //Instansvariabler
    public String question;
    public String[] alternativeAnswers;
    public String correctAnswer;
    public Categories category;


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
        return this.correctAnswer;
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
                this.alternativeAnswers[0] + " " +
                this.alternativeAnswers[1] + " " +
                this.alternativeAnswers[2] + " " +
                this.alternativeAnswers[3] + " " +
                this.correctAnswer + " " +
                this.correctAnswer;
    }
}

