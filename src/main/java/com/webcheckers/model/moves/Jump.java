package com.webcheckers.model.moves;

import com.webcheckers.model.Color;
import com.webcheckers.model.Piece;

public class Jump extends Move {

    private Piece jumped;

    public Jump(Piece piece, Direction direction, Color color, Piece taken){
        super(piece,direction,Type.JUMP,color);
        jumped = taken;
    }

    public Piece getJumped() {
        return jumped;
    }
}
