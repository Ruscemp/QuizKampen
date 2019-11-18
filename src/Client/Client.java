package Client;

import GUI.MenuFiles.MenuController;
import GUI.MultiPlayerGame.MultiPlayerGameController;
import GUI.MenuFiles.MultiPlayerMenuController;
import GUI.SinglePlayerMenu.Single_GameMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Client extends Application {
    public static ArrayList<Thread> threads = new ArrayList<>(5);
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


        loadFXMLs();


        Scene mainMenu = new Scene(menuPane);
        Scene multiMenu = new Scene(multiMenuPane);
        Scene singleGame = new Scene(singlePane);
        Scene multiGame = new Scene(multiPane);


        setControllerVariables(mainMenu, multiMenu, singleGame, multiGame);

        setThreads();


        window.setScene(mainMenu);
        window.setTitle("Scene!");
        window.show();
    }

    private void loadFXMLs() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("../GUI/MenuFiles/Menu.fxml"));
        menuPane = loader.load();
        MainMenuController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(Client.class.getResource("../GUI/MenuFiles/MultiPlayerMenu.fxml"));
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
    }
    private void setControllerVariables(Scene mainMenu, Scene multiMenu, Scene singleGame, Scene multiGame){
        MainMenuController.setClient(this);
        MainMenuController.setMultiPlayerMenuScene(multiMenu);
        MainMenuController.setSinglePlayerScene(singleGame);

        MultiMenuController.setClient(this);
        MultiMenuController.setMainMenu(mainMenu);
        MultiMenuController.setMultiGame(multiGame);

        SingleGameController.setClient(this);
        SingleGameController.setMainMenu(mainMenu);

        MultiGameController.setClient(this);
        MultiGameController.setMenu(multiMenu);
        MultiGameController.setMainMenu(mainMenu);
    }
    private void setThreads(){
        threads.add(0, Thread.currentThread());
        SingleGameController.setSingleGameThreads();
        MultiGameController.setMultiGameThreads();
        threads.add(3, new Thread(this::exit));
    }


    public void setScene(Scene scene){
        window.setScene(scene);
    }
    private void exit() {
        System.exit(0);
    }
}

