package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;

public class GameLobby {
    private Map<Player, Game> games = new HashMap<>();

    public boolean inGame(Player player) {
        return games.containsKey(player);
    }

    public Map<Player, Game> getGames() {
        return games;
    }
}
