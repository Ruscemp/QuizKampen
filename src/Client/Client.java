package Client;

import GUI.MainMenu.MenuController;
import GUI.SinglePlayerMenu.Single_GameMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Client extends Application {
    public static ArrayList<Thread> threads = new ArrayList<>(3);
    Stage window;
    Scene menu, single;
    private Pane menuPane, singlePane;
    public Single_GameMenuController controller2;
    public MenuController controller1;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("../GUI/MainMenu/Menu.fxml"));
        menuPane = loader.load();
        controller1 = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("../GUI/SinglePlayerMenu/Single_GameMenu.fxml"));
        singlePane = loader.load();
        controller2 = loader.getController();

        Scene menu = new Scene(menuPane);
        Scene single = new Scene(singlePane);


        controller1.setScene2(single);
        controller1.setMain(this);
        controller2.setScene1(menu);
        controller2.setMain(this);

        threads.add(0, Thread.currentThread());
        controller2.setSingleGameMenuThreads();

        window.setScene(menu);
        window.setTitle("Scene!");
        window.show();
    }

    public void setScene(Scene scene){
        window.setScene(scene);
    }
}

