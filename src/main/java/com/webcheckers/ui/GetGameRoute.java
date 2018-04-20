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

    static final String TITLE_ATTR = "title";
    static final String TITLE = "Game";
    static final String RESULT = "result";
    static final String LOST = "You lost!";
    static final String WIN = "You Won!";
    public static final String OPPONENT_RESIGNED = "Opponent Resigned.";
    public static final String PLAYER_RESIGNED = "Player resigned!";
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
        LOG.finer("GetGameRoute is invoked");
        Session httpSession = request.session();
        Player player = httpSession.attribute(PostSigninRoute.PLAYER_KEY);
        final Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR, TITLE);

        if(gameLobby.inGame(player) || gameLobby.isSpectating(player)) {
            Game game = (gameLobby.inGame(player) ? gameLobby.getGame(player):gameLobby.getSpectateGame(player));
            Player player1 = game.getRedPlayer();
            Player player2 = game.getWhitePlayer();

            if(game.isGameOver()) {
                if(gameLobby.inGame(player1) && gameLobby.inGame(player2)) {
                    if(gameLobby.isSpectating(player)) {
                        httpSession.attribute(RESULT, game.getWinner() + " Won!");
                    } else {
                        httpSession.attribute(RESULT, WIN);
                        gameLobby.leaveGame(player);
                    }
                }
                else if(gameLobby.inGame(player)){
                    httpSession.attribute(RESULT, LOST);
                    gameLobby.leaveGame(player);
                }
                else {
                    httpSession.attribute(RESULT, PLAYER_RESIGNED);
                }

                response.redirect(WebServer.HOME_URL);
                return null;
            }

            ViewMode view = ViewMode.PLAY;
            vm.put(PostGameRoute.CURRENT_PLAYER_ATTR, player);
            vm.put(PostGameRoute.VIEW_MODE_ATTR, view);
            vm.put(PostGameRoute.RED_PLAYER_ATTR, player1);
            vm.put(PostGameRoute.WHITE_PLAYER_ATTR, player2);
            vm.put(PostGameRoute.ACTIVE_COLOR_ATTR, game.getCurPlayer() == player2 ? Color.WHITE : Color.RED);
            vm.put(PostGameRoute.BOARD_ATTR, new BoardView(game.getBoard(player),((player == game.getRedPlayer() || gameLobby.isSpectating(player)) ? Color.RED : Color.WHITE)));
            return templateEngine.render(new ModelAndView(vm, PostGameRoute.TEMPLATE_NAME));
        }

        else {
            httpSession.attribute(RESULT, OPPONENT_RESIGNED);
            response.redirect(WebServer.HOME_URL);
            return null;
        }
    }
}
