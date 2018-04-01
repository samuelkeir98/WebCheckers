package com.webcheckers.ui;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

/**
 * Unit test suite for the GetHome component.
 * @author Andy Gin
 */
@Tag("UI-tier")
public class GetHomeRouteTest {

    private static final String PLAYER_NAME = "name";

    /** The component-under-test (CuT) */
    private GetHomeRoute CuT;

    //friendly objects
    private Player player;

    //mock objects
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private GameLobby gameLobby;

    /**
     * Setup mock objects before each test
     */
    @BeforeEach
    public void setup() {
        //mocks
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        playerLobby = mock(PlayerLobby.class);
        gameLobby = mock(GameLobby.class);

        //friendlies
        player = new Player(PLAYER_NAME);

        //return certain params when functions called
        when(request.session()).thenReturn(session);

        //setup test
        CuT = new GetHomeRoute(engine, playerLobby, gameLobby);
    }

    /**
     * Player signed out
     */
    @Test
    public void signed_Out() {
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(null);

        //mock render for analysis
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        //invoke test
        CuT.handle(request, response);

        //Analyze the results:
        //model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //model contains all necessary View-Model data
        testHelper.assertViewModelAttribute("title", "Welcome!");
        testHelper.assertViewModelAttribute(GetHomeRoute.NUM_PLAYER_PARAM, playerLobby.numPlayers());

        //test view name
        testHelper.assertViewName(GetHomeRoute.TEMPLATE_NAME);
    }

    /**
     * Player signed in, num players is 0, player not in game
     */
    @Test
    public void noOtherPlayers() {
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);
        when(playerLobby.numPlayers()).thenReturn(1);
        when(gameLobby.inGame(player)).thenReturn(false);

        //mock render for analysis
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        //invoke test
        CuT.handle(request, response);

        //Analyze the results:
        //model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //model contains all necessary View-Model data
        testHelper.assertViewModelAttribute("title", "Welcome!");
        testHelper.assertViewModelAttribute(GetHomeRoute.USER_PARAM, player);
        testHelper.assertViewModelAttribute(GetHomeRoute.NUM_PLAYER_PARAM, playerLobby.numPlayers());

        //test view name
        testHelper.assertViewName(GetHomeRoute.TEMPLATE_NAME);
    }

    /**
     * Player signed in, num players is > 1, player not in game
     */
    @Test
    public void otherUsersPresent() {
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);
        when(playerLobby.numPlayers()).thenReturn(2);
        when(gameLobby.inGame(player)).thenReturn(false);

        //mock render for analysis
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        //invoke test
        CuT.handle(request, response);

        //Analyze the results:
        //model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //model contains all necessary View-Model data
        testHelper.assertViewModelAttribute("title", "Welcome!");
        testHelper.assertViewModelAttribute(GetHomeRoute.USER_PARAM, player);
        testHelper.assertViewModelAttribute(GetHomeRoute.NUM_PLAYER_PARAM, playerLobby.numPlayers());

        //test view name
        testHelper.assertViewName(GetHomeRoute.TEMPLATE_NAME);
    }

}
