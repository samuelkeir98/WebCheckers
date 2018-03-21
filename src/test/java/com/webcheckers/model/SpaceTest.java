package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.model.moves.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Tag("Model-tier")
public class SpaceTest {
    // component under test
    private Space CuT;

    // friendly objects
    private Piece testPiece = new Piece(new Position(0, 0),
            Color.RED);

    /**
     * tests whether white spaces are automatically invalid
     */
    @Test
    public void white_space_invalid_test() {
        CuT = new Space(0, false);
        assertFalse(CuT.isValid());
    }

    /**
     * tests whether occupied spaces return as invalid
     */
    @Test
    public void occupied_space_invalid_test() {
        CuT = new Space(0, true);

        CuT.setPiece(testPiece);
        assertFalse(CuT.isValid());
    }

    /**
     * tests the placement of pieces
     */
    @Test
    public void place_piece_test() {
        CuT = new Space(0, true);
        CuT.setPiece(testPiece);
        assertSame(CuT.getPiece(), testPiece);
    }

    /**
     * tests the removal of a piece
     */
    @Test
    public void remove_piece_test() {
        CuT = new Space(0, true);
        CuT.setPiece(testPiece);

        CuT.removePiece();
        assertNull(CuT.getPiece());
    }

}
