package com.webcheckers.ui;

import spark.*;

import com.webcheckers.model.Board;
import com.webcheckers.model.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 *
 * @author Andy Gin
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private final TemplateEngine templateEngine;

    public GetGameRoute(TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;

        LOG.config("GetGameRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetGameRoute is invoked");

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game");
        Player player1 = new Player("Bob");
        Player player2 = new Player("Billy");
        vm.put("currentPlayer", player1);
        ViewMode view = ViewMode.PLAY;
        vm.put("viewMode", view);
        vm.put("redPlayer", player1);
        vm.put("whitePlayer", player2);
        vm.put("activeColor", Color.ORANGE);
        vm.put("board", new Board(player1, player2));
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
