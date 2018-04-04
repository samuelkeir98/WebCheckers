package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.TemplateEngine;

import static org.mockito.Mockito.mock;

/**
 * Unit test suites for WebServer
 */
@Tag("UI-tier")
public class WebServerTest {

    /** Component under test */
    private WebServer CuT;
    private Gson gson;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private GameLobby gameLobby;

    @BeforeEach
    /**
     * Setups test
     */
    public void setup() {
        //mock
        engine = mock(TemplateEngine.class);
        playerLobby = mock(PlayerLobby.class);
        gameLobby = mock(GameLobby.class);

        //friendly
        gson = new Gson();

        CuT = new WebServer(engine, gson, playerLobby, gameLobby);
    }

    /**
     * Invokes test
     */
    @Test
    public void test() {
        CuT.initialize();
    }
}
