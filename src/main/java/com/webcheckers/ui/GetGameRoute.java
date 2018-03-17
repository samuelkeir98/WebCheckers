package com.webcheckers.ui;

import com.webcheckers.appl.BoardView;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.model.Color;
import com.webcheckers.model.Game;
import spark.*;

import com.webcheckers.model.Board;
import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * UI Controller to GET game page
 *
 * @author Andy Gin
 */
public class GetGameRoute implements Route {

    static final String BOARD_ATTRIBUTE_KEY = "BOARD";
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final GameLobby gameLobby;

    /**
     * Create the spark component for the
     * {@code GET /game} HTTP request.
     * @param templateEngine ftl template engine
     * @param gameLobby keeps track of all games
     */
    public GetGameRoute(TemplateEngine templateEngine, GameLobby gameLobby) {
        this.gameLobby = gameLobby;
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;

        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * Render the game page
     * @param request HTTP request object
     * @param response HTTP response object
     * @return rendered game page
     */
    @Override
    public Object handle(Request request, Response response) {

        final Session httpSession = request.session();

        LOG.finer("GetGameRoute is invoked");
        Player player = request.session().attribute(PostSigninRoute.PLAYER_KEY);
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game");

        if(gameLobby.inGame(player)) {
            Player player1 = gameLobby.getGames().get(player).getRedPlayer();
            Player player2 = gameLobby.getGames().get(player).getWhitePlayer();
            Game game = gameLobby.getGames().get(player);

            vm.put("currentPlayer", player);
            ViewMode view = ViewMode.PLAY;
            vm.put("viewMode", view);
            vm.put("redPlayer", player1);
            vm.put("whitePlayer", player2);
            vm.put("activeColor", game.getTurn());
            vm.put("board", new BoardView(game.getBoard(),(player == game.getRedPlayer() ? Color.RED : Color.WHITE)));
            return templateEngine.render(new ModelAndView(vm, "game.ftl"));
        }

        else {
            response.redirect("/");
            return null;
        }
    }
}
