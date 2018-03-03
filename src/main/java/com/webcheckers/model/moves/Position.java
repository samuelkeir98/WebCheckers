package com.webcheckers.model.moves;

/**
 * An easy value object method to pass positions around without needing too many parameters
 */
public class Position {

    /**
     * the row and column this position represents
     */
    private final int row,col;

    /**
     * creates the position value object
     * @param row the row
     * @param col the column
     */
    public Position(int row,int col){
        this.row = row;
        this.col = col;
    }

    /**
     * gets the row
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * gets the column
     * @return the column
     */
    public int getCol() {
        return col;
    }
}
