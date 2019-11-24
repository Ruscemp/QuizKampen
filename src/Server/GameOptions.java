package Server;

import java.io.FileInputStream;
import java.util.Properties;

public class GameOptions {
    int rounds;
    int xQuestions;

    public GameOptions(){
        readGameOptionsFromFile();


    }
    public void readGameOptionsFromFile(){
        Properties p=new Properties();
        try{p.load(new FileInputStream("src/Server/GameOptions.properties"));
        }catch (Exception e){
        System.out.println("File could not be read");
        }

        rounds = Integer.parseInt(p.getProperty("Rounds","2"));
        xQuestions=Integer.parseInt(p.getProperty("xQuestions","4"));

    }

}
