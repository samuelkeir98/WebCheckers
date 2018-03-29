package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Board;
import com.webcheckers.model.Color;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.moves.Move;
import spark.*;

import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route {
	private static final Logger LOG = Logger.getLogger(PostValidateMoveRoute.class.getName());


	private final Gson gson;
	private final GameLobby gameLobby;

	public PostValidateMoveRoute(Gson gson, GameLobby gameLobby) {
		this.gson = gson;
		this.gameLobby = gameLobby;

		LOG.config("PostValidateMoveRoute is initialized.");
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		Move move = gson.fromJson(request.body(), Move.class);
		Player player = request.session().attribute(GetHomeRoute.PLAYER_KEY);
		Game game = gameLobby.getGame(player);
		game.generateMoves();
		/**if(!game.isValidMove(move)){
			return gson.toJson(new Message("Invalid Move", Message.Type.error));
		}*/
		game.storeMove(move);
		return gson.toJson(new Message("Move made", Message.Type.info));
	}
}
