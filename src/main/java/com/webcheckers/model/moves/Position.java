package com.webcheckers.model.moves;

import java.util.Objects;

/**
 * An easy value object method to pass positions around without needing too many parameters
 */
public class Position {

    /**
     * The row and column this position represents
     */
    private final int row,col;

    /**
     * Creates the position value object
     * @param row the row
     * @param col the column
     */
    public Position(int row,int col){
        this.row = row;
        this.col = col;
    }

    /**
     * Gets the row
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column
     * @return the column
     */
    public int getCol() {
        return col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row,col);
    }

    /**
     * Equals method for Position
     * @param obj object being compared to
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position){
            Position position = (Position) obj;
            return row == position.row && col == position.col;
        }
        return false;
    }
}
