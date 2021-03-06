package com.webcheckers.ui;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Color;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

/**
 * Unit test suite for the PostGameRoute component.
 * @author Andy Gin
 */
@Tag("UI-tier")
public class PostGameRouteTest {

    private static final String PLAYER_NAME = "name";
    private static final String OPPONENT = "opponent";

    /** The component-under-test (CuT) */
    private PostGameRoute CuT;

    //friendly objects
    private Player player;
    private Player opponent;
    private Board board;

    //mock objects
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private GameLobby gameLobby;
    private Game game;
    private PlayerLobby playerLobby;
    private Color color;

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
        gameLobby = mock(GameLobby.class);
        game = mock(Game.class);
        playerLobby = mock(PlayerLobby.class);


        //friendlies
        player = new Player(PLAYER_NAME);
        opponent = new Player(OPPONENT);
        board = new Board();
        color = Color.RED;

        //return certain params when functions called
        when(request.session()).thenReturn(session);
        when(session.attribute(PostSigninRoute.PLAYER_KEY)).thenReturn(player);
        when(request.queryParams("name")).thenReturn(OPPONENT);
        when(playerLobby.getPlayer(OPPONENT)).thenReturn(opponent);

        //setup test
        CuT = new PostGameRoute(engine, gameLobby, playerLobby);
    }

    /**
     * Test that the "challenge" action enters game
     */
    @Test
    public void game_entered() {
        //test scenario: opponent not in other game
        when(gameLobby.inGame(eq(opponent))).thenReturn(false);

        //mock render for analysis
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        when(gameLobby.getGame(opponent)).thenReturn(game);
        when(game.getBoard(player)).thenReturn(board);

        //invoke test
        CuT.handle(request, response);

        //Analyze the results:
        //model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //model contains all necessary View-Model data
        testHelper.assertViewModelAttribute(PostGameRoute.CURRENT_PLAYER_ATTR, player);
        testHelper.assertViewModelAttribute(PostGameRoute.VIEW_MODE_ATTR, ViewMode.PLAY);
        testHelper.assertViewModelAttribute(PostGameRoute.RED_PLAYER_ATTR, player);
        testHelper.assertViewModelAttribute(PostGameRoute.WHITE_PLAYER_ATTR, opponent);
        testHelper.assertViewModelAttribute(PostGameRoute.ACTIVE_COLOR_ATTR, color);
        
        //test view name
        testHelper.assertViewName(PostGameRoute.TEMPLATE_NAME);
    }

    /**
     * Test that the "challenge" action fails to enter game (opponent in game)
     */
    @Test
    public void game_not_entered_1() {
        //test scenario: opponent in game
        when(gameLobby.inGame(eq(opponent))).thenReturn(true);

        //invoke test
        CuT.handle(request, response);

        //response redirected
    }

    /**
     * Test that the "challenge" action fails to enter game (opponent in game)
     */
    @Test
    public void game_not_entered_2() throws NullPointerException {
        //test scenario: opponent logged out
        when(gameLobby.inGame(eq(opponent))).thenThrow(NullPointerException.class);

        //response redirected
    }

}
