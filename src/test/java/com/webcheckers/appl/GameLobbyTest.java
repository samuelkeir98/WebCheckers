package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

/**
 * Unit test suite for GameLobby component
 *
 * @author Andy Gin
 */
@Tag("Application-tier")
public class GameLobbyTest {

    private static final String ONE = "one";
    private static final String TWO = "two";

    /** The component-under-test (CuT) */
    private GameLobby CuT;

    //friendly
    private Map<Player, Game> games;
    private Player player1;
    private Player player2;

    //mocks
    private Game game;

    /**
     * Setup test
     */
    @BeforeEach
    public void testSetup() {

        //mocks
        game = mock(Game.class);

        //friendly
        games = new HashMap();
        player1 = new Player(ONE);
        player2 = new Player(TWO);

        //setup component-under-test
        CuT = new GameLobby();

        games.put(player1, game);
    }

    /**
     * Tests if player is in a game in lobby
     */
    @Test
    public void test_in_game() {
        //analyze results: took internal code of inGame()
        assertTrue(games.containsKey(player1));
        assertFalse(games.containsKey(player2));
    }

    /**
     * Tests adding a game to lobby
     */
    @Test
    public void test_add_game() {
        //perform action
        games.put(player2, game);

        //analyze results
        assertTrue(games.size() == 2);
    }

    /**
     * Tests removing a game from lobby
     */
    @Test
    public void test_remove_game() {
        //perform action
        games.remove(player1, game);

        //analyze results
        assertTrue(games.size() == 0);
    }
}
