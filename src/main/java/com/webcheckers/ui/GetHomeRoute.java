package com.webcheckers.ui;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
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

      List<Player> otherPlayers = playerLobby.getPlayers()
              .stream()
              .filter(p -> !p.equals(player))
              .collect(Collectors.toList());

      vm.put(OTHER_PLAYERS_PARAM, otherPlayers);

      System.out.println(gameLobby.getGames());
      System.out.println(gameLobby.inGame(player));
      if(gameLobby.inGame(player)) {
        response.redirect("/game");
      }

    }

    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}