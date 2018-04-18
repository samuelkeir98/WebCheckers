package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test suites for PostResignGame UI component
 *
 * @author Andy Gin
 */
@Tag("UI-tier")
public class PostResignGameTest {

    private static final String PLAYER_NAME = "name";

    private PostResignGameRoute CuT;

    //Mocks
    private GameLobby gameLobby;
    private Game game;
    private Request request;
    private Session session;
    private Response response;

    // Friendlies
    private Gson gson;
    private Player player;

    /**
     * Setup before each test
     */
    @BeforeEach
    public void setup() {
        //friendly
        gson = new Gson();
        player = new Player(PLAYER_NAME);

        //mock
        gameLobby = mock(GameLobby.class);
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        game = mock(Game.class);

        when(request.session()).thenReturn(session);
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);

        CuT = new PostResignGameRoute(gson, gameLobby);
    }

    /**
     * Tests case where other user already resigned
     */
    @Test
    public void otherResigned() {
        when(gameLobby.getGame(player)).thenReturn(null);

        CuT.handle(request, response);
    }

    /**
     * Tests case where resign is successful
     */
    @Test
    public void resign_success() {
        when(gameLobby.getGame(player)).thenReturn(game);
        assertNotNull(game);

        when(gameLobby.inGame(player)).thenReturn(false);

        CuT.handle(request, response);
    }

    /**
     * Tests case where resign is not successful
     */
    @Test
    public void resign_fail() {
        when(gameLobby.getGame(player)).thenReturn(game);
        assertNotNull(game);

        when(gameLobby.inGame(player)).thenReturn(true);

        CuT.handle(request, response);
    }
}
