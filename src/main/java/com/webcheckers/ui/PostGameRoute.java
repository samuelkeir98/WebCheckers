package com.webcheckers.ui;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
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

    public static final String CURRENT_PLAYER_ATTR = "currentPlayer";
    public static final String VIEW_MODE_ATTR = "viewMode";
    public static final String RED_PLAYER_ATTR = "redPlayer";
    public static final String WHITE_PLAYER_ATTR = "whitePlayer";
    public static final String ACTIVE_COLOR_ATTR = "activeColor";
    public static final String BOARD_ATTR = "board";
    public static final String TEMPLATE_NAME = "game.ftl";
    public static final String OPPONENT_SPECTATING = "Player spectating a game!";
    public static final String OPPONENT_BUSY = "Player already in game!";

    private final TemplateEngine templateEngine;
    private final GameLobby gameLobby;
    private final PlayerLobby playerLobby;

    /**
     * Create the spark component for the
     * {@code POST /game} HTTP request.
     * @param templateEngine ftl engine
     * @param gameLobby keeps track of games
     */
    public PostGameRoute(TemplateEngine templateEngine, GameLobby gameLobby, PlayerLobby playerLobby) {
        this.gameLobby = gameLobby;
        this.playerLobby = playerLobby;
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
        Player player2 = playerLobby.getPlayer(opponent);

        if(!gameLobby.inGame(player2) && !gameLobby.isSpectating(player2)) {
            Game game = new Game(player1, player2);
            gameLobby.enterGame(player2, game);
            gameLobby.enterGame(player1, game);

            ViewMode view = ViewMode.PLAY;
            vm.put(CURRENT_PLAYER_ATTR, player1);
            vm.put(VIEW_MODE_ATTR, view);
            vm.put(RED_PLAYER_ATTR, player1);
            vm.put(WHITE_PLAYER_ATTR, player2);
            vm.put(ACTIVE_COLOR_ATTR, game.getCurPlayer() == player1 ? Color.RED : Color.WHITE);
            vm.put(BOARD_ATTR, new BoardView(game.getBoard(),Color.RED));
            return templateEngine.render(new ModelAndView(vm, TEMPLATE_NAME));
        }

        else {
            if(gameLobby.inGame(player2))
                request.session().attribute("message", OPPONENT_BUSY);
            else
                request.session().attribute("message", OPPONENT_SPECTATING);
            response.redirect(WebServer.HOME_URL);
            return null;
        }
    }
}
