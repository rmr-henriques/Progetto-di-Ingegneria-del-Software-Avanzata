package Blackjack.BackEnd;

public class Card {
    private int value;
    private String name;
    private String figure;

    public Card (int value, String name, String figure) {
        this.value = value;
        this.name = name;
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public String getFigure() {
        return figure;
    }

    public int getValue() {
        return value;
    }
}