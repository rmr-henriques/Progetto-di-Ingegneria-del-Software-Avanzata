package Blackjack;
/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ){
        Deck deck = new Deck();
        Card card = deck.getCard();
        System.out.println(card.getName()+" of "+card.getFigure());
    }
}
