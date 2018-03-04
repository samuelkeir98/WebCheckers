package com.webcheckers.model.moves;

import java.util.Objects;

/**
 * An easy value object method to pass positions around without needing too many parameters
 */
public class Position {

    /**
     * the row and column this position represents
     */
    private final int row,cell;

    /**
     * creates the position value object
     * @param row the row
     * @param cell the column
     */
    public Position(int row,int cell){
        this.row = row;
        this.cell = cell;
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
    public int getCell() {
        return cell;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row,cell);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position){
            Position position = (Position) obj;
            return row == position.row && cell == position.cell;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Position row: "+row+" Cell: "+cell;
    }
}
