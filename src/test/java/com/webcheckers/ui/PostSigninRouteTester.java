package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * unit test for UI route POST /signin
 * @author Anthony Massicci
 */

@Tag("UI-tier")
public class PostSigninRouteTester {
    // component under test
    private PostSigninRoute CuT;

    // mock objects
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);

        playerLobby = mock(PlayerLobby.class);

        CuT = new PostSigninRoute(templateEngine, playerLobby);
    }

    @Test
    public void successful_login_test() {
        final String USERNAME = "bob";
        final Player playerObj = new Player(USERNAME);

        when(request.queryParams(eq("name"))).thenReturn(USERNAME);
        when(playerLobby.signin(USERNAME)).thenReturn(playerObj);

        assertThrows(HaltException.class, () -> CuT.handle(request, response));

        verify(session, times(1)).attribute(PostSigninRoute.PLAYER_KEY, playerObj);
        verify(response, times(1)).redirect(WebServer.HOME_URL);

    }

    @Test
    public void already_logged_in_test() {
        final String USERNAME = "bob";

        when(request.queryParams(eq("name"))).thenReturn(USERNAME);
        when(playerLobby.signin(USERNAME)).thenReturn(null);

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewName(GetSigninRoute.TEMPLATE_NAME);
        testHelper.assertViewModelAttribute(GetSigninRoute.MESSAGE_PARAM, "User name is already taken!");

    }

    @Test
    public void invalid_username_test() {
        final String USERNAME = "+Bob+";

        when(request.queryParams("name")).thenReturn(USERNAME);

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewName(GetSigninRoute.TEMPLATE_NAME);
        testHelper.assertViewModelAttribute(GetSigninRoute.MESSAGE_PARAM,
                "Names can only have alphanumeric characters or spaces");

    }


}
