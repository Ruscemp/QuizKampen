package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitingForPlayer {
    private JButton quitBtn;
    private JLabel waitingText;

    public WaitingForPlayer() {

        waitingText.setText("Väntar på att motspelare ska ansluta..");

        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Do some action that cancels the game
            }
        });

    }
}
