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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test suites for the PostCheckTurn component
 * @author Andy Gin
 */
@Tag("UI-tier")
public class PostCheckTurnRouteTest {
    private static final String PLAYER_NAME = "name";
    private static final String OPPONENT_NAME = "opponent";

    /** Component under test */
    private PostCheckTurnRoute CuT;

    //mock objects
    private Request request;
    private Session session;
    private Response response;
    private GameLobby gameLobby;
    private Game game;
    private Gson gson;

    //friendly
    private Player player;
    private Player opponent;

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
        gson = mock(Gson.class);

        //friendly
        player = new Player(PLAYER_NAME);
        opponent = new Player(OPPONENT_NAME);

        when(request.session()).thenReturn(session);
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);

        //setup test
        CuT = new PostCheckTurnRoute(gson, gameLobby);
    }

    /**
     * Tests case where not user's turn
     */
    @Test
    public void isNotTurn() {
        when(gameLobby.getGame(player)).thenReturn(game);
        when(game.getCurPlayer()).thenReturn(opponent);

        //invoke test
        CuT.handle(request, response);

        verify(gson).toJson(any(Message.class));
    }

    /**
     * Tests case where it is user's turn
     */
    @Test
    public void isTurn() {
        when(gameLobby.getGame(player)).thenReturn(game);
        when(game.getCurPlayer()).thenReturn(player);

        //invoke test
        CuT.handle(request, response);

        verify(gson).toJson(any(Message.class));
    }

    /**
     * Tests game is already over
     */
    @Test
    public void gameOver() {
        when(gameLobby.getGame(player)).thenReturn(null);

        //invoke test
        CuT.handle(request, response);

        verify(gson).toJson(any(Message.class));
    }
}
