package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


        //Lägger till alla Questions i en Arraylist.
        allQuestions.add(new Question("Vad heter Englands huvudstad?", "Stockholm", "Birmingham", "Seoul", "London", Categories.GEOGRAPHY));
        allQuestions.add(new Question("Vilken är den populäraste Sushi biten?", "Räka", "Avokado", "Smörfisk", "Lax", Categories.FOOD));
        allQuestions.add(new Question("Vad står DIF för?", "Djungelns Idrotts Förening", "Dildo I Fickan", "Dinosaurier Inomhus Forever", "Djurgårdens Idrotts Förening", Categories.SPORT));
        allQuestions.add(new Question("Har Trump en peruk?", "Nej", "Nej", "Nej", "JA!", Categories.POLITICS));
        allQuestions.add(new Question("Vad äter Myrslokar?", "Människor", "Fransmän", "Vego", "Myror", Categories.NATURE));
        allQuestions.add(new Question("Vilken är den populäraste Sushi biten?", "Räka", "Avokado", "Smörfisk", "Lax", Categories.FOOD));
        allQuestions.add(new Question("Vilken är den populäraste Sushi biten?", "Räka", "Avokado", "Smörfisk", "Lax", Categories.FOOD));
        allQuestions.add(new Question("Vilken är den populäraste Sushi biten?", "Räka", "Avokado", "Smörfisk", "Lax", Categories.FOOD));

    }


    //Metod
    protected List<Question> getQuestionCardsByCategory(String categoryFromUser) {
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
