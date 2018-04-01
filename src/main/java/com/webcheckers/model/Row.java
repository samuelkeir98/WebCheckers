package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a row in the board of a game
 */
public class Row implements Iterable<Space>{

    /** Number of rows and columns in a checker board */
    public static final int ROW_SIZE = 8;

    /** Spaces in the row */
    private List<Space> spaces;

    /** Location of the row respective to the board */
    private int index;

    /**
     * Initializes a Row
     * @param index location of Row in board
     */
    public Row(int index){
        this.index = index;
        spaces = new ArrayList<>(ROW_SIZE);
        for(int i = 0; i<ROW_SIZE; i++){
            spaces.add(new Space(i,(i+index)%2==1));
        }
    }

    /**
     * Places piece on specific space in row
     * @param piece piece to place
     * @param col location of space in the row to place piece on
     */
    public void placePiece(Piece piece,int col){
        spaces.get(col).setPiece(piece);
    }

    /**
     * Gets piece at specific location in row
     * @param index location in row to look at
     * @return Piece at location in row
     */
    public Piece getPieceAt(int index){
        return spaces.get(index).getPiece();
    }

    /**
     * Checks if space in row is a valid space
     * @param index index of space to check
     * @return true if space valid, false otherwise
     */
    public boolean isValid(int index){
        return spaces.get(index).isValid();
    }

    /**
     * Checks if piece is on space in row
     * @param index index of space to check
     * @return true if a piece is on the space, false otherwise
     */
    public boolean isPieceAt(int index){
        return spaces.get(index).hasPiece();
    }

    /**
     * Checks if piece of certain color on space
     * @param index space to check
     * @param color color of piece to check
     * @return
     */
    public boolean isPieceAt(int index, Color color) {
        Space space = spaces.get(index);
        return (isPieceAt(index) && space.getPiece().getColor() == color);
    }

    /**
     * Removes a piece from a space in the row
     * @param index index of space
     */
    public void removePiece(int index){
        spaces.get(index).removePiece();
    }

    /**
     * @return index of row in board
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return iterator of list of spaces
     */
    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }

}