package com.webcheckers.model;

public class Space {

    private Piece piece;

    public Space(){
        this.piece = null;
    }

    public boolean hasPiece(){
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void removePiece(){
        piece = null;
    }
}
