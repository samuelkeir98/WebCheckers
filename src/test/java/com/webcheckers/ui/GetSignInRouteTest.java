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
    private TemplateEngine engine;

    @BeforeEach
    public void setup() {

        //mocks
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);

        // component under test
        CuT = new GetSigninRoute(engine);
    }

    @Test
    /**
     * Test sign-in page rendering
     */
    public void signinRender() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        // perform action
        CuT.handle(request, response);
        // analyze results
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetSigninRoute.TITLE_PARAM, GetSigninRoute.PAGE_TITLE);
    }

}
