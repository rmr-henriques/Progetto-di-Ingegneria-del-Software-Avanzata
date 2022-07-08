package Blackjack;


public class Card {
    private int value;
    private int card;
    private String figure;

    public Card (int value, int card, String figure) {
        this.value = value;
        this.card = card;
        this.figure = figure;
    }

    public int getCard() {
        return card;
    }

    public String getFigure() {
        return figure;
    }

    public int getValue() {
        return value;
    }
}