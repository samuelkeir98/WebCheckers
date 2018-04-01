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

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import spark.*;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@Tag("UI-tier")
public class PostBackupMoveRouteTest {
    // component under test
    private PostBackupMoveRoute CuT;

    // mock objects
    private Gson gson;
    private Game game;
    private GameLobby gameLobby;
    private Request request;
    private Response response;
    private Session session;

    // friendly objects
    private Player testPlayer = new Player("bob");

    @BeforeEach
    public void setup() {
        gson = mock(Gson.class);
        request = mock(Request.class);
        response = mock(Response.class);
        game = mock(Game.class);
        gameLobby = mock(GameLobby.class);
        session = mock(Session.class);

        when(request.session()).thenReturn(session);
        when(gson.toJson(any(Message.class))).thenAnswer(new GsonAnswer());

        CuT = new PostBackupMoveRoute(gson, gameLobby);
    }

    @Test
    public void exceptionOnParamNull() {
        assertThrows(NullPointerException.class,
                () -> new PostBackupMoveRoute(null, gameLobby));
        assertThrows(NullPointerException.class,
                () -> new PostBackupMoveRoute(gson, null));

        CuT = new PostBackupMoveRoute(gson, gameLobby);
    }

    @Test
    public void errorOnEmptyStack() {
        when(game.backUpMove()).thenReturn(false);
        when(session.attribute(eq(PostSigninRoute.PLAYER_KEY))).thenReturn(testPlayer);
        when(gameLobby.inGame(testPlayer)).thenReturn(true);
        when(gameLobby.getGame(testPlayer)).thenReturn(game);


        // invoke CuT
        String type = (String) CuT.handle(request, response);

        assertNotNull(type);
        assertEquals("error", type);

    }

    @Test
    public void successOnNonEmptyStack() {
        when(game.backUpMove()).thenReturn(true);
        when(session.attribute(eq(PostSigninRoute.PLAYER_KEY))).thenReturn(testPlayer);
        when(gameLobby.inGame(testPlayer)).thenReturn(true);
        when(gameLobby.getGame(testPlayer)).thenReturn(game);

        // invoke CuT
        String type = (String) CuT.handle(request, response);

        assertNotNull(type);
        assertEquals("info", type);
    }

    @Test
    public void notSignedIn() {
        when(session.attribute(eq(PostSigninRoute.PLAYER_KEY))).thenReturn(null);

        // invoke CuT
        String type = (String) CuT.handle(request, response);

        assertNotNull(type);
        assertEquals("error", type);
    }

    @Test
    public void notInGame() {
        when(session.attribute(eq(PostSigninRoute.PLAYER_KEY))).thenReturn(testPlayer);
        when(gameLobby.inGame(testPlayer)).thenReturn(false);


        // invoke CuT
        String type = (String) CuT.handle(request, response);

        assertNotNull(type);
        assertEquals("error", type);
    }

    /*
     * Answer class to help with gson mocking
     */
    private class GsonAnswer implements Answer<String> {

        @Override
        public String answer(InvocationOnMock invocationOnMock) throws Throwable {
            Message msg = invocationOnMock.getArgument(0);
            return msg.getType().toString();
        }
    }
}
