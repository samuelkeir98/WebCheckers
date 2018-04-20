package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.moves.Move;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.Mockito.*;

/**
 * Unit test suites for the PostValidateMove component
 * @author Andy Gin
 */
@Tag("UI-tier")
public class PostValidateMoveRouteTester {

    private static final String PLAYER_NAME = "name";

    /** Component under test */
    private PostValidateMoveRoute CuT;

    //mock objects
    private Request request;
    private Session session;
    private Response response;
    private GameLobby gameLobby;
    private Game game;
    private Move move;
    private Gson gson;

    //friendly
    private Player player;

    /**
     * Setup objects before each test
     */
    @BeforeEach
    public void setup() {

        //mocks
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        gameLobby = mock(GameLobby.class);
        game = mock(Game.class);
        move = mock(Move.class);
        gson = mock(Gson.class);

        //friendly
        player = new Player(PLAYER_NAME);

        when(request.session()).thenReturn(session);
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);

        //setup test
        CuT = new PostValidateMoveRoute(gson, gameLobby);
    }

    /**
     * Tests case where invalid move made
     */
    @Test
    public void moveInvalid() {
        when(gameLobby.getGame(player)).thenReturn(game);
        when(game.isValidMove(move)).thenReturn(false);

        CuT.handle(request, response);

        verify(gson).toJson(any(Message.class));
    }

    /**
     * Tests case where valid move made
     */
    @Test
    public void moveValid() {
        when(gson.fromJson(request.body(), Move.class)).thenReturn(move);
        when(gameLobby.getGame(player)).thenReturn(game);
        when(game.isValidMove(move)).thenReturn(true);

        //invoke test
        CuT.handle(request, response);

        verify(game).makeMove(move);
        verify(gson).toJson(any(Message.class));
    }

    /**
     * Tests game is over
     */
    @Test
    public void game_over() {
        when(gameLobby.getGame(player)).thenReturn(null);

        CuT.handle(request, response);

        verify(gson).toJson(any(Message.class));
    }
}
