package Blackjack;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Game g = new Game();
        Scanner s = new Scanner(System.in);
        System.out.println( "New Game!" );
        System.out.println("Starting money: ");
        int money = s.nextInt();
        g.newPlayer(money);
        System.out.println("Starting bet: ");
        int bet = s.nextInt();
        g.placeBet(bet);
        Card c1 = g.playerHit();
        Card c2 = g.playerHit();
        System.out.println("Player: " + c1.getName() + " of " + c1.getFigure() + ", " + c2.getName() + " of " + c2.getFigure());
        System.out.println("Player score: " +  g.getPlayerScore());

        System.out.println("Dealer score: " +  g.getDealerScore());

        System.out.println("hit or stand?");
        String move = s.nextLine().trim();
        while (!move.equals("stand")) {
            g.playerHit();
            System.out.println("Player score: " +  g.getPlayerScore());
            System.out.println("hit or stand?");
            move = s.nextLine().trim();
        }
        while(g.getDealerScore() < 21 ) {
             System.out.println("Dealer score: "+ g.getDealerScore());
        }
        if(g.checkWin() == 1) {
            System.out.println("Player wins");
            g.settle();
            System.out.println("funds: " + g.getWallet());
        }
        else {
            System.out.println("Player looses.");
        }

    }
}
