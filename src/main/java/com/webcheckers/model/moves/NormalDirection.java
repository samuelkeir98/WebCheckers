package com.webcheckers.model.moves;

/**
 * Represents the four directions a regular piece can go
 * @author: Adrian Postolache
 */
public enum NormalDirection implements Direction {
    FL(-1,-1), //Front left move
    FR(-1,1); //Front right move

    /**
     * The step values in the row and column direction
     */
    private int row,col;

    /**
     * Constructor for the enum.
     * @param row step in the row direction
     * @param col step in the column direction
     */
    NormalDirection(int row,int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }
}
