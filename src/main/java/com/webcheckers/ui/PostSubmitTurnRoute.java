package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * UI Controller for the POST submitTurn route
 */
public class PostSubmitTurnRoute implements Route {

    public static final String MUST_MOVE = "Must make move";

    private static final Logger LOG = Logger.getLogger(PostSubmitTurnRoute.class.getName());

    private final Gson gson;
    private final GameLobby gameLobby;

    /**
     * Create the spark component for the HTTP request
     * @param gson parses JSON objects
     * @param gameLobby keeps track of games
     */
    public PostSubmitTurnRoute(Gson gson, GameLobby gameLobby) {
        this.gson = gson;
        this.gameLobby = gameLobby;

        LOG.config("PostSubmitTurnRoute is initialized.");
    }

    /**
     * Handles submitting a turn
     * @param request HTTP request object
     * @param response HTTP response object
     * @return Message telling game turn is over
     */
    @Override
    public Object handle(Request request, Response response) {
        Player player = request.session().attribute(GetHomeRoute.PLAYER_KEY);
        Game game = gameLobby.getGame(player);
        if(game == null) {
            return gson.toJson(new Message(PostValidateMoveRoute.GAME_OVER, Message.Type.info));
        }
        else if(game.isTurnOver()) {
            game.submitTurn();
            return gson.toJson(new Message(PostValidateMoveRoute.VALID_MOVE, Message.Type.info));
        }
        return gson.toJson(new Message(MUST_MOVE, Message.Type.error));
    }
}
