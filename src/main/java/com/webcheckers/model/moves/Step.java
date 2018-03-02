package com.webcheckers.model.moves;

import com.webcheckers.model.Piece;

public class Step extends Move {
    public Step(Piece piece, Direction direction){
        super(piece,direction,Type.STEP);
    }
}
