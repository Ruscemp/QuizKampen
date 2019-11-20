package Server;

import Client.Categories;
import Client.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class QuestionsCards {


    ArrayList<Client.Question> allQuestions = new ArrayList<Client.Question>();

    public QuestionsCards() {

        Properties p = new Properties();

        try {
            p.load(new FileInputStream("src/Server/Question.properties"));
            Enumeration keys = p.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                String value = p.getProperty(key);
                String[] questionsFromProp = value.split(";");

                String pCategory = questionsFromProp[6];

                try {
                    Client.Categories pCategoryFromEnum = Categories.valueOf(pCategory);
                    String pQuestion = questionsFromProp[0];
                    String pAlternative1 = questionsFromProp[1];
                    String pAlternative2 = questionsFromProp[2];
                    String pAlternative3 = questionsFromProp[3];
                    String pAlternative4 = questionsFromProp[4];
                    String pCorrectAnswer = questionsFromProp[5];

                    allQuestions.add(new Client.Question(pQuestion,new String[]{pAlternative1,pAlternative2,pAlternative3,pAlternative4},pCorrectAnswer, pCategoryFromEnum));
                }
                catch (Exception e){
                    //System.out.println("Category " + pCategory + " is not valid");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Metod
    protected List<Client.Question> getQuestionCardsByCategory(Categories categoryFromUser) {
        ArrayList<Client.Question> questionsByCategory = new ArrayList<>();

        // Loopar igenom alla frågor och jämför sedan om inputen för kategorin=kategorin för frågorna. Om sant så lägger de till frågorna från den kategorin i questionsByCategory.
        for (int i = 0; i < allQuestions.size(); i++) {
            if (categoryFromUser.toString().equals(allQuestions.get(i).getCategory().toString())) {
                questionsByCategory.add(allQuestions.get(i));
            }
        }
        Collections.shuffle(questionsByCategory);
        return questionsByCategory;
    }
}
