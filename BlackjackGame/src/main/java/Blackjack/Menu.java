package Blackjack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;   

public class Menu {

    private static JFrame frame;
    private static JLabel title;
    private static JLabel ligmaBalls;
    private static boolean ligma=false;

    public static void main(String args[]){
        frame = new JFrame("Blackjack Game");
        frame.getContentPane().setBackground(Color.decode("#17a100"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,600);
        frame.setLayout(new GridBagLayout());
        
        title = new JLabel("BlackJack", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));    
        ligmaBalls = new JLabel("",JLabel.CENTER); 
        ligmaBalls.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));   
        ligmaBalls.setSize(100,100);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 40)); 
        newGameButton.setFocusable(false);
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 40)); 
        exitButton.setFocusable(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;

        frame.add(title,constraints);
        constraints.gridy = 1;
        frame.add(newGameButton,constraints);       
        constraints.gridy = 2;
        frame.add(exitButton, constraints);
        constraints.gridy = 3;
        frame.add(ligmaBalls,constraints);

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               ligmaBalls.setText("Ligma balls");
               ligma = !ligma;
               ligmaBalls.setVisible(ligma);  
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
