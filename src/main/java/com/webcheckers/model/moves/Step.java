package com.webcheckers.model.moves;

import com.webcheckers.model.Color;
import com.webcheckers.model.Piece;

/**
 * Step for moves
 * @author: Adrian Postolache
 * Tested by Sam Keir
 */
public class Step extends Move {

    /**
     * Constructor for a Step ove
     * @param piece piece to move
     * @param direction direction to move piece in
     * @param color color of piece to move
     */
    public Step(Piece piece, Direction direction, Color color){
        super(piece,direction,Type.STEP,color);
    }
}
