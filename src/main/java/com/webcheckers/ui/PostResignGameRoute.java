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

    public static final String RESIGN = "You have resigned.";
    public static final String RESIGN_FAIL = "Other player has resigned. Click \"my home\" to return home.";
    public static final String SPECTATING = "You are a spectator! Click \"my home\" to return home.";

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
        if(game == null) {
            if(gameLobby.isSpectating(player))
                return gson.toJson(new Message(SPECTATING, Message.Type.error));
            return gson.toJson(new Message(RESIGN_FAIL, Message.Type.error));
        }
        gameLobby.endGame(game);
        if(!gameLobby.inGame(player)) {
            request.session().attribute(GetGameRoute.RESULT, RESIGN);
            return gson.toJson(new Message("success", Message.Type.info));
        }
        return gson.toJson(new Message("Error", Message.Type.error));
    }
}
