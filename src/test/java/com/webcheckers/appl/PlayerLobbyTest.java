package com.webcheckers.appl;

import com.webcheckers.model.Player;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.HashSet;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Collection;

/**
 * unit test for player lobby component
 * @author Samuel Keir
 */

@Tag("Application-tier")
public class PlayerLobbyTest {

    // component under test
    private PlayerLobby CuT ;
    /**
     * Test signin
     */
    @Test
    public void signInPlayerName() {
        // perform action
        CuT.signin("Bob");
        //analyze results
        //assertTrue(CuT.contains("Bob"));
    }

    @Test
    /**
     * Test signout
     */
    public void signOutPlayer() {
        // perform action
        Player bob = new Player("Bob");
        CuT.signout(bob);
        // analyze results
        //assertFalse(CuT.contains("Bob"));
    }

    @Test
    /**
     * Test get players
     */
    public void getPlayersCollection() {
        // perform action
        Collection<Player> players = CuT.getPlayers();
        // analyze results
        assertTrue(players.size() == 0);
    }

    @Test
    /**
     * Test num players
     */
    public void numPlayersSize() {
        // analyze results
        //assertTrue(CuT.size == CuT.numPlayers());
    }
}
