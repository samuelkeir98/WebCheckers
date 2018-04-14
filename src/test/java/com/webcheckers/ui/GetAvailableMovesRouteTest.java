package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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


    // friendlies
    private Gson gson = new Gson();

}
