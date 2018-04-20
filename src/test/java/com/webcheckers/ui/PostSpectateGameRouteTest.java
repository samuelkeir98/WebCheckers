package com.webcheckers.ui;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit Test suites for PostSpectateGame
 *
 * @author Andy Gin
 */
@Tag("UI-tier")
public class PostSpectateGameRouteTest {

    private PostSpectateGameRoute CuT;

    //mock
    private GameLobby gameLobby;
    private Request request;
    private Response response;
    private Session session;
    private Game game;

    //friendly
    private Player player;
    private String red;

    /**
     * Setup tests
     */
    @BeforeEach
    public void setup() {
        //mock
        gameLobby = mock(GameLobby.class);
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        game = mock(Game.class);

        //friendly
        player = new Player("name");
        red = "red";

        CuT = new PostSpectateGameRoute(gameLobby);

        when(request.session()).thenReturn(session);
        when(session.attribute(eq(PostSigninRoute.PLAYER_KEY))).thenReturn(player);
        when(request.queryParams("name")).thenReturn(red);
    }

    /**
     * Successfully enter spectate
     */
    @Test
    public void spectate_success() {
        when(gameLobby.getGame(red)).thenReturn(game);
        when(gameLobby.isSpectating(player)).thenReturn(false);

        CuT.handle(request, response);

        verify(gameLobby).spectateGame(player,game);
        verify(response).redirect(WebServer.GAME_URL);
    }

    /**
     * Tests case where player already spectating
     */
    @Test
    public void already_spectating() {
        when(gameLobby.getGame(red)).thenReturn(game);
        when(gameLobby.isSpectating(player)).thenReturn(true);

        CuT.handle(request, response);

        verify(gameLobby).removeSpectator(player);
        verify(gameLobby).spectateGame(player,game);
        verify(response).redirect(WebServer.GAME_URL);
    }

    /**
     * Tests when game does not exist
     */
    @Test
    public void game_already_over() {
        when(gameLobby.getGame(red)).thenReturn(null);

        CuT.handle(request, response);

        verify(response).redirect(WebServer.HOME_URL);
    }
}
