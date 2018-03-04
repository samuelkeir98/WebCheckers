package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import spark.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostGameRoute implements Route {

    private final TemplateEngine templateEngine;
    private final PlayerLobby lobby;

    public PostGameRoute(TemplateEngine templateEngine, PlayerLobby lobby) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
        this.lobby = lobby;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final String opponent = request.queryParams("name");
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game");
        Player player1 = request.session().attribute(PostSigninRoute.PLAYER_KEY);
        Player player2 = new Player(opponent);
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
