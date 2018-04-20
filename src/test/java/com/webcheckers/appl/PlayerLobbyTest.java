package com.webcheckers.appl;

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
 * Unit test suite for PlayerLobby component
 *
 * @author Andy Gin
 */
@Tag("Application-tier")
public class PlayerLobbyTest {

    private static final String name = "one";

    @Mock
    private Map<String, Player> players;

    private Player player;

    /** The component-under-test (CuT) */
    @InjectMocks
    private PlayerLobby CuT;

    @BeforeEach
    public void setup() {
        player = new Player(name);
        //setup component-under-test
        CuT = new PlayerLobby();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sign_out() {
        CuT.signout(player);

        verify(players).remove(player.getName());
    }

    @Test
    public void get_players() {
        List values = mock(ArrayList.class);
        when(players.values()).thenReturn(values);

        assertEquals(CuT.getPlayers(), values);
    }

    @Test
    public void num_players() {
        when(players.size()).thenReturn(0);

        assertEquals(CuT.numPlayers(), 0);
    }

    @Test
    public void get_player() {
        when(players.get(name)).thenReturn(player);

        assertEquals(CuT.getPlayer(name), player);
    }
}
