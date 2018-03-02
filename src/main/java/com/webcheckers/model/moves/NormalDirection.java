package com.webcheckers.model.moves;

public enum NormalDirection implements Direction {
    FL(-1,-1),
    FR(-1,1);

    private int row,col;

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
