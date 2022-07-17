package Blackjack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(Lifecycle.PER_CLASS)
@Disabled
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
    @DisplayName("Test Restart the game")
    public void testRestart() {
        game.restart();
        assertEquals(0, game.getPlayerScore(), "Player score should be 0");
        assertEquals(0, game.getDealerScore(), "Dealer score should be 0");
    }

    @Test
    @DisplayName("Test Add New Player")
    public void testNewPlayer() {
        game.newPlayer(100);
        assertEquals(100, game.getWallet(), "Player wallet should be equal to the @param");
    }

    @Test
    @DisplayName("Test Place a Bet")
    public void testPlaceBet() {
        int initial_wallet = game.getWallet();
        game.placeBet(20);
        int wallet = game.getWallet();
        assertTrue(20 == (initial_wallet - wallet), "Bet money should be retrived from the players wallet");
    }

    @Test
    @DisplayName("Test Draw Player Card")
    public void testPlayerHit() {
        int score = game.getPlayerScore();
        int ace_played = game.getStats().get(0);
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
    @DisplayName("Test Draw Dealer a Card")
    public void testDealerHit() {
        int score = game.getPlayerScore();
        int ace_played = game.getStats().get(0);
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
    @DisplayName("Test Check who Wins the Game")
    public void testCheckWin() {
        game.dealerHit();
        game.playerHit();
        if (game.getDealerScore() > game.getPlayerScore()) {
            assertFalse(game.checkWin(), "Dealer should win");
        }
        else {
            assertTrue(game.checkWin(), "Player should win");
        }
    }

    @Test
    @DisplayName("Test Give back Bet Money")
    public void testSettle() {
        game.placeBet(20);
        game.dealerHit();
        game.playerHit();
        game.settle();
        if(game.checkWin())
            assertEquals(90, game.getWallet(), "Add to wallet twice the bet value");
        else    
            assertEquals(50, game.getWallet(), "Player Lost");
    }






}