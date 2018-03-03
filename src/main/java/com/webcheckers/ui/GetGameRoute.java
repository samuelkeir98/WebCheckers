package com.webcheckers.ui;

import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import com.webcheckers.model.Row;
import spark.*;

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
        Player currentPlayer = new Player("bob");
        vm.put("currentPlayer", currentPlayer);
        ViewMode view = ViewMode.WHITE;
        vm.put("viewMode", view);
        vm.put("redPlayer", currentPlayer);
        vm.put("whitePlayer", currentPlayer);
        vm.put("activeColor", Color.BLUE);
        vm.put("board", new Board(currentPlayer, currentPlayer));
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}