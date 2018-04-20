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
public class JumpUndoTest {
    // component under test
    private JumpUndo CuT;

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
                () -> new JumpUndo(null, board, null));
        assertThrows(NullPointerException.class,
                () -> new JumpUndo(move, null, null));
        assertThrows(NullPointerException.class,
                () -> new JumpUndo(move, null, piece));
    }

    @Test
    public void undoJumpTest1() {
        Move undo = new Move(move.getEnd(), move.getStart());

        // invoke test
        CuT = new JumpUndo(move, board, piece);
        CuT.execute();

        // verify method calls
        verify(board, times(1)).makeMove(undo);
        //verify(board, times(1)).placePiece(piece, piece.getPosition());

    }
}
