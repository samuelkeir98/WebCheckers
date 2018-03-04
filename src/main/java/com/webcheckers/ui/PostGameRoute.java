package com.webcheckers.ui;

import com.webcheckers.appl.BoardView;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Color;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostGameRoute implements Route {

    private final TemplateEngine templateEngine;
    private final PlayerLobby lobby;
    private final GameLobby gameLobby;

    public PostGameRoute(TemplateEngine templateEngine, PlayerLobby lobby, GameLobby gameLobby) {
        this.gameLobby = gameLobby;
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
        Game game = new Game(player1, player2);
        gameLobby.getGames().put(player2, game);

        vm.put("currentPlayer", player1);
        ViewMode view = ViewMode.PLAY;
        vm.put("viewMode", view);
        vm.put("redPlayer", player1);
        vm.put("whitePlayer", player2);
        vm.put("activeColor", Color.RED);
        vm.put("board", new BoardView(game.getBoard(),Color.RED));
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
