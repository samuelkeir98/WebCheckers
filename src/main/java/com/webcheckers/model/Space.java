package com.webcheckers.model;

public class Space {

    private Piece piece;

    private boolean isValid;

    private int cellIdx;

    public Space(int cellIdx, boolean isValid){
        this.cellIdx = cellIdx;
        this.isValid = isValid;
        this.piece = null;
    }

    public int getCellIdx() {
        return cellIdx;
    }

    public boolean isValid(){
        return piece == null && isValid;
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