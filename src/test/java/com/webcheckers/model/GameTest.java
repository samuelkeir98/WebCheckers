package com.webcheckers.model;

import com.webcheckers.model.moves.Move;
import com.webcheckers.model.moves.MoveAction;
import com.webcheckers.model.moves.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Stack;

@Tag("Model-tier")
public class GameTest {

    // mocks
    @Mock private Board board;
    @Mock private Board turnBoard;
    @Mock private Stack<MoveAction> lastPlayed;
    @Mock private List<Move> movesMade;


    // Component under test
    @InjectMocks
    private Game CuT;


    // friendly objects
    Player player1 = new Player("Jim");
    Player player2 = new Player("Bob");
    Move testMove = new Move(new Position(1, 1), new Position(2, 2));
    MoveAction action = new MoveAction();

    @BeforeEach
    public void setup() {
        CuT = new Game(player1, player2);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void gameOverTest() {
        when(board.isGameOver()).thenReturn(true);
        assertTrue(CuT.isGameOver());
    }

    @Test
    public void makeMoveTest() {
        when(turnBoard.getMove(testMove)).thenReturn(testMove);
        when(turnBoard.makeMove(testMove)).thenReturn(action);

        // invoke test
        CuT.makeMove(testMove);
        verify(turnBoard, times(1)).makeMove(testMove);
        verify(lastPlayed, times(1)).push(action);
        verify(movesMade, times(1)).add(testMove);
    }

    @Test
    public void submitTurnTest() {

    }
}