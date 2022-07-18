package Blackjack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Game {
    
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private int playerScore, dealerScore, wallet, bet, aceP, aceD;
    private double nCards;
    private Map<String, Double> stats;
    private Deck deck;
    private String[] cards = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};


    public Game () {
        playerHand = new LinkedList<Card>();
        dealerHand = new LinkedList<Card>();
        deck = new Deck();
        playerScore = 0;
        dealerScore = 0;
        wallet = 0;
        bet = 0;
        nCards = 0.0;
        aceP = 0;
        aceD = 0;
        stats = new HashMap<String,Double>();
        resetStats();
        
    }

    public void restart() {
        playerHand.clear();
        dealerHand.clear();
        playerScore = 0;
        dealerScore = 0;
        nCards = 0;
        bet = 0;
        aceP = 0;
        aceD = 0;
        deck.newDeck();
        resetStats();
    }

    public void newPlayer(int wallet) {
        this.wallet = wallet;
    }

    public void placeBet(int b) {
        bet = b;
        wallet -= b;
    }

    private  void resetStats() {
        stats.clear();
        for (String s : cards) {
            stats.put(s, 4.0);
        }
    }

    public Card playerHit() {
        Card c = deck.getCard();
        playerHand.add(c);
        double cur = stats.get(c.getName());
        cur --;
        stats.put(c.getName(), cur);
        nCards ++;
        if (c.getValue() != 1) {
            playerScore += c.getValue();
        }  
        else {
            aceP++;
        }
        return c;
    }

    public void playDealer() {
        while(getDealerScore() < 21) {
            dealerHit();
            if (getDealerScore() >= 17) {
                break;
            }
        }
    }

    public Card dealerHit() {
        Card c = deck.getCard();
        dealerHand.add(c);
        double cur = stats.get(c.getName());
        cur--;
        stats.put(c.getName(), cur);
        nCards++;
        if (c.getValue() != 1) {
            dealerScore += c.getValue();
        }  
        else {
            aceD ++;
        }
        return c;
    } 

    public int getPlayerScore() {
        int aux = playerScore;
        for (int i = 0; i <aceP; i++) {
            if((aux + 11) < 21) {
                aux +=11;
            }
            else {
                aux++;
            }
        }
        return aux;
    }

    public int getDealerScore() {
        int aux = dealerScore;
        for (int i = 0; i <aceD; i++) {
            if((aux + 11) < 21) {
                aux +=11;
            }
            else {
                aux++;
            }
        }
        return aux;
    }

    public List<Card> getPlayerCards () {
        return playerHand;
    }

    public List<Card> getDealerCards () {
        return dealerHand;
    }

    // return 0 if dealer wins, 1 if player wins, 2 if its a tie
    public int checkWin() {
        int a = getPlayerScore();
        int b = getDealerScore();
        if(a > 21 ) {
            return 0;
        }
        if(b > 21) {
            return 1;
        }
        if (b > a) {
            return 0;
        }
        if (a == b) {
            return 2;
        }
        return 1;
    }

    public void settle() {
        if(checkWin() == 1) {
            wallet += (bet*2);
        }
        if(checkWin() == 2) {
            wallet += bet;
        }
    }

    public int getWallet() {
        return wallet;
    }

    public List<Double> getStats() {
        double cardsLeft = 52.0 - nCards;
        List<Double> aux = new LinkedList<Double>();
        for (int i = 0; i < 13; i++) {
            System.out.println(stats.get(cards[i]) + "  " + cardsLeft);
            double chance = 100.0 * (stats.get(cards[i])/cardsLeft);
            System.out.println("BOOBA " + chance);
            aux.add(chance);
        }
        return aux;
    }

}