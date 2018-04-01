package com.webcheckers.model.moves;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Tag("Model-tier")
public class MoveUndoTest {
    // component under test
    private MoveUndo CuT;

    // mock objects
    private Board board;

    // friendly object
    private Move move =
            new Move(new Position(1, 1), new Position(2, 2));

    @BeforeEach
    public void setup() {
        // set up mock board object
        board = mock(Board.class);
    }

    @Test
    public void paramsNonNullTest() {
        assertThrows(NullPointerException.class,
                () -> new MoveUndo(null, board));
        assertThrows(NullPointerException.class,
                () -> new MoveUndo(move, null));
    }

    @Test
    public void undoMoveTest1() {
        Move undo = new Move(move.getEnd(), move.getStart());

        // invoke test
        CuT = new MoveUndo(move, board);
        CuT.execute();

        // verify method calls
        verify(board, times(1)).makeMove(undo);

    }
}
