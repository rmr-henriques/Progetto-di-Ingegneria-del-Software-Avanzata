package Blackjack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  

public class Game {

    private static JFrame frame;

    public Game(JFrame frame){ 
        Game.frame = frame;

        showGame();
    }

    private void showGame() {
        JLabel title = new JLabel("Ligma Balls", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));
        
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        exitButton.setFocusable(false);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                new Menu(frame);
            }
        });

        frame.add(title);
        frame.add(exitButton);
        frame.setVisible(true);
    }
}
