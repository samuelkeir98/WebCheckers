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
 * @author Anthony Massicci
 * Tested by Samuel Keir
 */
public class PostSigninRoute implements Route {

    public final static String PLAYER_KEY = "player";

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    /**
     * Constructor
     * @param templateEngine template engine to render pages
     */
    public PostSigninRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
        Objects.requireNonNull(templateEngine);
        Objects.requireNonNull(playerLobby);

        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }

    /**
     * Implementation of handle
     * @param request HTTP request
     * @param response HTTP response object
     * @return rendered HTML page
     */
    @Override
    public Object handle(Request request, Response response) {
        final String name = request.queryParams("name");
        final Session session = request.session();
        final Map<String, Object> vm = new HashMap<>();

        vm.put(GetSigninRoute.TITLE_PARAM, GetSigninRoute.PAGE_TITLE);

        if (validateName(name)) {
            Player player = playerLobby.signin(name);
            if (player != null) {
                session.attribute(PLAYER_KEY, player);
                response.redirect(WebServer.HOME_URL);
                halt();
                return null;
            } else {
                return templateEngine.render(error(vm, "User name is already taken!"));
            }
        } else {
            return templateEngine.render(
                    error(vm, "Names can only have alphanumeric characters or spaces")
            );
        }
    }

    /**
     * Error message production
     * @param vm the map
     * @param message error message
     * @return
     */
    private ModelAndView error(Map<String, Object> vm, String message) {
        vm.put(GetSigninRoute.MESSAGE_PARAM, message);
        return new ModelAndView(vm, GetSigninRoute.TEMPLATE_NAME);
    }

    /**
     * Validates the given name
     * @param name the given name
     * @return
     */
    private boolean validateName(String name) {
        return name.matches("[0-9a-zA-Z\\s]+");
    }
}
