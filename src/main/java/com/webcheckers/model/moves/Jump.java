package com.webcheckers.model.moves;

import com.webcheckers.model.Color;
import com.webcheckers.model.Piece;

/**
 * Representation of a Jump move. Keeps track of the piece being jumped for easy undo functionality
 * @author: Adrian Postolache axp3806@rit.edu
 */
public class Jump extends Move {

    /**
     * The piece that's getting jumped
     */
    private Piece jumped;

    /**
     * Creates a move that represents a jump
     * @param piece Piece thats moving
     * @param direction the raw direction it's going in
     * @param color the color of the piece that's moving
     * @param taken The piece being jumped over and killed
     */
    public Jump(Piece piece, Direction direction, Color color, Piece taken){
        super(piece,direction,Type.JUMP,color);
        jumped = taken;
    }

    /**
     * Gets the piece that being jumped
     * @return the jumped piece
     */
    public Piece getJumped() {
        return jumped;
    }
}
