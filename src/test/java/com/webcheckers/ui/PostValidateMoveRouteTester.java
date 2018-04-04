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

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

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

    //friendly
    private Gson gson;
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
        gson = new Gson();

        //friendly
        player = new Player(PLAYER_NAME);

        when(request.session()).thenReturn(session);
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);
        when(gameLobby.getGame(player)).thenReturn(game);
        //when(gson.toJson(any(Message.class))).thenAnswer(new GsonAnswer());

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
        String type = (String) CuT.handle(request, response);

        //assertNotNull(type);
        //assertEquals(PostValidateMoveRoute.INVALID_MOVE, type);
    }

    /**
     * Tests case where valid move made
     */
    @Test
    public void moveValid() {
        when(game.isValidMove(move)).thenReturn(true);

        //invoke test
        String type = (String) CuT.handle(request, response);

        //assertNotNull(type);
        //assertEquals(PostValidateMoveRoute.INVALID_MOVE, type);
    }

    /*
     * help for gson mocking
     */
    private class GsonAnswer implements Answer<String> {

        @Override
        public String answer(InvocationOnMock invocationOnMock) {
            Message msg = invocationOnMock.getArgument(0);
            return msg.getType().toString();
        }
    }
}
