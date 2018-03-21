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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostValidateMoveRoute implements Route {
	private static final Logger LOG = Logger.getLogger(PostValidateMoveRoute.class.getName());


	private final TemplateEngine templateEngine;
	private final Gson gson;
	private final GameLobby gameLobby;

	public PostValidateMoveRoute(TemplateEngine templateEngine, Gson gson, GameLobby gameLobby) {
		Objects.requireNonNull(templateEngine, "templateEngine must not be null");
		this.templateEngine = templateEngine;
		this.gson = gson;
		this.gameLobby = gameLobby;

		LOG.config("PostValidateMoveRoute is initialized.");
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		Move move = gson.fromJson(request.body(),Move.class);
		Player player = request.session().attribute(GetHomeRoute.PLAYER_KEY); //REFACTOR TO CONSTANT
		Game game = gameLobby.getGames().get(player);
		if(!game.isValidMove(player,move)){
			return gson.toJson(new Message("Invalid Move", Message.Type.error));
		}
		game.makeMove(player,move);
		return gson.toJson(new Message("Move made", Message.Type.info));
	}
}
