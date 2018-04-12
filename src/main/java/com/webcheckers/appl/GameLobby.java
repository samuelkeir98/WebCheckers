package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.Collection;
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

    /**Games being spectated*/
    private Map<Player, Game> spectators = new HashMap<>();

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
     * Checks if player is spectating a game
     * @param player potential spectator
     * @return whether the player is spectating
     */
    public boolean isSpectating(Player player){return spectators.containsKey(player);}

    /**
     * Gets the game a player is spectating
     * @param player spectator to get from
     * @return the game that they're spectating
     */
    public Game getSpectateGame(Player player){return spectators.get(player);}

    /**
     * makes the player spectate the passed game
     * @param player the spectator
     * @param game the game to be spectated
     */
    public void spectateGame(Player player, Game game){spectators.put(player,game);}

    /**
     * makes it so the player is no longer spectating their game
     * @param player the spectator
     */
    public void removeSpectator(Player player){spectators.remove(player);}

    /**
     * gets All the current games so they can be displayed on the home page for spectating
     * @return collection of all games' game views
     */
    public Collection<GameView> getGames(){
        ArrayList<GameView> allGames = new ArrayList<>();
        games.values().forEach(e->allGames.add(new GameView(e)));
        return allGames;
    }
}
