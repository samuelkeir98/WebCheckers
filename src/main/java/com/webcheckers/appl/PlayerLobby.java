package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.*;

/**
 * Player Lobby Service class
 * @author Anthony Massicci
 * Tested by Samuel Keir
 */
public class PlayerLobby {
    private Set<Player> players = new HashSet<>();

    /**
     * Signs a player into the web checkers app
     * @param playerName
     * @return a new Player object if specified
     * player name is available, null otherwise.
     */
    public synchronized Player signin(String playerName) {
        Player newPlayer = new Player(playerName);

        if (players.contains(newPlayer))
            return null;

        players.add(newPlayer);
        return newPlayer;
    }

    public synchronized void signout(Player player) {
        players.remove(player);
    }

    /**
     * Returns a list of all currently signed in players
     * @return list of player names
     */
    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
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
        Iterator<Player> iterator = players.iterator();
        while(iterator.hasNext()) {
            Player player = iterator.next();
            if(player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

}