package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Unit test suite for GameLobby component
 *
 * @author Andy Gin
 */
@Tag("Application-tier")
public class GameLobbyTest {

    private static final String ONE = "one";
    private static final String TWO = "two";

    //friendly
    private Player player1;
    private Player player2;

    //mocks
    private Game game;

    @Mock
    private Map<Player, Game> games;
    @Mock
    private Map<Player, Game> spectators;

    /** The component-under-test (CuT) */
    @InjectMocks
    private GameLobby CuT;

    /**
     * Setup test
     */
    @BeforeEach
    public void testSetup() {

        //mocks
        game = mock(Game.class);

        //friendly
        player1 = new Player(ONE);
        player2 = new Player(TWO);

        //setup component-under-test
        CuT = new GameLobby();
        MockitoAnnotations.initMocks(this);

    }

    /**
     * Tests if player is in a game in lobby
     */
    @Test
    public void in_game() {
        when(games.containsKey(player1)).thenReturn(true);

        assertTrue(CuT.inGame(player1));
    }

    @Test
    public void not_in_game() {
        when(games.containsKey(player1)).thenReturn(false);

        assertFalse(CuT.inGame(player1));
    }

    @Test
    public void get_game_test() {
        when(games.get(player1)).thenReturn(game);

        assertEquals(CuT.getGame(player1), game);
    }

    @Test
    public void get_game_by_name() {
        when(games.get(new Player(ONE))).thenReturn(game);

        assertEquals(CuT.getGame(ONE), game);
    }

    @Test
    public void enter_game() {
        CuT.enterGame(player1, game);

        verify(games).put(player1, game);
    }

    @Test
    public void has_games() {
        List values =  mock(ArrayList.class);
        when(games.values()).thenReturn(values);
        when(values.isEmpty()).thenReturn(false);

        assertTrue(CuT.hasGames());
    }

    @Test
    public void has_no_games() {
        List values =  mock(ArrayList.class);
        when(games.values()).thenReturn(values);
        when(values.isEmpty()).thenReturn(true);

        assertFalse(CuT.hasGames());
    }

    @Test
    public void spectating_true() {
        when(spectators.containsKey(player1)).thenReturn(true);

        assertTrue(CuT.isSpectating(player1));
    }

    @Test
    public void not_spectating() {
        when(spectators.containsKey(player1)).thenReturn(false);

        assertFalse(CuT.isSpectating(player1));
    }

    @Test
    public void get_spectate_game() {
        when(spectators.get(player1)).thenReturn(game);

        assertEquals(CuT.getSpectateGame(player1), game);
    }

    @Test
    public void spectate_game() {
        CuT.spectateGame(player1, game);

        verify(spectators).put(player1, game);
    }

    @Test
    public void stop_spectating() {
        CuT.removeSpectator(player1);

        verify(spectators).remove(player1);
    }

    /**
     * Tests removing a game from lobby
     */
    @Test
    public void test_remove_game() {
        when(game.getWhitePlayer()).thenReturn(player2);
        when(game.getRedPlayer()).thenReturn(player1);

        //perform action
        CuT.endGame(game);

        verify(game).endGame();
        verify(games).remove(player1);
        verify(games).remove(player2);
    }

    @Test
    public void leave_game() {
        CuT.leaveGame(player1);

        verify(games).remove(player1);
    }
}
