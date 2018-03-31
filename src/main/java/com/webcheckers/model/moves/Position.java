package com.webcheckers.model.moves;

import java.io.Serializable;
import java.util.Objects;

/**
 * An easy value object method to pass positions around without needing too many parameters
 * @author: Adrian Postolache
 * Tested by Sam Keir
 */
public class Position implements Serializable{

    /**
     * The row and column this position represents
     */
    private final int row,cell;

    /**
     * Creates the position value object
     * @param row The row
     * @param cell The column
     */
    public Position(int row,int cell){
        this.row = row;
        this.cell = cell;
    }

    /**
     * Gets the row
     * @return The row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column
     * @return The column
     */
    public int getCell() {
        return cell;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row,cell);
    }

    /**
     * Equals method for Position
     * @param obj Object being compared to
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position){
            Position position = (Position) obj;
            return row == position.row && cell == position.cell;
        }
        return false;
    }

    /**
     * Override for the toString method
     * @return  The row and cell representing the position
     */
    @Override
    public String toString() {
        return "Position row: "+row+" Cell: "+cell;
    }
}
