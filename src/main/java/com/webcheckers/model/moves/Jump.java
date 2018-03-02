package com.webcheckers.model.moves;

import com.webcheckers.model.Piece;

public class Jump extends Move {

    private int jumpSpotRow,jumpSpotCol;

    public Jump(Piece piece, Direction direction){
        super(piece,direction,Type.JUMP);
        //TODO Implement Piece then handle jump spots
    }

    public int getJumpSpotCol() {
        return jumpSpotCol;
    }

    public int getJumpSpotRow() {
        return jumpSpotRow;
    }
}
