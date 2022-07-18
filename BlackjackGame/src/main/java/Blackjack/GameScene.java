package Blackjack;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameScene {

    private static JFrame frame;
    private static JPanel panel;
    private static Game g;
    private static int bet;
    private static JLabel dealerInfo;
    private static JLabel cardsInfo;
    private static final double SCALING = 0.35;
    private static final int WIDTH = 255;
    private static final int HEIGHT = 380;
    private static JPanel dealerPanel;
    private static JPanel playerPanel;

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
                try {
                    gameScreen();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel.add(button);
    }

    private void gameScreen() throws IOException{
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setLayout(new GridLayout(5, 1, 0, 50));
        JPanel dummy = new JPanel();
        dummy.setBackground(Color.decode("#17a100"));
        frame.add(dummy);

        dealerPanel = new JPanel();
        dealerPanel.setBackground(Color.decode("#17a100"));
        dealerPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 

        playerPanel = new JPanel();
        playerPanel.setBackground(Color.decode("#17a100"));
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 

        g.dealerHit();
        
        dealerInfo = new JLabel("<html>Dealer: <br/> Score: " + g.getDealerScore() + "</html>", JLabel.CENTER);
        dealerInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        dealerPanel.add(dealerInfo);
        
        dealerPanel.add(showPictures(1));

        frame.add(dealerPanel);

        g.playerHit();
        g.playerHit();

        cardsInfo = new JLabel("<html>Player: <br/> Score: " + g.getPlayerScore() + "<html>", JLabel.CENTER);
        cardsInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));   
        playerPanel.add(cardsInfo);

        playerPanel.add(showPictures(0));

        frame.add(playerPanel);
        
        panel = new JPanel();
        panel.setBackground(Color.decode("#17a100"));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20,50));

        JButton holdButton = new JButton("Hold");
        holdButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        holdButton.setFocusable(false);

        holdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g.playDealer();
                String text = "<html>Dealer: <br/>";
                text += "Score: " + g.getDealerScore() + "<html>";
                dealerInfo.setText(text);

                dealerPanel.removeAll();
                dealerPanel.add(dealerInfo);

                try {
                    dealerPanel.add(showPictures(1));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

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
                String text = "<html>Player: <br/>";
                text += "Score: " + g.getPlayerScore() + "<html>";
                cardsInfo.setText(text);

                playerPanel.removeAll();
                playerPanel.add(cardsInfo);

                try {
                    playerPanel.add(showPictures(0));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

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

    private JPanel showPictures(int user) throws IOException{
        JPanel card = new JPanel();
        card.setBounds(50, 50, 500, 500);
        
        List<Card> cards;
        if(user == 0)
            cards = g.getPlayerCards();
        else 
            cards = g.getDealerCards();

        for(Card c : cards){
            String cardPath = "Cards/"+c.getFigure()+"/"+c.getName()+".png";
            Image cardImage = ImageIO.read(getClass().getResource(cardPath));
            cardImage = cardImage.getScaledInstance((int)(WIDTH*SCALING),(int)(HEIGHT*SCALING), Image.SCALE_FAST);
            JLabel cardPic = new JLabel(new ImageIcon(cardImage));
            card.add(cardPic);
        }
        return card;
    }
}
