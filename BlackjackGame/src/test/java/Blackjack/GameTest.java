package Blackjack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(Lifecycle.PER_CLASS)
public class GameTest {
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
        double ace_played = game.getStats().get(0);
        Card c = game.playerHit();
        assertNotNull(c, "aA card should always be drawn");
        if ( c.getValue() != 1 ) {
            assertTrue(game.getPlayerScore() == (score + c.getValue()), "Player score should be updated");
        }
        else {
            assertEquals(score, game.getPlayerScore());
            assertTrue(game.getStats().get(0) == (ace_played + 1), "An ace should be played");
        }

    }

    @Test
    public void testDealerHit() {
        int score = game.getPlayerScore();
        double ace_played = game.getStats().get(0);
        Card c = game.playerHit();
        assertNotNull(c, "aA card should always be drawn");
        if ( c.getValue() != 1 ) {
            assertTrue(game.getPlayerScore() == (score + c.getValue()), "Player score should be updated");
        }
        else {
            assertEquals(score, game.getPlayerScore());
            assertTrue(game.getStats().get(0) == (ace_played + 1), "An ace should be played");
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






}