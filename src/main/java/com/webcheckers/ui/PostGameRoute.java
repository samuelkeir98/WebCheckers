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

/**
 * UI Controller for POST /game
 *
 * @author Andy Gin
 */
public class PostGameRoute implements Route {

    private final TemplateEngine templateEngine;
    private final GameLobby gameLobby;

    /**
     * Create the spark component for the
     * {@code POST /game} HTTP request.
     * @param templateEngine ftl engine
     * @param gameLobby keeps track of games
     */
    public PostGameRoute(TemplateEngine templateEngine, GameLobby gameLobby) {
        this.gameLobby = gameLobby;
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
    }

    /**
     * Renders game page if opponent not in game
     * @param request HTTP request object
     * @param response HTTP response object
     * @return rendered game page
     */
    @Override
    public Object handle(Request request, Response response) {
        final String opponent = request.queryParams("name");
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game");
        Player player1 = request.session().attribute(PostSigninRoute.PLAYER_KEY);
        Player player2 = new Player(opponent);

        if(!gameLobby.inGame(player2)) {
            Game game = new Game(player1, player2);
            gameLobby.getGames().put(player2, game);
            gameLobby.getGames().put(player1, game);

            vm.put("currentPlayer", player1);
            ViewMode view = ViewMode.PLAY;
            vm.put("viewMode", view);
            vm.put("redPlayer", player1);
            vm.put("whitePlayer", player2);
            vm.put("activeColor", game.getTurn());
            vm.put("board", new BoardView(gameLobby.getGames().get(player2).getBoard(), Color.RED));
            return templateEngine.render(new ModelAndView(vm, "game.ftl"));
        }

        else {
            request.session().attribute("message", "Player already in game!");
            response.redirect("/");
            return null;
        }
    }
}
