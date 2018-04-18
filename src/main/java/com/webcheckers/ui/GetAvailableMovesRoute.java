package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.moves.Move;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;
import java.util.Set;

public class GetAvailableMovesRoute implements Route {
    private final Gson gson;
    private final GameLobby gameLobby;

    public GetAvailableMovesRoute(Gson gson, GameLobby gameLobby) {
        Objects.requireNonNull(gson);
        Objects.requireNonNull(gameLobby);

        this.gson = gson;
        this.gameLobby = gameLobby;
    }

    @Override
    public Object handle(Request request, Response response) {
        Session session = request.session();
        Player currentPlayer = session.attribute(PostSigninRoute.PLAYER_KEY);

        if (currentPlayer == null) {
            return gson.toJson(new Message("not logged in", Message.Type.error));
        }

        if (gameLobby.inGame(currentPlayer)) {
            Game game = gameLobby.getGame(currentPlayer);
            Set<Move> availableMoves = game.availableMoves();
            return gson.toJson(new Message(gson.toJson(availableMoves), Message.Type.info));
        } else {
            return gson.toJson(new Message("not in game", Message.Type.error));
        }

    }
}
