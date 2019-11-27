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


public class Client extends JFrame implements ActionListener {
    String[] split;
    JPanel panelMain = new JPanel();
    JPanel questionPanel = new JPanel();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
    JTextArea questionArea = new JTextArea();
    JTextArea scoreLabel = new JTextArea();
    List<JButton> buttonList = new ArrayList<>();
    ObjectInputStream in;
    PrintWriter out;


    Client() throws IOException {
        String hostName = "localhost";
        int portNumber = 11111;

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

        buttonPanel.setPreferredSize(new Dimension(400, 200));
        questionPanel.setPreferredSize(new Dimension(400, 400));
        questionArea.setEditable(false);
        questionArea.setPreferredSize(new Dimension(400, 200));

        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);

        addActionListener();

        try {
            Socket addressSocket = new Socket(hostName,
                    portNumber);
            in = new ObjectInputStream(addressSocket.getInputStream());
            out = new PrintWriter(addressSocket.getOutputStream(), true);

            Object fromServer;
            String fromUser;



            while ((fromServer = in.readObject()) != null) {
                System.out.println(fromServer.toString());
                if (fromServer.toString().contains(",")) {
                    split = fromServer.toString().split(",");
                    questionArea.setText(split[1]);
                    button1.setText(split[2]);
                    button2.setText(split[3]);
                    button3.setText(split[4]);
                    button4.setText(split[5]);
                } else if (fromServer.toString().contains(":")){
                    scoreLabel.setText(fromServer.toString());
                }
                else {
                    split = fromServer.toString().split("\n");
                    Collections.shuffle(Arrays.asList(split));
                    button1.setText(split[2]);
                    button2.setText(split[3]);
                    button3.setText(split[4]);
                    button4.setText(split[5]);
                }

//                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//                fromUser = stdIn.readLine();
//                if (fromUser != null) {
//                    out.println(fromUser);
//                }
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
                out.println(button.getText());
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
