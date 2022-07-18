package Blackjack;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

public class GameScene {

    private static JFrame frame;
    private static JPanel panel;
    private static Game g;
    private static int bet;
    private static JLabel dealerInfo;
    private static JLabel cardsInfo;

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

        dealerInfo = new JLabel("<html>Dealer: <br/>" + c3.getName() + " of " + c3.getFigure() +"<br/> Score: " + g.getDealerScore() + "</html>", JLabel.CENTER);
        dealerInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); 
        frame.add(dealerInfo);

        cardsInfo = new JLabel("<html>Player: <br/>" + c1.getName() + " of " + c1.getFigure() + "<br/>" + c2.getName() + " of " + c2.getFigure()+ "<br/> Score: " + g.getPlayerScore() + "<html>", JLabel.CENTER);
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
                g.playDealer();
                List<Card> dealer = g.getDealerCards();
                String text = "<html>Dealer: <br/>";
                for (Card c : dealer)
                    text += c.getName() + " of " + c.getFigure() + "<br/>";
                text += "Score: " + g.getDealerScore() + "<html>";
                dealerInfo.setText(text);

                String result = ""; 
                if(g.checkWin() == 1)
                    result = "You Win!";
                else if (g.checkWin() == 0)
                    result = "You Lose...";
                else 
                    result = "You Tie.";
                
                JLabel resultLabel = new JLabel(result,JLabel.CENTER);
                resultLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

                panel.removeAll();

                JButton betAgain = new JButton("Bet");
                betAgain.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                betAgain.setFocusable(false);
                betAgain.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        new GameScene(frame);
                    }
                });

                panel.add(betAgain);

                JButton backToMenu = new JButton("Main Menu");
                backToMenu.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                backToMenu.setFocusable(false);
                backToMenu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        new Menu(frame);
                    }
                });

                panel.add(backToMenu);

                panel.add(resultLabel);

                frame.repaint();
            }
        });

        panel.add(holdButton);

        JButton hitButton = new JButton("Hit");
        hitButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        hitButton.setFocusable(false);

        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g.playerHit();
                List<Card> player = g.getPlayerCards();
                String text = "<html>Player: <br/>";
                for (Card c : player)
                    text += c.getName() + " of " + c.getFigure() + "<br/>";
                text += "Score: " + g.getPlayerScore() + "<html>";
                cardsInfo.setText(text);

                int r = g.getPlayerScore();
                if ( r > 21) {
                    JLabel resultLabel = new JLabel("You Lose...",JLabel.CENTER);
                    resultLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

                    panel.removeAll();

                    JButton betAgain = new JButton("Bet");
                    betAgain.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                    betAgain.setFocusable(false);
                    betAgain.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        new GameScene(frame);
                        }
                    });

                    panel.add(betAgain);
    
                    JButton backToMenu = new JButton("Main Menu");
                    backToMenu.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                    backToMenu.setFocusable(false);
                    backToMenu.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            frame.getContentPane().removeAll();
                            frame.repaint();
                            new Menu(frame);
                        }
                    });
    
                    panel.add(backToMenu);
    
                    panel.add(resultLabel);
    
                    frame.repaint();
                }

                frame.repaint();
            }
        });

        panel.add(hitButton);

        frame.add(panel);

        frame.setVisible(true);
    }
}
