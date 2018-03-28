package com.webcheckers.model.moves;

import com.webcheckers.model.Color;
import com.webcheckers.model.Piece;

/**
 * Representation of a Jump move. Keeps track of the piece being jumped for easy undo functionality
 * @author: Adrian Postolache axp3806@rit.edu
 * Tested by Sam Keir
 */
public class Jump extends Move {

    /**
     * The piece that's getting jumped
     */
    private Piece jumped;

    /**
     * Creates a move that represents a jump
     * @param piece Piece that's moving
     * @param direction The raw direction it's going in
     * @param color The color of the piece that's moving
     * @param taken The piece being jumped over and killed
     */
    public Jump(Piece piece, Direction direction, Color color, Piece taken){
        super(piece,direction,Type.JUMP,color);
        jumped = taken;
    }

    /**
     * Gets the piece that being jumped
     * @return The jumped piece
     */
    public Piece getJumped() {
        return jumped;
    }
}
