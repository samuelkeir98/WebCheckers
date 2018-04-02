package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.Objects;

import static spark.Spark.*;


public class PostBackupMoveRoute implements Route {
    // fields
    private Gson gson;
    private GameLobby gameLobby;

    /**
     * constructor
     * @param gson gson builder
     * @param gameLobby GameLobby application component
     */
    public PostBackupMoveRoute(Gson gson, GameLobby gameLobby) {
        Objects.requireNonNull(gson, "gson must not be null");
        Objects.requireNonNull(gameLobby, "gameLobby must not be null");
        this.gson = gson;
        this.gameLobby = gameLobby;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) {
        Session httpSession = request.session();
        Player player = httpSession.attribute(PostSigninRoute.PLAYER_KEY);

        if (player == null)
            return gson.toJson(new Message("Not signed in", Message.Type.error));

        if (!gameLobby.inGame(player))
            return gson.toJson(new Message("Not in a game", Message.Type.error));

        Game game = gameLobby.getGame(player);

        return game.backUpMove() ?
                gson.toJson(new Message("success", Message.Type.info))
                : gson.toJson(new Message("no moves to undo", Message.Type.error));

    }

}