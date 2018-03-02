package com.webcheckers.model.moves;

public enum KingDirection implements Direction {
    FL(-1,-1),
    FR(-1,1),
    BL(1,-1),
    BR(1,1);

    private int row,col;

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
