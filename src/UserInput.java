public class UserInput {
    //Test main, ska flyttas till "allmänna main sen".
    public static void main(String[] args) {

        //För att få tillgång till alla kategorier.
        Categories[] EnumCategories = Categories.values();
        for (int i =0;i<EnumCategories.length;i++){
            System.out.println(EnumCategories[i].toString());
        }






        //För att få frågekort med användarens valda kategori.
       QuestionsCards QC=new QuestionsCards();
        System.out.println(QC.getQuestionCardsByCategory("geography").get(0).getQuestion());
    }
}
