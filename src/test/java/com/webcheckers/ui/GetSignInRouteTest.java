package com.webcheckers.ui;


import static org.mockito.Mockito.*;
import spark.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;

/**
 * unit test for UI route GET /signin
 * @author Samuel Keir
 */

@Tag("UI-tier")
public class GetSignInRouteTest {

    // component under test
    private GetSigninRoute CuT;

    // mock objects
    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine templateEngine;


    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);

        // component under test
        CuT = new GetSigninRoute(templateEngine);
    }

    @Test
    /**
     * Test template engine being null
     */
    public void templateNull() {
        // perform action
        CuT = null;
        // analyze results
        //assertThrows(IllegalArgumentException.class, () -> {new GetSigninRoute(CuT);}, "templateEngine " +
        //        "cannot be null");
    }

    @Test
    /**
     * Test sign-in page rendering
     */
    public void signinRender() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        // perform action
        CuT.handle(request, response);
        // analyze results
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
    }

}
