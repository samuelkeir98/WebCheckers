package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * GameLobby class for keeping track of game being played
 *
 * @author Andy Gin
 */
public class GameLobby {

    /** Games being played */
    private Map<Player, Game> games = new HashMap<>();

    /**
     * Returns whether or not player is in a game
     * @param player player to check if in a game
     * @return true if player is in game, false otherwise
     */
    public boolean inGame(Player player) {
        return games.containsKey(player);
    }

    /**
     * Returns map of all ongoing games
     * @return ongoing games
     */
    public Map<Player, Game> getGames() {
        return games;
    }
}
