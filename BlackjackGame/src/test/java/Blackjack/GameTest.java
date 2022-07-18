package Blackjack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DecimalFormat;
import java.util.List;

@TestInstance(Lifecycle.PER_CLASS)
public class GameTest {
    private static DecimalFormat df = new DecimalFormat("0.00");
    
    Game game;
    @BeforeAll
    public void execBeforeAll() {
        game = new Game();
    }

    @BeforeEach
    public void execBeforeEach() {
        game = new Game();
        game.newPlayer(50);
    }

    @Test
    public void testRestart() {
        game.restart();
        assertEquals(0, game.getPlayerScore(), "Player score should be 0");
        assertEquals(0, game.getDealerScore(), "Dealer score should be 0");
    }

    @Test
    public void testNewPlayer() {
        game.newPlayer(100);
        assertEquals(100, game.getWallet(), "Player wallet should be equal to the @param");
    }

    @Test
    public void testPlaceBet() {
        int initial_wallet = game.getWallet();
        game.placeBet(20);
        int wallet = game.getWallet();
        assertTrue(20 == (initial_wallet - wallet), "Bet money should be retrived from the players wallet");
    }

    @Test
    public void testPlayerHit() {
        int score = game.getPlayerScore();
        Card c = game.playerHit();
        assertNotNull(c, "aA card should always be drawn");
        if ( c.getValue() != 1 ) {
            assertTrue(game.getPlayerScore() == (score + c.getValue()), "Player score should be updated");
        }
        else {
            assertEquals(11, game.getPlayerScore());
        }

    }

    @Test
    public void testDealerHit() {
        int score = game.getPlayerScore();
        Card c = game.playerHit();
        assertNotNull(c, "aA card should always be drawn");
        if ( c.getValue() != 1 ) {
            assertTrue(game.getPlayerScore() == (score + c.getValue()), "Player score should be updated");
        }
        else {
            assertEquals(11, game.getPlayerScore(), "Only one ace so score should equal to 11S");
        }
    }

    @Test
    public void testCheckWin() {
        game.dealerHit();
        game.playerHit();
        if (game.getDealerScore() > game.getPlayerScore()) {
            assertTrue(game.checkWin() == 0, "Dealer should win");
        }
        else if(game.getDealerScore() < game.getPlayerScore()) {
            assertTrue(game.checkWin() == 1, "Player should win");
        }
        else {
            assertTrue(game.checkWin() == 2, "Player should tie");
        }
    }

    @Test
    public void testSettle() {
        game.placeBet(20);
        game.dealerHit();
        game.playerHit();
        game.settle();
        if(game.checkWin() == 1)
            assertEquals(70, game.getWallet(), "Add to wallet twice the bet value");
        else if (game.checkWin() == 0)    
            assertEquals(30, game.getWallet(), "Player Lost");
        else
            assertEquals(50, game.getWallet(), "Player tie");
    }

    @Test
    public void testGetStats() {
        Card c1 = game.playerHit();
        List<Double> stats = game.getStats();
        String[] names = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        int index = 0;
        for (int i = 0; i < 13; i ++)
            if(c1.getName() == names[i])
                index = i;

        assertEquals("5.88", df.format(stats.get(index)), "The probability value should be equal to 3/51 * 100 = 5.88");
    }



}