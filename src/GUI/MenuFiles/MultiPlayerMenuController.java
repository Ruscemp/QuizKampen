package GUI.MenuFiles;

import Client.Client;
import Client.ServerHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MultiPlayerMenuController {

    public TextField ServerIP;
    public TextField ServerPort;
    public Label ConnectionResult;
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
        ServerHandler.hostName = ServerIP.getText();
        ServerHandler.hostPort = ServerPort.getText();
        Client.threads.add(4, new ServerHandler());
        Client.threads.get(4).start();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Client.threads.get(4).isAlive()){
            client.MultiGameController.initializeGameScene();
            client.setScene(multiGame);
        } else {
            if (!ServerHandler.getException().equalsIgnoreCase("")){
                ConnectionResult.setText("Connection refused! "+ ServerHandler.getException());
            }else {
                ConnectionResult.setText("Connection refused! Empty Server ip and/or port!");
            }
        }
    }
}