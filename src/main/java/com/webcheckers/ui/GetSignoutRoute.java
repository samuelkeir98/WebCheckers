package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;

/**
 * UI Controller for GET /signout route
 *
 * @author Andy Gin
 */
public class GetSignoutRoute implements Route {
    private final PlayerLobby playerLobby;

    /**
     * Create the spark component for the
     * {@code GET /signout} HTTP request.
     * @param playerLobby tracks all signed in players
     */
    public GetSignoutRoute(PlayerLobby playerLobby) {
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");

        this.playerLobby = playerLobby;
    }

    /**
     * Redirect to home after signing out
     * @param request HTTP request object
     * @param response HTTP response object
     * @return null
     */
    @Override
    public Object handle(Request request, Response response) {
        Player player = request.session().attribute(PostSigninRoute.PLAYER_KEY);
        playerLobby.signout(player);
        request.session().removeAttribute(PostSigninRoute.PLAYER_KEY);
        response.redirect(WebServer.HOME_URL);
        return null;
    }
}
