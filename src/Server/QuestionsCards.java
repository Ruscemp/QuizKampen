package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuestionsCards {

    ArrayList<Question> allQuestions = new ArrayList<Question>();
       /* Det ska vara minst 4 frågor per spel.(OBL) CHECK.
                Frågorna ska vara indelade i olika ämneskategorier. (OBL) CHeck.
   De frågor som ingår i samma rond ska tillhöra samma kategori. (OBL) CHECK*/

           /*     Frågorna i spelet ska vara uppdelade i ronder med initialt 2 frågor/rond så att vi sammanlagt
        har 2 ronder/spel och sammanlagt 4 frågor/spel. (OBL)

                Det ska gå att ställa in hur många frågor som ska finnas i en rond och hur många ronder som
        ska ingå i ett spel, via en Properties-fil. (OBL)

        */

    public QuestionsCards() {


        //Skapar Question objekt och sätter egenskaper via konstruktorn.
        Question Q1 = new Question("Vad heter Sveriges huvudstad", new String[]{"Budapest", "Stockholm", "Belgrad", "Kattby"}, "Stockholm", Categories.GEOGRAPHY);
        Question Q2 = new Question("Vilka katter är sötast?", new String[]{"Budapest", "", "Brittisk korthår", "Kattby"}, "Brittisk korthår", Categories.NATURE);
        Question Q3 = new Question("Vad heter Hollands huvudstad", new String[]{"Budapest", "Stockholm", "Amsterdam", "Kattby"}, "Amsterdam", Categories.GEOGRAPHY);
        Question Q4 = new Question("Vad heter de äckliga orangea pommesen?", new String[]{"Budapest", "Sötpommes", "Belgrad", "Kattby"}, "Sötpommes", Categories.NATURE);

        //Lägger till alla Questions i en Arraylist.
        allQuestions.add(Q1);
        allQuestions.add(Q2);
        allQuestions.add(Q3);
        allQuestions.add(Q4);
    }


    //Metod
    protected ArrayList<Question> getQuestionCardsByCategory(String categoryFromUser) {
        ArrayList<Question> questionsByCategory = new ArrayList<Question>();

        // Loopar igenom alla frågor och jämför sedan om inputen för kategorin=kategorin för frågorna. Om sant så lägger de till frågorna från den kategorin i questionsByCategory.
        for (int i = 0; i < allQuestions.size(); i++) {
            if (categoryFromUser.equals(allQuestions.get(i).getCategory().toString())) {
                questionsByCategory.add(allQuestions.get(i));

            }
        }
        Collections.shuffle(questionsByCategory);
        return questionsByCategory;
    }


}
