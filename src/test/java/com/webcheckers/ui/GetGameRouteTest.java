package com.webcheckers.ui;

import com.webcheckers.appl.BoardView;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit test suite for the GetGameRoute component.
 * @author Andy Gin
 */
@Tag("UI-tier")
public class GetGameRouteTest {

    private static final String PLAYER_NAME = "name";
    private static final String PLAYER1 = "player1";
    private static final String PLAYER2 = "player2";

    /** The component-under-test (CuT) */
    private GetGameRoute CuT;

    //friendly objects
    private Player player;
    private Player player1;
    private Player player2;
    private Board board;

    //mock objects
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private GameLobby gameLobby;
    private Game game;
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

        //friendlies
        player = new Player(PLAYER_NAME);
        player1 = new Player(PLAYER1);
        player2 = new Player(PLAYER2);
        board = new Board();
        color = Color.RED;

        //return certain params when functions called
        when(request.session()).thenReturn(session);
        when(session.attribute(PostSigninRoute.PLAYER_KEY)).thenReturn(player);
        when(game.getBoard(player)).thenReturn(board);

        //setup test
        CuT = new GetGameRoute(engine, gameLobby);
    }

    /**
     * Test player is in game
     */
    @Test
    public void game_entered() {
        when(gameLobby.getGame(eq(player))).thenReturn(game);
        when(gameLobby.inGame(eq(player))).thenReturn(true);
        when(game.getRedPlayer()).thenReturn(player1);
        when(game.getWhitePlayer()).thenReturn(player);
        when(game.isGameOver()).thenReturn(false);

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
        testHelper.assertViewModelAttribute(PostGameRoute.CURRENT_PLAYER_ATTR, player);
        testHelper.assertViewModelAttribute(PostGameRoute.VIEW_MODE_ATTR, ViewMode.PLAY);
        testHelper.assertViewModelAttribute(PostGameRoute.RED_PLAYER_ATTR, player1);
        testHelper.assertViewModelAttribute(PostGameRoute.WHITE_PLAYER_ATTR, player);
        testHelper.assertViewModelAttribute(PostGameRoute.ACTIVE_COLOR_ATTR, color);

        testHelper.assertViewModelAttribute(PostGameRoute.BOARD_ATTR, new BoardView(board, Color.WHITE));
        //test view name
        testHelper.assertViewName(PostGameRoute.TEMPLATE_NAME);
    }

    /**
     * Test that the "challenge" action fails to enter game (player1 in game)
     */
    @Test
    public void game_not_entered_1() {
        //test scenario: player1 in game
        when(gameLobby.inGame(eq(player))).thenReturn(false);

        //invoke test
        CuT.handle(request, response);

        //response redirected
        verify(response, times(1)).redirect(WebServer.HOME_URL);
    }

    /**
     * Test that the player has signed out
     */
    @Test
    public void game_not_entered_2() throws NullPointerException {
        //test scenario: player1 logged out
        when(gameLobby.inGame(eq(player))).thenThrow(NullPointerException.class);

    }

    /**
     * Tests game is won
     */
    @Test
    public void game_won() {
        when(gameLobby.getGame(eq(player))).thenReturn(game);
        when(gameLobby.inGame(eq(player))).thenReturn(true);
        when(gameLobby.isSpectating(eq(player))).thenReturn(false);
        when(game.getRedPlayer()).thenReturn(player1);
        when(game.getWhitePlayer()).thenReturn(player);
        when(game.isGameOver()).thenReturn(true);
        when(gameLobby.inGame(eq(player1))).thenReturn(true);
        when(gameLobby.inGame(eq(player))).thenReturn(true);

        CuT.handle(request, response);

        verify(session).attribute(GetGameRoute.RESULT, GetGameRoute.WIN);
        verify(gameLobby).leaveGame(player);
        verify(response).redirect(WebServer.HOME_URL);
    }

    /**
     * Tests game is lost
     */
    @Test
    public void game_lost() {
        when(gameLobby.getGame(eq(player))).thenReturn(game);
        when(gameLobby.inGame(eq(player))).thenReturn(true);
        when(gameLobby.isSpectating(eq(player))).thenReturn(false);
        when(game.getRedPlayer()).thenReturn(player1);
        when(game.getWhitePlayer()).thenReturn(player);
        when(game.isGameOver()).thenReturn(true);
        when(gameLobby.inGame(eq(player1))).thenReturn(false);

        CuT.handle(request, response);

        verify(session).attribute(GetGameRoute.RESULT, GetGameRoute.LOST);
        verify(gameLobby).leaveGame(player);
        verify(response).redirect(WebServer.HOME_URL);
    }

    /**
     * Spectator leaves game is over normally (not resigned)
     */
    @Test
    public void spectator_leave() {
        when(gameLobby.getGame(eq(player))).thenReturn(game);
        when(gameLobby.inGame(eq(player))).thenReturn(false);
        when(gameLobby.isSpectating(eq(player))).thenReturn(true);
        when(game.getRedPlayer()).thenReturn(player1);
        when(game.getWhitePlayer()).thenReturn(player);
        when(game.isGameOver()).thenReturn(true);
        when(gameLobby.inGame(eq(player1))).thenReturn(true);
        when(gameLobby.inGame(eq(player))).thenReturn(true);
        when(game.getWinner()).thenReturn(player1);

        CuT.handle(request, response);

        verify(session).attribute(GetGameRoute.RESULT, player1 + " Won!");
        verify(response).redirect(WebServer.HOME_URL);
    }

    /**
     * Spectator leaves player resigns
     */
    @Test
    public void spectator_leave_after_resign() {
        when(gameLobby.inGame(eq(player))).thenReturn(false);
        when(gameLobby.getSpectateGame(eq(player))).thenReturn(game);
        when(gameLobby.isSpectating(eq(player))).thenReturn(true);
        when(game.getRedPlayer()).thenReturn(player1);
        when(game.getWhitePlayer()).thenReturn(player2);
        when(game.isGameOver()).thenReturn(true);
        when(gameLobby.inGame(eq(player1))).thenReturn(false);
        when(gameLobby.inGame(eq(player2))).thenReturn(false);

        CuT.handle(request, response);

        verify(session).attribute(GetGameRoute.RESULT, GetGameRoute.PLAYER_RESIGNED);
        verify(response).redirect(WebServer.HOME_URL);
    }

}
