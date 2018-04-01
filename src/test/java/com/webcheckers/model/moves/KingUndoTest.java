package com.webcheckers.model.moves;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Board;
import com.webcheckers.model.Color;
import com.webcheckers.model.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Tag("Model-tier")
public class KingUndoTest {
    // component under test
    private KingUndo CuT;

    // mock objects
    private Board board;

    // friendly objects
    private Move move =
            new Move(new Position(1, 1), new Position(3, 3));
    private Piece piece = new Piece(new Position(2, 2), Color.RED);

    @BeforeEach
    public void setup() {
        // set up mock board object
        board = mock(Board.class);
    }

    @Test
    public void paramsNonNullTest() {
        assertThrows(NullPointerException.class,
                () -> new KingUndo(null, board, null));
        assertThrows(NullPointerException.class,
                () -> new KingUndo(move, null, null));
        assertThrows(NullPointerException.class,
                () -> new KingUndo(move, null, piece));
    }

    @Test
    public void undoKingTest1() {
        Move undo = new Move(move.getEnd(), move.getStart());

        // invoke test
        CuT = new KingUndo(move, board, piece);
        CuT.execute();

        // verify method calls
        verify(board, times(1)).makeMove(undo);

    }
}
