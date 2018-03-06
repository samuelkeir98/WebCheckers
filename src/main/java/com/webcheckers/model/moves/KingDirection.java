package com.webcheckers.model.moves;


/**
 * Represents the four directions a king can go
 * @author: Adrian Postolache
 */
public enum KingDirection implements Direction {
    FL(-1,-1), //Front left move
    FR(-1,1), //Front right move
    BL(1,-1), //Back left move
    BR(1,1); //Back right move

    /**
     * The step values in the row and column direction
     */
    private int row,col;

    /**
     * Constructor for the enum.
     * @param row step in the row direction
     * @param col step in the column direction
     */
    KingDirection(int row,int col){
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
