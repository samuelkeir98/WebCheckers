package com.webcheckers.ui;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.logging.Logger;

public class PostSpectateGameRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private final GameLobby gameLobby;

    public PostSpectateGameRoute(GameLobby gameLobby){
        this.gameLobby = gameLobby;
        LOG.config("GetSpectateGameRoute is initialized.");

    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Player player = request.session().attribute(PostSigninRoute.PLAYER_KEY);
        final Game game =

        return null;
    }
}
