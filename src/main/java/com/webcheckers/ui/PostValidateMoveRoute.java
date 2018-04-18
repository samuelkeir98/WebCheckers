package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.moves.Move;
import spark.*;

import java.util.logging.Logger;

/**
 * UI controller for the POST validateMove route
 */
public class PostValidateMoveRoute implements Route {

	public static final String INVALID_MOVE = "Invalid Move";
	public static final String VALID_MOVE = "Move made";
	public static final String GAME_OVER = "Opponent Resigned. Submit turn to end";

	private static final Logger LOG = Logger.getLogger(PostValidateMoveRoute.class.getName());

	private final Gson gson;
	private final GameLobby gameLobby;

	/**
	 * Create the spark component for the HTTP request
	 * @param gson parses JSON objects
	 * @param gameLobby keeps track of ongoing games
	 */
	public PostValidateMoveRoute(Gson gson, GameLobby gameLobby) {
		this.gson = gson;
		this.gameLobby = gameLobby;

		LOG.config("PostValidateMoveRoute is initialized.");
	}

	/**
	 * Checks if move is valid
	 * @param request HTTP request object
	 * @param response HTTP response object
	 * @return Success message if valid move, Error message otherwise
	 */
	@Override
	public Object handle(Request request, Response response) {
		Move move = gson.fromJson(request.body(), Move.class);
		Player player = request.session().attribute(GetHomeRoute.PLAYER_KEY);
		Game game = gameLobby.getGame(player);

		if(game == null) {
			return gson.toJson(new Message(GAME_OVER, Message.Type.info));
		}

		else if(game.isValidMove(move)) {
			game.makeMove(move);
			return gson.toJson(new Message(VALID_MOVE, Message.Type.info));
		}

		return gson.toJson(new Message(INVALID_MOVE, Message.Type.error));
	}
}
