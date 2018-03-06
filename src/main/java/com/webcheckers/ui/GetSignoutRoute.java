package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;

public class GetSignoutRoute implements Route {
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    public GetSignoutRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");

        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }

    @Override
    public Object handle(Request request, Response response) {
        Player player = request.session().attribute(PostSigninRoute.PLAYER_KEY);
        playerLobby.signout(player);
        request.session().removeAttribute(PostSigninRoute.PLAYER_KEY);
        response.redirect("/");
        return null;
    }
}
