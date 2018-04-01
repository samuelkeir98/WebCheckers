package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test suites for the GetSignout component
 */
@Tag("UI-tier")
public class GetSignoutRouteTest {
    private static final String PLAYER_NAME = "name";

    /** Component under test */
    private GetSignoutRoute CuT;

    //mock objects
    private Request request;
    private Session session;
    private Response response;
    private PlayerLobby playerLobby;

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
        playerLobby = mock(PlayerLobby.class);

        //friendly
        player = new Player(PLAYER_NAME);

        when(request.session()).thenReturn(session);

        //setup test
        CuT = new GetSignoutRoute(playerLobby);
    }

    /**
     * Tests case where user signs out
     */
    @Test
    public void signedOut() {
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);

        //invoke test
        CuT.handle(request, response);
    }

    /**
     * Tests case where user already signed out
     */
    @Test
    public void alreadySignedOut() throws NullPointerException {
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(null);

        //invoke test
        CuT.handle(request, response);
    }
}
