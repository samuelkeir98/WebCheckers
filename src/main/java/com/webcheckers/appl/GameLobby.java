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
    private Map<Player, Game> players = new HashMap<>();

    private Map<Game, Player> games = new HashMap<>();

    /**
     * Returns whether or not player is in a game
     * @param player player to check if in a game
     * @return true if player is in game, false otherwise
     */
    public boolean inGame(Player player) {
        return players.containsKey(player);
    }

    /**
     *
     * @param player
     * @return
     */
    public Game getGame(Player player) {
        return players.get(player);
    }

    public void enterGame(Player player, Game game) {
        players.put(player, game);
    }
}
