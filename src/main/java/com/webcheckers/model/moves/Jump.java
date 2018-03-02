package com.webcheckers.model.moves;

import com.webcheckers.model.Piece;

public class Jump extends Move {

    private Piece jumped;

    public Jump(Piece piece, Direction direction,Piece taken){
        super(piece,direction,Type.JUMP);
        jumped = taken;
    }

    public Piece getJumped() {
        return jumped;
    }
}
