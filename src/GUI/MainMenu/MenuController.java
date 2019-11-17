package GUI.MainMenu;

import Client.Client;
import GUI.SinglePlayerMenu.Single_GameMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MenuController {
    private Scene scene2;
    private Client main;

    public void setMain(Client main){
        this.main = main;
    }
    public void setScene2(Scene scene2){
        this.scene2 = scene2;
    }

    @FXML
    private Button StartSingle;

    @FXML
    private Button StartMulti;

    @FXML
    private Button Exit;

    @FXML
    protected void handleSingleplayerButtonAction(ActionEvent event) {
        main.controller2.initializeGameScene();
        main.setScene(scene2);
    }

    @FXML
    protected void handleMultiplayerButtonAction(ActionEvent event) {

    }

    @FXML
    protected void handleExitButtonAction(ActionEvent event) {
        System.exit(0);
    }
}
