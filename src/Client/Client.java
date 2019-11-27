package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;


public class Client extends JFrame implements ActionListener {
    String[] split;
    JFrame frame =new JFrame();
    JPanel panelMain = new JPanel();
    JPanel questionPanel = new JPanel();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JPanel buttonPanel = new JPanel(new GridLayout(2,2));
    JTextArea questionArea = new JTextArea();
    JTextArea scoreLabel = new JTextArea();

    String toServer;
    PrintWriter out;

    Client() {
        String hostName = "localhost";
        int portNumber = 11111;
        add(panelMain, BorderLayout.NORTH);
        add(questionPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(400, 600);
        setVisible(true);
        questionPanel.add(questionArea);
        questionPanel.add(scoreLabel);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        ObjectInputStream in;
        PrintWriter out;

        buttonPanel.setPreferredSize(new Dimension(400,200));
        questionPanel.setPreferredSize(new Dimension(400,400));
        questionArea.setEditable(false);
        questionArea.setPreferredSize(new Dimension(400,200));


        try {Socket addressSocket = new Socket(hostName, portNumber);
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
                    questionPanel.updateUI();
                }
                else {
                    split = fromServer.toString().split("\n");
                    Collections.shuffle(Arrays.asList(split));
                    button1.setText(split[2]);
                    button2.setText(split[3]);
                    button3.setText(split[4]);
                    button4.setText(split[5]);
                }

                System.out.println(toServer);
                System.out.println("hej baso");

                if(toServer != null) {
                    out.println(toServer);
                }

                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                fromUser = stdIn.readLine();
                if (fromUser != null) {

                    out.println(fromUser);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void actionPerformed (ActionEvent e){
        if(e.getSource() == button1)
            out.println(button1.getText());
        if(e.getSource() == button2)
            out.println(button2.getText());
        if(e.getSource() == button3)
            out.println(button3.getText());
        if(e.getSource() == button4)
            out.println(button4.getText());
    }

    public static void main(String[] args) {
        Client c = new Client();
    }

}
