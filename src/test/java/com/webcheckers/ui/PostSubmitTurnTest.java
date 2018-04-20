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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

/**
 * Unit test suites for the PostSubmitTurn component
 * @author Andy Gin
 */
@Tag("UI-tier")
public class PostSubmitTurnTest {

    private static final String PLAYER_NAME = "name";

    /** Component under test */
    private PostSubmitTurnRoute CuT;

    //friendly
    private Player player;

    //mock
    private Request request;
    private Session session;
    private Response response;
    private GameLobby gameLobby;
    private Game game;
    private Gson gson;

    /**
     * Setup up before each test
     */
    @BeforeEach
    public void setup() {
        //mocks
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        gameLobby = mock(GameLobby.class);
        game = mock(Game.class);
        gson = mock(Gson.class);

        //friendly
        player = new Player(PLAYER_NAME);

        when(request.session()).thenReturn(session);
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);

        //setup test
        CuT = new PostSubmitTurnRoute(gson, gameLobby);
    }

    /**
     * Tests case where turn is submitted
     */
    @Test
    public void turnComplete() {
        when(gameLobby.getGame(player)).thenReturn(game);
        when(game.isTurnOver()).thenReturn(true);

        //invoke test
        CuT.handle(request, response);

        verify(gson).toJson(any(Message.class));
    }

    /**
     * Tests case where turn cannot be submitted
     */
    @Test
    public void turnIncomplete() {
        when(gameLobby.getGame(player)).thenReturn(game);
        //must jump
        when(game.isTurnOver()).thenReturn(false);

        //invoke test
        CuT.handle(request, response);

        verify(gson).toJson(any(Message.class));
    }

    /**
     * Tests Game is over
     */
    @Test
    public void gameOver() {
        when(gameLobby.getGame(player)).thenReturn(null);

        //invoke test
        CuT.handle(request, response);

        verify(gson).toJson(any(Message.class));
    }

}
