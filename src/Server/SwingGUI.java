package Server;

import Client.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGUI {
    public JPanel panelMain;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel buttonPanel;
    private JPanel questionPanel;
    private JLabel questionLabel;
    private JPanel scorePanel;
    private JLabel scoreLabel;

    private Question question;

    public SwingGUI(String s) {


        button1.setText(s);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.setText("new text");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
//    // setters
//    public void setQuestionInGUI(Question question) {
//        this.question = question;
//    }
//
//    // getters
//    public Question getQuestion() {
//        return question;
//    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Quizkampen");
//        frame.setContentPane(new SwingGUI().panelMain);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        frame.pack();
//        frame.setSize(500, 500);
//        frame.setVisible(true);
//    }

    public void startGui() {
        JFrame frame = new JFrame("Quizkampen");
        frame.setContentPane(new SwingGUI("hej").panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}