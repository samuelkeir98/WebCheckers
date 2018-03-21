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

public class PostSubmitTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostValidateMoveRoute.class.getName());


    private final TemplateEngine templateEngine;
    private final Gson gson;
    private final GameLobby gameLobby;

    public PostSubmitTurnRoute(TemplateEngine templateEngine, Gson gson, GameLobby gameLobby) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
        this.gson = gson;
        this.gameLobby = gameLobby;

        LOG.config("PostSubmitTurnRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Player player = request.session().attribute(GetHomeRoute.PLAYER_KEY); //REFACTOR TO CONSTANT
        Game game = gameLobby.getGames().get(player);
        game.submitTurn();
        return gson.toJson(new Message("Move made", Message.Type.info));
    }
}
