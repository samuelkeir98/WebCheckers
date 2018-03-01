package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;

/**
 * UI controller for route for POST /signin
 */
public class PostSigninRoute implements Route {

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    /**
     * constructor
     * @param templateEngine template engine to render pages
     */
    public PostSigninRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
        Objects.requireNonNull(templateEngine);
        Objects.requireNonNull(playerLobby);

        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }

    /**
     * implementation of handle
     * @param request HTTP request
     * @param response HTTP response object
     * @return rendered HTML page
     */
    @Override
    public Object handle(Request request, Response response) {
        return null;
    }
}
