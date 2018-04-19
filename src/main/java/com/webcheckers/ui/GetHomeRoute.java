package com.webcheckers.ui;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.GameView;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */

public class GetHomeRoute implements Route {

  public static final String NUM_PLAYER_PARAM = "numPlayers";
  public static final String USER_PARAM = "currentPlayer";
  public static final String OTHER_PLAYERS_PARAM = "otherPlayers";
  public static final String PLAYER_KEY = "player";
  public static final String TEMPLATE_NAME = "home.ftl";
  public static final String OTHER_GAMES_PARAM = "games";
  public static final String PLAYER_IN_GAME = "message";
  public static final String GAME_URL = "/game";


  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private final TemplateEngine templateEngine;
  private final PlayerLobby playerLobby;
  private final GameLobby gameLobby;

  /**
   * Create the Spark Route (UI controller) for the
   * {@code GET /} HTTP request.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby, final GameLobby gameLobby) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    Objects.requireNonNull(playerLobby, "playerLobby must not be null");
    //
    this.templateEngine = templateEngine;
    this.playerLobby = playerLobby;
    this.gameLobby = gameLobby;
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    final Session session = request.session();
    LOG.finer("GetHomeRoute is invoked.");
    //
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");
    vm.put(NUM_PLAYER_PARAM, playerLobby.numPlayers());

    if (session.attribute(PLAYER_KEY) != null) {
      Player player = session.attribute(PLAYER_KEY);
      vm.put(USER_PARAM, player);

      if(playerLobby.numPlayers() > 1) {
        List<Player> otherPlayers = playerLobby.getPlayers()
                .stream()
                .filter(p -> !p.equals(player))
                .collect(Collectors.toList());
        vm.put(OTHER_PLAYERS_PARAM, otherPlayers);
      }

      if(gameLobby.hasGames()){
        Collection<GameView> games = gameLobby.getGames();
        vm.put(OTHER_GAMES_PARAM,games);
      }

      if(gameLobby.inGame(player)) {
        response.redirect(GAME_URL);
      }

      if(gameLobby.isSpectating(player)){
        gameLobby.removeSpectator(player);
      }

      String message = session.attribute("message");
      vm.put("message", message);

      String message = session.attribute(PLAYER_IN_GAME);
      vm.put(PLAYER_IN_GAME, message);

      String result = session.attribute(GetGameRoute.RESULT);
      vm.put(GetGameRoute.RESULT, result);

    }

    return templateEngine.render(new ModelAndView(vm , TEMPLATE_NAME));
  }
}