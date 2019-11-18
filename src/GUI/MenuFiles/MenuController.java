package GUI.MenuFiles;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MenuController {
    private Scene SinglePlayerScene;
    private Scene MultiPlayerMenuScene;
    private Client client;

    public void setClient(Client client){
        this.client = client;
    }
    public void setSinglePlayerScene(Scene SinglePlayerScene){
        this.SinglePlayerScene = SinglePlayerScene;
    }
    public void setMultiPlayerMenuScene(Scene MultiPlayerMenuScene){ this.MultiPlayerMenuScene = MultiPlayerMenuScene; }

    @FXML
    private Button StartSingle;

    @FXML
    private Button StartMulti;

    @FXML
    private Button Exit;

    @FXML
    protected void handleSingleplayerButtonAction(ActionEvent event) {
        client.SingleGameController.initializeGameScene();
        client.setScene(SinglePlayerScene);
    }

    @FXML
    protected void handleMultiplayerButtonAction(ActionEvent event) {
        client.setScene(MultiPlayerMenuScene);
    }

    @FXML
    protected void handleExitButtonAction(ActionEvent event) {
        System.exit(0);
    }
}
