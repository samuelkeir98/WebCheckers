package com.webcheckers.model.moves;

public class Position {
    private final int row,col;

    public Position(int row,int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
