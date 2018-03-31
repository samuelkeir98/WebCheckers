package com.webcheckers.model;

/**
 * Represents a Space in a checkers game
 */
public class Space {

    /** Piece on space */
    private Piece piece;

    /** If space can be moved onto */
    private boolean isValid;

    /** Column space is in */
    private int cellIdx;

    /**
     * Constructs and initializes a Space
     * @param cellIdx column of space in row
     * @param isValid if space is playable in game
     */
    public Space(int cellIdx, boolean isValid){
        this.cellIdx = cellIdx;
        this.isValid = isValid;
        this.piece = null;
    }

    /**
     * @return column of space
     */
    public int getCellIdx() {
        return cellIdx;
    }

    /**
     * @return true if space is playable, false otherwise
     */
    public boolean isValid(){
        return piece == null && isValid;
    }

    /**
     * @return true if piece is on space, false otherwise
     */
    public boolean hasPiece() {
        return piece != null;
    }

    /**
     * @return Piece that is on the space
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Puts a piece on the Space
     * @param piece piece to put on Space
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Removes piece from a Space
     */
    public void removePiece(){
        piece = null;
    }

    /**
     * @return String representation of a Space
     */
    @Override
    public String toString() {
        return (piece == null ? " ": piece.toString());
    }
}