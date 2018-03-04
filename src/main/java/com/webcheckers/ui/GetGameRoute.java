package com.webcheckers.ui;

import com.webcheckers.model.Color;
import spark.*;

import com.webcheckers.model.Board;
import com.webcheckers.model.Player;

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

    static final String BOARD_ATTRIBUTE_KEY = "BOARD";

    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private final TemplateEngine templateEngine;

    public GetGameRoute(TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;

        LOG.config("GetGameRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        final Session httpSession = request.session();

        LOG.finer("GetGameRoute is invoked");

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Game");


        if(httpSession.attribute(BOARD_ATTRIBUTE_KEY) == null) {
            Player player1 = new Player("Bob");
            Player player2 = new Player("Billy");
            vm.put("currentPlayer", player1);
            ViewMode view = ViewMode.PLAY;
            vm.put("viewMode", view);
            vm.put("redPlayer", player1);
            vm.put("whitePlayer", player2);
            Board board = new Board(player1,player2);
            httpSession.attribute(BOARD_ATTRIBUTE_KEY,board);
            vm.put("board", board);
            vm.put("activeColor", board.whoseTurn());

        }else{
            Board board = httpSession.attribute(BOARD_ATTRIBUTE_KEY);
            vm.put("currentPlayer",board.getPlayer(board.whoseTurn()));
            vm.put("viewMode",ViewMode.PLAY);
            vm.put("redPlayer", board.getPlayer(Color.RED));
            vm.put("whitePlayer", board.getPlayer(Color.WHITE));
            vm.put("board",board);
            vm.put("activeColor", board.whoseTurn());
        }

        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}