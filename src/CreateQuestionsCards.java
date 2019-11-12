public class CreateQuestionsCards {
    public static void main(String[] args) {

        Question Q1 = new Question("Vad heter Sveriges huvudstad", new String[]{"Budapest", "Stockholm", "Belgrad", "Kattby"},"Stockholm",Categories.GEOGRAPHY );
        Question Q2=new Question("Vilka katter är sötast?", new String[]{"Budapest", "", "Brittisk korthår", "Kattby"},"Brittisk korthår",Categories.NATURE);
        Question Q3=new Question("Vad heter Hollands huvudstad", new String[]{"Budapest", "Stockholm", "Amsterdam", "Kattby"},"Amsterdam",Categories.GEOGRAPHY );
        Question Q4=new Question("Vad heter de äckliga orangea pommesen?", new String[]{"Budapest", "Sötpommes", "Belgrad", "Kattby"},"Sötpommes", Categories.FOOD );


        System.out.println(Q2.getCategory());
    }
}
