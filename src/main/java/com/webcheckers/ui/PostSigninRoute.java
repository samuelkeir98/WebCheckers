package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * UI controller for route for POST /signin
 */
public class PostSigninRoute implements Route {

    public final static String PLAYERLOBBY_KEY = "playerLobby";

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
        final String name = request.queryParams("name");
        final Session session = request.session();
        final Map<String, Object> vm = new HashMap<>();

        Player player = playerLobby.signin(name);
        if (player != null) {
            session.attribute(PLAYERLOBBY_KEY, player);
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        } else {
            vm.put(GetSigninRoute.TITLE_PARAM, GetSigninRoute.PAGE_TITLE);
            vm.put(GetSigninRoute.MESSAGE_PARAM, "User name is already taken!");
            return templateEngine.render(new ModelAndView(vm, GetSigninRoute.TEMPLATE_NAME));
        }
    }
}
