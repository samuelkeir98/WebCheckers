package com.webcheckers.model.moves;

import com.webcheckers.model.Color;
import com.webcheckers.model.Piece;

public class Step extends Move {
    public Step(Piece piece, Direction direction, Color color){
        super(piece,direction,Type.STEP,color);
    }
}
