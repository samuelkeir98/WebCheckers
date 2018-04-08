package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * UI Controller for resigning a game
 */
public class PostResignGameRoute implements Route {

    /** Used to parse JSON objects */
    private final Gson gson;
    private final GameLobby gameLobby;

    public PostResignGameRoute(Gson gson, GameLobby gameLobby) {
        this.gson = gson;
        this.gameLobby = gameLobby;
    }

    /**
     *
     * @return
     */
    public Object handle(Request request, Response response) {
        Player player = request.session().attribute(GetHomeRoute.PLAYER_KEY);
        Game game = gameLobby.getGame(player);
        gameLobby.endGame(game);
        if(!gameLobby.inGame(player)) {
            return gson.toJson(new Message("success", Message.Type.info));
        }
        return gson.toJson(new Message("fail", Message.Type.error));
    }
}
