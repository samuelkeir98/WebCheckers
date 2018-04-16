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
     * Gets game player is in
     * @param player player to look for in game
     * @return game that player is in
     */
    public Game getGame(Player player) {
        return games.get(player);
    }

    /**
     * Places player into game
     * @param player player to put into game
     * @param game game to put player in
     */
    public void enterGame(Player player, Game game) {
        games.put(player, game);
    }

    /**
     * Removes player from game
     * @param game game to end
     */
    public void endGame(Game game) {
        games.remove(game.getRedPlayer());
        games.remove(game.getWhitePlayer());
    }

    public void leaveGame(Player player) {
        games.remove(player);
    }
}
