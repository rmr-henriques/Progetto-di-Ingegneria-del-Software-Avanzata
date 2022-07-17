package Blackjack.FrontEnd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Blackjack.BackEnd.Card;
import Blackjack.BackEnd.Game;  

public class GameScene {

    private static JFrame frame;
    private static JPanel panel;
    private static Game g;
    private static int bet;

    public GameScene(JFrame frame){ 
        GameScene.frame = frame;
        g = new Game();
        showGame();
    }

    private void showGame() {
        g.newPlayer(100);

        frame.setLayout(new GridLayout(2, 1));

        JLabel text = new JLabel("Select a bet:", JLabel.CENTER);
        text.setFont(new Font("Comic Sans MS", Font.PLAIN, 75));
        frame.add(text);

        panel = new JPanel();
        panel.setBackground(Color.decode("#17a100"));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        bet = 10;
        createBetButton();
        bet = 20;
        createBetButton();
        bet = 50;
        createBetButton();

        frame.add(panel);

        frame.setVisible(true);
    }

    private void createBetButton(){
        JButton button = new JButton(Integer.toString(bet));
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));
        button.setFocusable(false);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g.placeBet(bet);
                gameScreen();
            }
        });

        panel.add(button);
    }

    private void gameScreen(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setLayout(new GridLayout(3, 1, 0, 50));

        Card c1 = g.playerHit();
        Card c2 = g.playerHit();
        Card c3 = g.dealerHit();

        JLabel dealerInfo = new JLabel("<html>Dealer: <br/>" + c3.getName() + " of " + c3.getFigure() +"<br/> Score: " + g.getDealerScore() + "</html>", JLabel.CENTER);
        dealerInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); 
        frame.add(dealerInfo);

        JLabel cardsInfo = new JLabel("<html>Player: <br/>" + c1.getName() + " of " + c1.getFigure() + "<br/>" + c2.getName() + " of " + c2.getFigure()+ "<br/> Score: " + g.getPlayerScore() + "<html>", JLabel.CENTER);
        cardsInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        frame.add(cardsInfo);
        
        panel = new JPanel();
        panel.setBackground(Color.decode("#17a100"));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20,50));

        JButton holdButton = new JButton("Hold");
        holdButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        holdButton.setFocusable(false);

        holdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //todo
            }
        });

        panel.add(holdButton);

        JButton hitButton = new JButton("Hit");
        hitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        hitButton.setFocusable(false);

        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //todo
                Card c4 = g.playerHit();
                
            }
        });

        panel.add(hitButton);

        frame.add(panel);

        frame.setVisible(true);
    }
}
