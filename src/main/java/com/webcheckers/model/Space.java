package com.webcheckers.model;

public class Space {

    private Piece piece;

    private int cellIdx;

    public Space(){
        this.piece = null;
    }

    public int getCellIdx() {
        return cellIdx;
    }

    public boolean isValid(){
        return piece == null;
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

    @Override
    public String toString() {
        return (piece == null ? " ": piece.toString());
    }
}