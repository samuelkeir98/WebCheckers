package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import spark.*;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

/**
 * Tests for GetAvailableMovesRoute
 * @author Anthony Massicci
 */
@Tag("UI-tier")
public class GetAvailableMovesRouteTest {

    // CuT
    private GetAvailableMovesRoute CuT;

    // mock objects
    private Session session;
    private Request request;
    private Response response;
    private GameLobby gameLobby;
    private Game game;


    // friendlies
    private Gson gson = new Gson();
    private Player testPlayer = new Player("testPlayer");

    @BeforeEach
    public void setup() {
        // set up mocks
        session = mock(Session.class);
        request = mock(Request.class);
        response = mock(Response.class);
        gameLobby = mock(GameLobby.class);
        game = mock(Game.class);

        // initialize CuT
        CuT = new GetAvailableMovesRoute(gson, gameLobby);

        when(request.session()).thenReturn(session);
    }

    @Test
    public void notSignedInTest() {
        when(session.attribute(PostSigninRoute.PLAYER_KEY)).thenReturn(null);

        // invoke CuT
        String json = (String)CuT.handle(request, response);

        // assertions
        assertNotNull(json);
        assertTrue(json.contains("error"));
    }

    @Test
    public void notInGameTest() {
        when(session.attribute(PostSigninRoute.PLAYER_KEY)).thenReturn(testPlayer);
        when(gameLobby.inGame(testPlayer)).thenReturn(false);

        // invoke CuT
        String json = (String) CuT.handle(request, response);

        // verify behavior
        verify(gameLobby, times(1)).inGame(eq(testPlayer));

        // assertions
        assertTrue(json.contains("error"));
    }

    @Test
    public void movesAvailableTest() {
        when(session.attribute(PostSigninRoute.PLAYER_KEY)).thenReturn(testPlayer);
        when(gameLobby.inGame(testPlayer)).thenReturn(true);
        when(gameLobby.getGame(testPlayer)).thenReturn(game);

        // invoke CuT
        String json = (String) CuT.handle(request, response);

        // verify behavior
        verify(gameLobby, times(1)).inGame(eq(testPlayer));
        verify(gameLobby, times(1)).getGame(eq(testPlayer));
        verify(game, times(1)).availableMoves();

        // assertions
        assertTrue(json.contains("info"));
    }

}
