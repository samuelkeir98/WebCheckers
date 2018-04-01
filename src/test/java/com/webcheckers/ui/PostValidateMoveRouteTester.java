package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.moves.Move;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test suites for the PostValidateMove component
 * @author Andy Gin
 */
@Tag("UI-Tier")
public class PostValidateMoveRouteTester {

    private static final String PLAYER_NAME = "name";

    /** Component under test */
    private PostValidateMoveRoute CuT;

    //mock objects
    private Request request;
    private Session session;
    private Response response;
    private Gson gson;
    private GameLobby gameLobby;
    private Game game;
    private Move move;
    private Message message;

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
        message = mock(Message.class);

        //friendly
        Player player = player = new Player(PLAYER_NAME);
        gson = new Gson();

        when(request.session()).thenReturn(session);
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);
        when(gameLobby.getGame(player)).thenReturn(game);

        //setup test
        CuT = new PostValidateMoveRoute(gson, gameLobby);
    }

    /**
     * Tests case where invalid move made
     */
    @Test
    public void moveInvalid() {
        when(game.isValidMove(move)).thenReturn(false);

        //invoke test
        CuT.handle(request, response);
    }

    /**
     * Tests case where valid move made
     */
    @Test
    public void moveValid() {
        when(game.isValidMove(move)).thenReturn(true);

        //invoke test
        CuT.handle(request, response);
    }
}
