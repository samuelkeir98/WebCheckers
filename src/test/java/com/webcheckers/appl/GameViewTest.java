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
 * Unit test suite for GameView component
 *
 * @author Andy Gin
 */
public class GameViewTest {

    private static final String name = "name";

    private Player player;

    @Mock
    private Game game;

    /** The component-under-test (CuT) */
    @InjectMocks
    private GameView CuT;

    @BeforeEach
    public void setup() {
        player = new Player(name);

        //setup component-under-test
        CuT = new GameView(game);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void get_red() {
        when(game.getRedPlayer()).thenReturn(player);
        assertEquals(CuT.getRedPlayer(), player);
    }

    @Test
    public void get_white() {
        when(game.getWhitePlayer()).thenReturn(player);
        assertEquals(CuT.getWhitePlayer(), player);
    }

    @Test
    public void display() {
        Player other = mock(Player.class);
        when(game.getWhitePlayer()).thenReturn(other);
        when(game.getRedPlayer()).thenReturn(other);
        when(other.getName()).thenReturn(name);
        assertEquals(CuT.display(), name + " vs. " + name);
    }
}
