package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import java.util.logging.Logger;

/**
 * UI Controller for POST check turn route
 */
public class PostCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());

    /** Used to parse JSON objects */
    private final Gson gson;
    private final GameLobby gameLobby;

    /**
     * Create spark component for HTTP request
     * @param gson used to parse JSON objects
     * @param gameLobby lobby of games
     */
    public PostCheckTurnRoute(Gson gson, GameLobby gameLobby) {
        this.gson = gson;
        this.gameLobby = gameLobby;

        LOG.config("PostCheckTurnRoute is initialized.");
    }

    /**
     * Checks if it is player's turn yet
     * @param request HTTP request object
     * @param response HTTP response object
     * @return true if it is player's turn, false otherwise
     */
    @Override
    public Object handle(Request request, Response response) {
        Player player = request.session().attribute(GetHomeRoute.PLAYER_KEY);
        Game game = gameLobby.getGame(player);

        if(game!=null) {
            if (game.getCurPlayer().equals(player)) {
                return gson.toJson(new Message("true", Message.Type.info));
            } else {
                return gson.toJson(new Message("false", Message.Type.info));
            }


        if(game == null || game.getCurPlayer().equals(player)){
            return gson.toJson(new Message("true", Message.Type.info));

        }else{
            return gson.toJson(new Message("true", Message.Type.info));
        }
    }
}
