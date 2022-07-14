package Blackjack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private int playerScore, dealerScore, wallet, bet, nCards;
    private int[] stats;
    private Deck deck;

    public Game () {
        playerHand = new ArrayList<Card>();
        dealerHand = new ArrayList<Card>();
        deck = new Deck();
        playerScore = 0;
        dealerScore = 0;
        wallet = 0;
        bet = 0;
        nCards = 0;
        stats = new int[10];
        resetStats();
    }

    public void restart() {
        playerHand.clear();
        dealerHand.clear();
        playerScore = 0;
        dealerScore = 0;
        nCards = 0;
        bet = 0;
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
        for(int i = 0; i < 10; i++) {
            stats[i] = 4;
        }
    }

    public Card playerHit() {
        Card c = deck.getCard();
        playerHand.add(c);
        stats[c.getValue() -1] -= 1;
        nCards ++;
        if (c.getValue() != 1) {
            playerScore += c.getValue();
        }  
        else {
            aceP++;
        }
        return c;
    }

    public Card dealerHit() {
        Card c = deck.getCard();
        dealerHand.add(c);
        stats[c.getValue() -1] -= 1;
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
        return playerScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public List<Card> getPlayerCards () {
        return playerHand;
    }

    public List<Card> getDealerCards () {
        return dealerHand;
    }

    public boolean checkWin() {
        if(playerScore > 21 ) {
            return false;
        }
        if(dealerScore > 21) {
            return true;
        }
        if (dealerScore > playerScore) {
            return false;
        }
        return true;
    }

    public void settle() {
        if(checkWin()) {
            wallet += (bet*2);
        }
    }

    public int getWallet() {
        return wallet;
    }

    public List<Integer> getStats() {
        int cardsLeft = 52 - nCards;
        List<Integer> aux = new LinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            aux.add((stats[i]/cardsLeft)*100);
        }
        return aux;
    }

}
