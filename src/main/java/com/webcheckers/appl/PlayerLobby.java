package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Player Lobby Service class
 * @author Anthony Massicci
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

    /**
     * returns a list of all currently signed in players
     * @return list of player names
     */
    public List<String> getAllPlayerNames() {
        return players.stream()
                .map(player -> player.getName())
                .collect(Collectors.toList());
    }

    /**
     * returns number of players signed in
     * @return # of players
     */
    public synchronized int numPlayers() {
        return players.size();
    }

}
