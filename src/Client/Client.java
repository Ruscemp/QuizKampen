package Client;

import GUI.MainMenu.MenuController;
import GUI.MultiPlayerGame.MultiPlayerGameController;
import GUI.MultiPlayerGame.MultiPlayerMenuController;
import GUI.SinglePlayerMenu.Single_GameMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Client extends Application {
    public static ArrayList<Thread> threads = new ArrayList<>(4);
    Stage window;
    private Pane menuPane, multiMenuPane, singlePane, multiPane;
    public MenuController MainMenuController;
    public MultiPlayerMenuController MultiMenuController;
    public Single_GameMenuController SingleGameController;
    public MultiPlayerGameController MultiGameController;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("../GUI/MainMenu/Menu.fxml"));
        menuPane = loader.load();
        MainMenuController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("../GUI/MultiPlayerGame/MultiPlayerMenu.fxml"));
        multiMenuPane = loader.load();
        MultiMenuController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("../GUI/SinglePlayerMenu/Single_GameMenu.fxml"));
        singlePane = loader.load();
        SingleGameController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("../GUI/MultiPlayerGame/MultiPlayerGame.fxml"));
        multiPane = loader.load();
        MultiGameController = loader.getController();

        Scene mainMenu = new Scene(menuPane);
        Scene multiMenu = new Scene(multiMenuPane);
        Scene singleGame = new Scene(singlePane);
        Scene multiGame = new Scene(multiPane);



        MainMenuController.setMainMenuScene(this);
        MainMenuController.setMultiPlayerMenuScene(multiMenu);
        MainMenuController.setSinglePlayerScene(singleGame);
        MultiMenuController.setClient(this);
        MultiMenuController.setMainMenu(mainMenu);
        MultiMenuController.setMultiGame(multiGame);
        SingleGameController.setClient(this);
        SingleGameController.setMainMenu(mainMenu);
        MultiGameController.setClient(this);
        MultiGameController.setMenu(multiMenu);

        threads.add(0, Thread.currentThread());
        threads.add(3, new Thread(this::exit));
        SingleGameController.setSingleGameMenuThreads();
        MultiGameController.setMultiGameMenuThreads();

        window.setScene(mainMenu);
        window.setTitle("Scene!");
        window.show();
    }

    public void setScene(Scene scene){
        window.setScene(scene);
    }

    private void exit() {
        System.exit(0);
    }
}

