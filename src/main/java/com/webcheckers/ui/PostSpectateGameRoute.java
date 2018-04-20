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
    public Object handle(Request request, Response response) {
        final Player player = request.session().attribute(PostSigninRoute.PLAYER_KEY);
        final String redPlayer = request.queryParams("name");

        final Game game = gameLobby.getGame(redPlayer);

        if(game!=null){
            if(gameLobby.isSpectating(player)){
                gameLobby.removeSpectator(player);
            }
            gameLobby.spectateGame(player,game);
            response.redirect(WebServer.GAME_URL);
        }else{
            response.redirect(WebServer.HOME_URL);
        }


        return null;
    }
}
