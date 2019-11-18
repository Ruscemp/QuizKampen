package GUI.MultiPlayerGame;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.scene.Scene;

public class MultiPlayerMenuController {

    private Scene mainMenu;
    private Scene multiGame;
    private Client client;

    public void setClient(Client client){
        this.client = client;
    }
    public void setMainMenu(Scene mainMenu){
        this.mainMenu = mainMenu;
    }
    public void setMultiGame(Scene multiGame){
        this.multiGame = multiGame;
    }

    public void goBack(ActionEvent event) {
        client.setScene(mainMenu);
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }

    public void ConnectToServer(ActionEvent event) {
        client.setScene(multiGame);
    }
}
