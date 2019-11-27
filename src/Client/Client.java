package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Client extends JFrame implements ActionListener, Runnable {
    String[] split;
    int loop = 0;
    Timer timer;
    JPanel panelMain = new JPanel();
    JPanel questionPanel = new JPanel();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton clickedButton;
    Color defaultColor = button1.getBackground();
    JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
    JTextArea questionArea = new JTextArea();
    JTextArea scoreLabel = new JTextArea();
    List<JButton> buttonList = new ArrayList<>();
    ObjectInputStream in;
    PrintWriter out;


    Client() {
        String hostName = "localhost";
        int portNumber = 11121;

        add(panelMain, BorderLayout.NORTH);
        add(questionPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(400, 600);
        setVisible(true);

        questionPanel.add(questionArea);
        questionPanel.add(scoreLabel);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        questionArea.setLineWrap(true);

        buttonPanel.setPreferredSize(new Dimension(400, 200));
        questionPanel.setPreferredSize(new Dimension(400, 400));
        questionArea.setEditable(false);
        questionArea.setPreferredSize(new Dimension(380, 200));
        questionArea.setText("Waiting for opponent");

        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);

        addActionListener();

        try {
            Socket addressSocket = new Socket(hostName, portNumber);
            in = new ObjectInputStream(addressSocket.getInputStream());
            out = new PrintWriter(addressSocket.getOutputStream(), true);

            Object fromServer;
            String fromUser;

            disableButtons();
            while ((fromServer = in.readObject()) != null) {
                System.out.println(fromServer.toString());
                 if (fromServer.toString().contains(":")){
                    split = fromServer.toString().split(";");
                    scoreLabel.setText(split[0]);
                    questionPanel.updateUI();
                    buttonPanel.updateUI();
                    if (split.length>1&&split[1].equals("OVER")){
                        disableButtons();
                        JOptionPane.showConfirmDialog(null, "Game Is Over\n"+scoreLabel.getText(), "Game Over", JOptionPane.DEFAULT_OPTION);
                        System.exit(0);
                    }
                }else if (fromServer.toString().contains(";")) {
                    enableButtons();
                    split = fromServer.toString().split(";");
                    questionArea.setText(split[1]);
                    button1.setText(split[2]);
                    button2.setText(split[3]);
                    button3.setText(split[4]);
                    button4.setText(split[5]);
                    questionPanel.updateUI();
                    buttonPanel.updateUI();
                }else if(fromServer.toString().equals("END")) {
                    disableButtons();
                    questionArea.setText("Opponents turn\nWait!");
                } else {
                    enableButtons();
                    split = fromServer.toString().split("\n");
                    Collections.shuffle(Arrays.asList(split));
                    questionArea.setText("Choose Category");
                    button1.setText(split[2]);
                    button2.setText(split[3]);
                    button3.setText(split[4]);
                    button4.setText(split[5]);
                    questionArea.setText("Choose a category!");
                    questionPanel.updateUI();
                    buttonPanel.updateUI();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        Client c = new Client();
    }

    public void addActionListener() {
        for (JButton button : buttonList) {
            button.addActionListener(l -> {
                clickedButton = button;
                if (split.length>6&&clickedButton.getText().trim().equalsIgnoreCase(split[6].trim())){
                    clickedButton.setBackground(Color.green);
                    questionArea.setText("Correct!\n");
                } else if(split.length>6) {
                    clickedButton.setBackground(Color.RED);
                    questionArea.setText("False!\n");
                }
                if (split.length>6){
                    timer = new Timer(100, this::changeColor);
                    timer.setInitialDelay(0);
                    timer.start();
                } else {
                    out.println(clickedButton.getText().trim());
                }
            });
        }
    }

    private void enableButtons(){
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
    }
    private void disableButtons(){
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
    }

    private void changeColor(ActionEvent actionEvent) {
        if (loop == 5){
            timer.setRepeats(false);
            clickedButton.setBackground(defaultColor);
            out.println(clickedButton.getText().trim());
            loop = 0;
        }else if(timer.isRepeats()){
            String[] split2 = questionArea.getText().split("\n");
            questionArea.setText(split2[0]+"\nNext Question in: "+(5-loop)+" seconds!");
        }
        loop++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
    }
}
