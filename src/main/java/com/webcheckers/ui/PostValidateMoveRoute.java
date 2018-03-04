package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Board;
import com.webcheckers.model.Color;
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

	public PostValidateMoveRoute(TemplateEngine templateEngine, Gson gson) {
		Objects.requireNonNull(templateEngine, "templateEngine must not be null");
		this.templateEngine = templateEngine;
		this.gson = gson;

		LOG.config("GetGameRoute is initialized.");
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {

		final Session httpSession = request.session();
		final String requestBody = request.body();
		final Move move = gson.fromJson(requestBody,Move.class);
		Board board = httpSession.attribute(GetGameRoute.BOARD_ATTRIBUTE_KEY);
		board.makeMove(move);
		Map<String, Object> vm = new HashMap<>();
		vm.put("title", "Game");
		vm.put("currentPlayer",board.getPlayer(board.whoseTurn()));
		vm.put("viewMode",ViewMode.PLAY);
		vm.put("redPlayer", board.getPlayer(Color.RED));
		vm.put("whitePlayer", board.getPlayer(Color.WHITE));
		vm.put("board",board);
		vm.put("activeColor", board.whoseTurn());

		return templateEngine.render(new ModelAndView(vm, "game.ftl"));


	}
}
