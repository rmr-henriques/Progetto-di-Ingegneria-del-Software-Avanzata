package Blackjack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu {

    private static JLabel title;
    private static JFrame frame;

    public Menu(JFrame frame) {
        Menu.frame = frame;
        showMenu();
    }

    private void showMenu() {
        frame.setLayout(new GridBagLayout());

        title = new JLabel("BlackJack", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));

        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        newGameButton.setFocusable(false);
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        exitButton.setFocusable(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;

        frame.add(title, constraints);
        constraints.gridy = 1;
        frame.add(newGameButton, constraints);
        constraints.gridy = 2;
        frame.add(exitButton, constraints);

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                new Game(frame);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}
