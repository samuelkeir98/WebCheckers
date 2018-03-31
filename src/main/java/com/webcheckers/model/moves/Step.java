package com.webcheckers.model.moves;

import com.webcheckers.model.Color;
import com.webcheckers.model.Piece;

/**
 * Step for moves
 * @author: Adrian Postolache
 * Tested by Sam Keir
 */
public class Step extends Move {
    public Step(Piece piece, Direction direction, Color color){
        super(piece,direction,Type.STEP,color);
    }
}
