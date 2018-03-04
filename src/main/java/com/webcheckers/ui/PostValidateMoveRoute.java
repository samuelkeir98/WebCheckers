package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.moves.Move;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

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
		final String requestBody = request.body();
		final Move move = gson.fromJson(requestBody,Move.class);
		System.out.println(requestBody);

		System.out.println(move);
		return null;


	}
}
