package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.*;

/**
 * Player Lobby Service class
 * @author Anthony Massicci
 * Tested by Samuel Keir
 */
public class PlayerLobby {
    private Map<String, Player> players = new HashMap<>();

    /**
     * Signs a player into the web checkers app
     * @param playerName
     * @return a new Player object if specified
     * player name is available, null otherwise.
     */
    public synchronized Player signin(String playerName) {
        Player newPlayer = new Player(playerName);

        if(players.containsKey(newPlayer))
            return null;

        players.put(playerName, newPlayer);
        return newPlayer;
    }

    public synchronized void signout(Player player) {
        players.remove(player.getName());
    }

    /**
     * Returns a list of all currently signed in players
     * @return list of player names
     */
    public Collection<Player> getPlayers() {
        return players.values();
        //return Collections.unmodifiableSet(players);
    }

    /**
     * Returns number of players signed in
     * @return # of players
     */
    public synchronized int numPlayers() {
        return players.size();
    }

    /**
     * Gets player from player lobby
     * @param name name of player to get
     * @return player that was being searched
     */
    public Player getPlayer(String name) {
        return players.get(name);
    }

}