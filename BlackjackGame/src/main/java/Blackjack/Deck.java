package Blackjack;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
    private Queue<Card> deck = new LinkedList<Card>();
    private List<Card> cards = new LinkedList<Card>();

    public Deck(){
        String[] names = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] figures = {"Spades","Hearts","Diamonds","Clubs"};     
        int value = 1;
        for(String figure : figures) {
            for(String name : names) {
                cards.add(new Card(value, name, figure));
                if (value < 10)
                    value++;
            } 
            value = 1;
        }
        newDeck();
    }

    public void newDeck() {
        deck.clear();
        Collections.shuffle(cards);
        for(Card card : cards)
            deck.add(card);   
    }

    public Card getCard(){
        if(deck.isEmpty())
            newDeck();
        return deck.poll();
    }
}